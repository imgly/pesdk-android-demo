precision highp float;

varying vec2 v_texCoord;
varying vec2 v_backgroundTexCoord;

uniform #INPUT_TYPE u_backgroundImage;
uniform sampler2D u_image;

uniform float u_alpha;

/*
ALPHA: 0,
OVERLAY: 1,
HARD_LIGHT: 2,
SOFT_LIGHT: 3,
MULTIPLY: 4,
DARKEN: 5,
LIGHTEN: 6,
SCREEN: 7,
COLOR_BURN: 8
*/
uniform int u_blendmode;

const float EPSILON = 0.000001;

// https://en.wikipedia.org/wiki/Blend_modes

vec4 overlay(vec4 a, vec4 b) {
    vec3 result = mix(
          1.0 - 2.0 * (1.0 - a.rgb) * (1.0 - b.rgb),
          2.0 * a.rgb * b.rgb,
          vec3(a.r < 0.5, a.g < 0.5, a.b < 0.5)
      );
    return mix(a, vec4(result, a.a), u_alpha * b.a);
}

vec4 hardLight(vec4 b, vec4 a) {
    vec3 result = mix(
          1.0 - 2.0 * (1.0 - a.rgb) * (1.0 - b.rgb),
          2.0 * a.rgb * b.rgb,
          vec3(a.r < 0.5, a.g < 0.5, a.b < 0.5)
      );
    return mix(b, vec4(result, b.a), u_alpha * a.a);
}

vec4 softLight(vec4 a, vec4 b) {
    vec3 result = mix(
          2.0 * a.rgb * (1.0 - b.rgb) + (2.0 * b.rgb - 1.0) * sqrt(a.rgb),
          2.0 * a.rgb * b.rgb + a.rgb * a.rgb * (1.0 - 2.0 * b.rgb),
          vec3(b.r < 0.5, b.g < 0.5, b.b < 0.5)
      );
    return mix(a, vec4(result, a.a), u_alpha * b.a);
}

vec4 multiply(vec4 a, vec4 b) {
    vec3 result = a.rgb * b.rgb;
    return mix(a, vec4(result, a.a), u_alpha * b.a);
}

vec4 darken(vec4 a, vec4 b) {
    vec3 result = min(a.rgb, b.rgb);
    return mix(a, vec4(result, a.a), u_alpha * b.a);
}

vec4 lighten(vec4 a, vec4 b) {
    vec3 result = max(a.rgb, b.rgb);
    return mix(a, vec4(result, a.a), u_alpha * b.a);
}

vec4 screen(vec4 a, vec4 b) {
    vec3 result = 1.0 - (1.0 - a.rgb) * (1.0 - b.rgb);
    return mix(a, vec4(result, a.a), u_alpha * b.a);
}

vec4 colorBurn(vec4 a, vec4 b) {
    vec3 result = clamp(1.0 - (1.0 - a.rgb) / max(b.rgb, EPSILON), 0.0, 1.0);
    return mix(a, vec4(result, a.a), u_alpha * b.a);
}

vec4 normal(vec4 a, vec4 b) {
    return mix(a, vec4(b.rgb, a.a), u_alpha * b.a);
}

void main() {

  vec4 backgroundColor = texture2D(u_backgroundImage, v_backgroundTexCoord);
  vec4 layerColor      = texture2D(u_image, v_texCoord);

  backgroundColor.rgb /= max(backgroundColor.a, EPSILON);
  layerColor.rgb      /= max(layerColor.a, EPSILON);

  vec4 resultColor;
  if (u_blendmode == 0) {
    resultColor = normal(backgroundColor, layerColor);
  } else if (u_blendmode == 1) {
    resultColor = overlay(backgroundColor, layerColor);
  } else if (u_blendmode == 2) {
    resultColor = hardLight(backgroundColor, layerColor);
  } else if (u_blendmode == 3) {
    resultColor = softLight(backgroundColor, layerColor);
  } else if (u_blendmode == 4) {
    resultColor = multiply(backgroundColor, layerColor);
  } else if (u_blendmode == 5) {
    resultColor = darken(backgroundColor, layerColor);
  } else if (u_blendmode == 6) {
    resultColor = lighten(backgroundColor, layerColor);
  } else if (u_blendmode == 7) {
    resultColor = screen(backgroundColor, layerColor);
  } else if (u_blendmode == 8) {
    resultColor = colorBurn(backgroundColor, layerColor);
  }

  gl_FragColor = vec4(vec3(resultColor.rgb * resultColor.a), resultColor.a);
}