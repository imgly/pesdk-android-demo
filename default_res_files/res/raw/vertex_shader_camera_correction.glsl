attribute vec4 a_position;
attribute vec4 a_texCoord;
attribute vec4 a_backgroundTexCoord;

uniform mat4 u_correctionMatrix;

varying vec2 v_texCoord;

void main() {
    gl_Position = u_correctionMatrix * a_position;
    v_texCoord = a_texCoord.xy;
}