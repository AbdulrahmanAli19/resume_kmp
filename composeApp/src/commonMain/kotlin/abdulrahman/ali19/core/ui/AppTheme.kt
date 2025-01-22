package abdulrahman.ali19.core.ui

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val darkColorScheme = Colors(
    background = Color.Black,
    surface = Color(0xFF1E1E1E),
    onBackground = Color.White,
    onSurface = Color.White,
    primary = Color(0xFF4F8BF9),
    primaryVariant = Color(0xFF3D6ED5),
    secondary = Color(0xFF9E9E9E),
    secondaryVariant = Color(0xFF757575),
    error = Color(0xFFD32F2F),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onError = Color.White,
    isLight = false
)

@Composable
fun AppTheme(
    color: Colors = darkColorScheme,
    typography: Typography = MaterialTheme.typography,
    shapes: Shapes = MaterialTheme.shapes,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = color,
        typography = typography,
        shapes = shapes,
        content = content
    )
}