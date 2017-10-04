precision highp float;

uniform #INPUT_TYPE u_image;
uniform float u_blurRadius;
uniform float u_gradientSize;
uniform float u_size;

uniform vec2 u_startPosition;
uniform vec2 u_endPosition;
uniform vec2 u_delta;
uniform vec2 u_texSize;

varying vec2 v_texCoord;

const float blurSteps = 5.0;
const float EPSILON = 0.000001;

void main() {
    vec4 color = vec4(0.0);
    float total = 0.0;
    vec2 normal = normalize(vec2(u_startPosition.y - u_endPosition.y, u_endPosition.x - u_startPosition.x));
    float radius = u_blurRadius * smoothstep(
        0.0,
        1.0,
        (abs(dot(v_texCoord * u_texSize - u_startPosition, normal)) - u_size) / u_gradientSize
    );

    float percent;
    float weight;
    vec4 samplePix;

    float lod = max(sqrt(radius / blurSteps), 0.0);
    for (float t = -blurSteps; t <= blurSteps; t++) {
        percent = t / blurSteps;

        samplePix = texture2D(u_image, v_texCoord + u_delta * percent * radius / u_texSize, lod);
        samplePix.rgb /= max(samplePix.a, EPSILON); // Revert premultiplied alpha

        weight = (1.0 - abs(percent));
        color += samplePix * weight;
        total += weight;
    }

    color /= max(total, EPSILON);
    color.rgb *= color.a; // Do premultiplie alpha

    gl_FragColor = color;
}