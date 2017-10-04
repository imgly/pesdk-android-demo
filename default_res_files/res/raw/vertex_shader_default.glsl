attribute vec4 a_position;
attribute vec4 a_texCoord;

varying mediump vec2 v_texCoord;

void main() {
    gl_Position = a_position;
    v_texCoord = a_texCoord.xy;
}
