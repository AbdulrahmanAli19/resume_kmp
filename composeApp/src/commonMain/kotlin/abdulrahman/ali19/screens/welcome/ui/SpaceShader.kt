package abdulrahman.ali19.screens.welcome.ui

import abdulrahman.ali19.core.ui.shader.Shader


object SpaceShader : Shader {

    override val speedModifier: Float
        get() = 1.0f

    override val sksl = """
/**
 * Ether Shader (Centered & Slowed Down, Transparent Background)
 *  * SkSL conversion of a GLSL shader with transparency support
 */

uniform float uTime;
uniform vec3 uResolution;

const float timeSpeed = 0.2;

mat2 rotate(float angle) {
    float c = cos(angle), s = sin(angle);
    return mat2(c, -s, s, c);
}

float map(vec3 p) {
    float t = uTime * timeSpeed;
    p.xz *= rotate(t * 0.4);
    p.xy *= rotate(t * 0.3);

    vec3 q = p * 2.0 + t;
    return length(p + vec3(sin(t * 0.7), 0.0, 0.0)) * log(length(p) + 1.0)
         + sin(q.x + sin(q.z + sin(q.y))) * 0.5 - 1.0;
}

vec4 main(vec2 fragCoord) {
    vec2 uv = (fragCoord - 0.5 * uResolution.xy) / uResolution.y;

    vec3 finalColor = vec3(0.0);
    float dist = 2.5;

    for (int i = 0; i <= 5; i++) {
        vec3 p = vec3(0.0, 0.0, 5.0) + normalize(vec3(uv, -1.0)) * dist;

        float rz = map(p);
        float lighting = clamp((rz - map(p + 0.1)) * 0.5, -0.1, 1.0);
        vec3 lightColor = vec3(0.1, 0.3, 0.4) + vec3(5.0, 2.5, 3.0) * lighting;

        finalColor = finalColor * lightColor + smoothstep(2.5, 0.0, rz) * 0.7 * lightColor;
        dist += min(rz, 1.0);
    }

    // Compute brightness to determine transparency
    float brightness = dot(finalColor, vec3(0.299, 0.587, 0.114));

    // If brightness is low, treat it as background and set alpha to 0
    float alpha = brightness > 0.05 ? 1.0 : 0.0;

    return vec4(finalColor, alpha);
}
"""

}