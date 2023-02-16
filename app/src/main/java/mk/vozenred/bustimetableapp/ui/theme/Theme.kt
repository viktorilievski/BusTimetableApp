package mk.vozenred.bustimetableapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
  primary = SteelTeal,
  primaryVariant = Color.DarkGray,
  secondary = Teal200,
  onPrimary = Color.White
)

private val LightColorPalette = lightColors(
  primary = Purple500,
  primaryVariant = Purple700,
  secondary = Teal200,

  /* Other default colors to override
  background = Color.White,
  surface = Color.White,
  onPrimary = Color.White,
  onSecondary = Color.Black,
  onBackground = Color.Black,
  onSurface = Color.Black,
  */
)

@Composable
fun BusTimetableAppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {

  val systemUiController = rememberSystemUiController()

  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  if (darkTheme) {
    systemUiController.setSystemBarsColor(
      color = Color.Black,
      darkIcons = false
    )
  } else {
    systemUiController.setSystemBarsColor(
      color = Purple600,
      darkIcons = false,
    )
  }

//  val view = LocalView.current
//  if (!view.isInEditMode) {
//    SideEffect {
//      val window = (view.context as Activity).window
//      window.statusBarColor = colors.primaryVariant.toArgb()
//      WindowCompat.getInsetsController(window, view)
//        .isAppearanceLightStatusBars = darkTheme
//      WindowCompat.getInsetsController(window, view)
//        .isAppearanceLightNavigationBars = darkTheme
//    }
//  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}