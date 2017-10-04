precision highp float;

varying vec2 v_texCoord;
uniform #INPUT_TYPE u_image;

uniform float u_gamma;
uniform float u_shadows;
uniform float u_highlights;

uniform mat4 u_colorMatrix;
uniform vec4 u_colorOffset;

const float EPSILON = 0.0000001;

float calculateLuminance(vec3 rgb) {
    // This is the luminance calculation part of the RGB to HSL formular.
    vec4 p = mix(
        vec4(rgb.gb, 0.0, -1.0 / 3.0),
        vec4(rgb.bg, -1.0, 2.0 / 3.0),
        vec4(rgb.g < rgb.b)
    );

    vec4 q = mix(
        vec4(rgb.r, p.yzx),
        vec4(p.xyw, rgb.r),
        vec4(rgb.r < p.x)
    );

    float croma = q.x - min(q.w, q.y);
    float luminance = q.x - croma * 0.5;
    return luminance;
}

void main() {

  vec4 color = clamp(texture2D(u_image, v_texCoord), 0.0, 1.0);
  color.rgb /= max(color.a, EPSILON); // Revert premultiplied alpha

  // Apply gamma
  if (u_gamma != 1.0) {
    color.rgb = pow(color.rgb, vec3(1.0 / max(u_gamma, EPSILON)));
  }

  // Apply shadows and highlights
  float luminance = calculateLuminance(color.rgb);

  float shadow = u_shadows >= 0.0
    ? clamp(
          pow(luminance, 1.0 / (u_shadows + 1.0))
          + pow(luminance, 2.0 / (u_shadows + 1.0)) * -0.76
          - luminance
    , 0.0, max(u_shadows, 1.0))
    : -clamp(
          pow(luminance, 1.0 / (-u_shadows + 1.0))
          + pow(luminance, 2.0 / (-u_shadows + 1.0)) * -0.76
          - luminance
    , 0.0, max(-u_shadows, 1.0));

  float highlight = u_highlights < 0.0
    ? clamp(
          1.0
          - pow(1.0 - luminance, 1.0 / (1.0 - u_highlights))
          - pow(1.0 - luminance, 2.0 / (1.0 - u_highlights)) * -0.8
          - luminance
     , -1.0, 0.0)
    : -clamp(
          1.0
          - pow(1.0 - luminance, 1.0 / (1.0 + u_highlights))
          - pow(1.0 - luminance, 2.0 / (1.0 + u_highlights)) * -0.8
          - luminance
     , -1.0, 0.0);

  // Bright color need more contrast and dark color need more brightness.
  // This is to keep saturatation because the color information of a dark colors is lost.
  float shadowContrast   = shadow * luminance * luminance;
  float shadowBrightness = shadow - shadowContrast;
  color.rgb = clamp((luminance + highlight + shadowContrast) * ((color.rgb + shadowBrightness) / max(luminance, EPSILON)), 0.0, 1.0);

  // Apply Color Matrix
  color = clamp(color * u_colorMatrix + u_colorOffset, 0.0, 1.0);

  color.rgb *= color.a; // Do premultiplie alpha

  gl_FragColor = color;
}