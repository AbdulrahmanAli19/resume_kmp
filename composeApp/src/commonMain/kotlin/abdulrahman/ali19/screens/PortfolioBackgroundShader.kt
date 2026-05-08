package abdulrahman.ali19.screens

import abdulrahman.ali19.core.ui.shader.Shader

object PortfolioBackgroundShader : Shader {

    override val speedModifier: Float
        get() = 0.35f

    override val sksl = """
uniform float uTime;
uniform vec3 uResolution;

// Very slow motion
const float timeSpeed = 0.08;

// Brand colors
const vec3 DARK      = vec3(0.005, 0.008, 0.025); // almost black navy
const vec3 DARK_BLUE = vec3(0.015, 0.025, 0.075);
const vec3 PURPLE    = vec3(0.486, 0.227, 0.929); // #7C3AED
const vec3 MAGENTA   = vec3(0.850, 0.120, 0.950);
const vec3 BLUE      = vec3(0.133, 0.827, 0.933); // #22D3EE

float hash(vec2 p) {
    p = fract(p * vec2(123.34, 456.21));
    p += dot(p, p + 45.32);
    return fract(p.x * p.y);
}

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);

    float a = hash(i);
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));

    vec2 u = f * f * (3.0 - 2.0 * f);

    return mix(a, b, u.x)
         + (c - a) * u.y * (1.0 - u.x)
         + (d - b) * u.x * u.y;
}

float circleGlow(vec2 uv, vec2 center, float radius, float blur) {
    float d = length(uv - center);
    return smoothstep(radius + blur, radius, d);
}

float lineGlow(float d, float width, float blur) {
    return 1.0 - smoothstep(width, width + blur, abs(d));
}

mat2 rotate(float angle) {
    float c = cos(angle);
    float s = sin(angle);
    return mat2(c, -s, s, c);
}

vec4 main(vec2 fragCoord) {
    vec2 uv = fragCoord / uResolution.xy;
    vec2 p = (fragCoord - 0.5 * uResolution.xy) / uResolution.y;

    float t = uTime * timeSpeed;

    // Base very dark background
    vec3 color = DARK;

    // Subtle vertical depth gradient
    color += mix(DARK_BLUE, vec3(0.0), uv.y) * 0.65;

    // Main soft neon glow from top-left
    float glow1 = circleGlow(
        p,
        vec2(-0.55 + sin(t * 0.7) * 0.08, 0.35 + cos(t * 0.5) * 0.05),
        0.03,
        0.75
    );

    // Secondary cyan glow from bottom-center
    float glow2 = circleGlow(
        p,
        vec2(0.20 + cos(t * 0.4) * 0.08, -0.55 + sin(t * 0.6) * 0.04),
        0.02,
        0.85
    );

    // Right-side purple curve glow
    vec2 curveP = p;
    curveP.x += sin(curveP.y * 4.0 + t * 2.0) * 0.08;
    float curve = lineGlow(curveP.x - 0.88, 0.003, 0.035);

    color += PURPLE * glow1 * 0.65;
    color += BLUE * glow2 * 0.45;
    color += mix(PURPLE, BLUE, uv.y) * curve * 0.5;

    // Subtle animated mist/noise
    float n = noise(p * 3.0 + vec2(t * 0.3, -t * 0.2));
    color += mix(PURPLE, BLUE, n) * n * 0.035;

    // Very subtle dotted tech pattern
    vec2 grid = fract((p + vec2(t * 0.02, 0.0)) * 38.0);
    float dotShape = 1.0 - smoothstep(0.015, 0.035, length(grid - 0.5));

    // Show dots mostly on left and right edges, like the mockup
    float edgeMask = smoothstep(0.45, 0.95, abs(p.x));
    float verticalMask = smoothstep(-0.55, 0.15, p.y) * (1.0 - smoothstep(0.45, 0.75, p.y));

    color += mix(PURPLE, BLUE, uv.x) * dotShape * edgeMask * verticalMask * 0.18;

    // Soft vignette
    float vignette = smoothstep(0.95, 0.25, length(p));
    color *= vignette;

    // Keep it dark and premium
    color = pow(color, vec3(1.05));

    return vec4(color, 1.0);
}
"""
}