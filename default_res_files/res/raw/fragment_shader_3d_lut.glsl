precision highp float; // highp recommended because otherwise the values can beyond the range.

// Input Image #INPUT_TYPE is replaced with the input image type. ex. sampler2D or samplerExternalOES
uniform #INPUT_TYPE u_image;

// Lut-Texture with config MIN_FILTER and MAG_FILTER: GL_NEAREST, WRAP_S and WRAP_T: CLAMP_TO_EDGE, MIPMAP: NO!!!
uniform sampler2D u_lutTexture;

uniform float u_texRes;     // Texture-Resolution, must be power of 2: 64, 128, 256, 512, 1024, 2048, 4096
uniform float u_hTileCount; // Horizontal Blue Tiles
uniform float u_vTileCount; // Vertical Blue Tiles
uniform float u_intensity;  // Internsity of the mapped color range(0.0 - 1.0). (Values over 1.0 are possible and do intense the color differenz it's a nice effect.)

varying vec2 v_texCoord;

const float EPSILON = 0.000001;

// Try to get the exact pixel of the lut map image
vec3 lutColor(int texPosX, int texPosY) {
    return texture2D(u_lutTexture, (0.5 / u_texRes) + vec2(float(texPosX), float(texPosY)) / u_texRes).rgb;
}

vec3 belinearInterpolate(vec3 cRfGf, vec3 cRfGc, vec3 cRcGf, vec3 cRcGc, float redFract, float greenFract) {
    return mix(mix(cRfGf, cRcGf, redFract), mix(cRfGc, cRcGc, redFract), greenFract);
}

void main() {
    vec4 inputColor = clamp(texture2D(u_image, v_texCoord), 0.0, 1.0);

    // Revert premultiplied alpha
    vec3 sourceColor = vec3(inputColor.rgb / max(inputColor.a, EPSILON));

    float rRange = floor(u_texRes / u_hTileCount - 1.0);
    float gRange = floor(u_texRes / u_vTileCount - 1.0);
    float bRange = floor(u_hTileCount * u_hTileCount - 1.0);

    int rFloor = int(sourceColor.r * rRange);
    int gFloor = int(sourceColor.g * gRange);
    int bFloor = int(sourceColor.b * bRange);

    int rCeil = int(ceil(sourceColor.r * rRange));
    int gCeil = int(ceil(sourceColor.g * gRange));
    int bCeil = int(ceil(sourceColor.b * bRange));

    ivec2 tileFloor; // Blue tile index
    tileFloor.y = bFloor / int(u_hTileCount);
    tileFloor.x = bFloor - (tileFloor.y * int(u_hTileCount));

    ivec2 tileCeil; // Blue tile index
    tileCeil.y = bCeil / int(u_hTileCount);
    tileCeil.x = bCeil - (tileCeil.y * int(u_hTileCount));

    // Map tile index to tile pixel pos.
    tileFloor *= int(u_texRes / u_hTileCount);
    tileCeil  *= int(u_texRes / u_vTileCount);

    // Interpolate between red and green
    vec3 lutColorFB = belinearInterpolate(
        lutColor(tileFloor.x + rFloor, tileFloor.y + gFloor),
        lutColor(tileFloor.x + rFloor, tileFloor.y + gCeil),
        lutColor(tileFloor.x + rCeil,  tileFloor.y + gFloor),
        lutColor(tileFloor.x + rCeil,  tileFloor.y + gCeil),
        fract(sourceColor.r * rRange), fract(sourceColor.g * gRange)
    );
    vec3 lutColorCB = belinearInterpolate(
        lutColor(tileCeil.x + rFloor, tileCeil.y + gFloor),
        lutColor(tileCeil.x + rFloor, tileCeil.y + gCeil),
        lutColor(tileCeil.x + rCeil,  tileCeil.y + gFloor),
        lutColor(tileCeil.x + rCeil,  tileCeil.y + gCeil),
        fract(sourceColor.r * rRange), fract(sourceColor.g * gRange)
    );

    // Interpolate between the blue values
    vec3 interpolation = mix(lutColorFB, lutColorCB, fract(sourceColor.b * bRange));

    // Round Color Values to prevent that for ex. 254.999999 is cutting to 254 instead of 255;
    interpolation = clamp(floor(interpolation * 255. + .5) / 255., 0.0, 1.0);

    // Mix the intensity and do premultiplie alpha
    gl_FragColor = mix(inputColor, vec4(interpolation.rgb * inputColor.a, inputColor.a), u_intensity);
}



