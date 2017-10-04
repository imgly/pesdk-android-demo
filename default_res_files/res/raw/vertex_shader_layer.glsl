attribute vec4 a_position;
attribute vec4 a_texCoord;
attribute vec4 a_backgroundTexCoord;

varying vec2 v_texCoord;
varying vec2 v_backgroundTexCoord;

void main() {
    gl_Position = a_position;
    v_texCoord = a_texCoord.xy;
    v_backgroundTexCoord = a_backgroundTexCoord.xy;
}