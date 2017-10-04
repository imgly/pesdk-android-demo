precision mediump float;

varying mediump vec2 v_texCoord;
uniform mediump #INPUT_TYPE u_image;

void main() {
    vec4 color = texture2D(u_image, v_texCoord);
    gl_FragColor = color;
}