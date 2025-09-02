package abdulrahman.ali19.core.ui

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.joy_pixels
import resume.composeapp.generated.resources.notosans_black
import resume.composeapp.generated.resources.notosans_bold
import resume.composeapp.generated.resources.notosans_extrabold
import resume.composeapp.generated.resources.notosans_extralight
import resume.composeapp.generated.resources.notosans_light
import resume.composeapp.generated.resources.notosans_medium
import resume.composeapp.generated.resources.notosans_regular
import resume.composeapp.generated.resources.notosans_semibold
import resume.composeapp.generated.resources.notosans_thin

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
    typography: Typography = Typography(defaultFontFamily = tekoFontFamily()) /*MaterialTheme.typography*/,
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

@Composable
fun tekoFontFamily() = FontFamily(
    Font(Res.font.notosans_thin, weight = FontWeight.W100),
    Font(Res.font.notosans_extralight, weight = FontWeight.W200),
    Font(Res.font.notosans_light, weight = FontWeight.W300),
    Font(Res.font.notosans_regular, weight = FontWeight.W400),
    Font(Res.font.notosans_medium, weight = FontWeight.W500),
    Font(Res.font.notosans_semibold, weight = FontWeight.W600),
    Font(Res.font.notosans_bold, weight = FontWeight.W700),
    Font(Res.font.notosans_extrabold, weight = FontWeight.W800),
    Font(Res.font.notosans_black, weight = FontWeight.W900)
)

@Composable
fun joyPixelsFontFamily() = FontFamily(Font(Res.font.joy_pixels))
