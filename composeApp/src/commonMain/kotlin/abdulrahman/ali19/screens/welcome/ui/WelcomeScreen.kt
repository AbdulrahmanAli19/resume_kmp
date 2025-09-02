package abdulrahman.ali19.screens.welcome.ui

import abdulrahman.ali19.core.ui.shader.shaderBackground
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier.fillMaxSize().shaderBackground(SpaceShader),
) {
    //SnowfallEffect()
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Hi, I'm",
                    style = MaterialTheme.typography.h4.copy(
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Abdulrahman",
                    style = MaterialTheme.typography.h2.copy(
                        color = Color.White
                    )
                )
            }
        }
    }
}
