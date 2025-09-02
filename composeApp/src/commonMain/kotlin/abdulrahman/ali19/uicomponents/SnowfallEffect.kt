package abdulrahman.ali19.uicomponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import kotlin.random.Random

@Composable
fun SnowfallEffect(
    modifier: Modifier = Modifier,
    snowflakeCount: Int = 200
) {
    var canvasSize by remember { mutableStateOf<Size?>(null) }

    if (canvasSize != null) {
        val (width, height) = canvasSize!!

        val snowflakes = remember(width) {
            List(snowflakeCount) {
                mutableStateOf(
                    Snowflake(
                        x = Random.nextFloat() * width,
                        y = Random.nextFloat() * height,
                        radius = Random.nextFloat() * 4f + 2f,
                        speed = Random.nextFloat() * 50f + 50f,
                        drift = Random.nextFloat() * 20f - 10f
                    )
                )
            }
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            snowflakes.forEach {
                val flake = it.value
                drawCircle(
                    color = Color.White,
                    radius = flake.radius,
                    center = Offset(flake.x, flake.y)
                )
            }
        }
        LaunchedEffect(Unit) {
            var lastTimeNanos = 0L
            while (true) {
                withFrameNanos { time ->
                    if (lastTimeNanos == 0L) {
                        lastTimeNanos = time
                        return@withFrameNanos
                    }

                    val deltaSeconds = (time - lastTimeNanos) / 1_000_000_000f
                    lastTimeNanos = time

                    snowflakes.forEach { flakeState ->
                        val flake = flakeState.value
                        val newY = flake.y + flake.speed * deltaSeconds
                        val newX = flake.x + flake.drift * deltaSeconds
                        flakeState.value = flake.copy(
                            x = newX,
                            y = newY
                        )
                    }
                }
            }
        }

        Canvas(
            modifier = modifier.fillMaxSize()
        ) {
            val width = size.width
            val height = size.height
            snowflakes.forEach { flakeState ->
                var flake = flakeState.value

                // Reset when out of bounds
                if (flake.y > height) {
                    flake = flake.copy(
                        y = 0f,
                        x = Random.nextFloat() * width
                    )
                    flakeState.value = flake
                }

                drawCircle(
                    color = Color.White,
                    radius = flake.radius,
                    center = Offset(flake.x, flake.y)
                )
            }
        }

    } else {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged {
                    canvasSize = Size(it.width.toFloat(), it.height.toFloat())
                }
        ) {

        }
    }
}

data class Snowflake(
    val x: Float,
    val y: Float,
    val radius: Float,
    val speed: Float,
    val drift: Float
)