package abdulrahman.ali19.uicomponents

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DynamicGradientBackground(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(
        Color(0xFF8E44AD),
        Color(0xFF3498DB),
        Color(0xFF2C3E50),
        Color.Transparent
    )
) {
    val infiniteTransition = rememberInfiniteTransition(label = "GradientTransition")

    val animatedColor by infiniteTransition.animateColor(
        initialValue = colors[0],
        targetValue = colors.last(),
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 9000
                0f to colors[0]
                1000f to colors[1]
                2000f to colors[2]
                3000f to colors[0]
            },
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                Brush.verticalGradient(colors = listOf(animatedColor, colors.last()))
            )
    )
}