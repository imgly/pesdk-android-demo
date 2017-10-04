precision highp float;

//Input Image with config WRAP_S and WRAP_T: CLAMP_TO_EDGE
uniform #INPUT_TYPE u_image;

// Size of one pixel == vec2(1.0 / u_image.width, 1.0 / u_image.height)
uniform vec2 u_pixelDimension;

// Range (-1.0 - 1.0)
uniform float u_clarity;

varying vec2 v_texCoord;

uniform mat4 u_colorMatrix;
uniform vec4 u_colorOffset;

const float EPSILON = 0.000001;

void main() {
    vec4 color = texture2D(u_image, v_texCoord);

    vec4 mergedColor = color;
    mergedColor += texture2D(u_image, v_texCoord + vec2(-u_pixelDimension.x, -u_pixelDimension.y));
    mergedColor += texture2D(u_image, v_texCoord + vec2(-u_pixelDimension.x,                 0.0));
    mergedColor += texture2D(u_image, v_texCoord + vec2(-u_pixelDimension.x,  u_pixelDimension.y));

    mergedColor += texture2D(u_image, v_texCoord + vec2(                0.0, -u_pixelDimension.y));
    mergedColor += texture2D(u_image, v_texCoord + vec2(                0.0,  u_pixelDimension.y));

    mergedColor += texture2D(u_image, v_texCoord + vec2( u_pixelDimension.x, -u_pixelDimension.y));
    mergedColor += texture2D(u_image, v_texCoord + vec2( u_pixelDimension.x,                 0.0));
    mergedColor += texture2D(u_image, v_texCoord + vec2( u_pixelDimension.x,  u_pixelDimension.y));

    mergedColor /= 9.0;

    color.rgb /= max(color.a, EPSILON);       // Revert premultiplied alpha
    mergedColor.rgb /= max(mergedColor.a, EPSILON); // Revert premultiplied alpha

    float grayValue = clamp(color.r * 0.3 + color.g * 0.59 + color.b * 0.1, 0.111111, 0.999999);
    // 1.0 and 0.0 result in white not black, therefore we clamp

    // Here we create a function that will map values below 0.1 to 0. Values above 0.2 will be mapped to 1,
    // and for values between 0.1 and 0.2 it will produce a gradient.
    // The funtion is mirror at 0.5, meaning values between 0.8 and 0.9 will result in a decending gradient.
    // And values above 0.9 will be mapped to 0.
    float frequenceFactor = min(smoothstep(1.0 - grayValue, 0.0, 0.11), smoothstep(grayValue, 0.0, 0.11));

    // here we apply the high pass filter. Its strength is determined by the uniform ,
    // and the frequence factor. That means the only the mid tones are affected by this filter.
    // Clarity input is ranging from -1 to 1. But we want to strengthen the effect.
    // Therefore we see this little magic number '3.7'.
    color = clamp(color + clamp((color - mergedColor) * u_clarity * 3.7 * frequenceFactor, 0.0, 10.0), 0.0, 1.0);

    // apply exposure but only to the mid tones.
    color.rgb = color.rgb * pow(2.0, u_clarity * 0.27 * frequenceFactor);

    // apply contrast and desaturation matrix


    color = clamp(color * u_colorMatrix + u_colorOffset, 0.0, 1.0);

    color.rgb *= color.a; // Do premultiplie alpha

    gl_FragColor = clamp(color * u_colorMatrix + u_colorOffset, 0.0, 1.0);
}