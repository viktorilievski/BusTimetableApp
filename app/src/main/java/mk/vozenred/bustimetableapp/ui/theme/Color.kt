package mk.vozenred.bustimetableapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple50 = Color(0xFFEFE5FD)
val Purple100 = Color(0xFFd5bff9)
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple600 = Color(0xFF5000C4)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Accent = Color(0xFF0CCA4A)
val Complementary = Color(0xFF008B00)
val AlmostPureWhite = Color(0xFFFCFAFA)
val LightGray = Color(0xFFC8D3D5)
val SteelTeal = Color(0xFF6E8387)


val Colors.splashScreenBackgroundColor: Color
  @Composable
  get() = if (isLight) Color.Green else Color.Black

val Colors.searchRelationsScreenBackgroundColor: Color
  @Composable
  get() = if (isLight) Color.White else Color.Yellow

val Colors.accentColor: Color
  @Composable
  get() = if (isLight) Purple100 else SteelTeal

val Colors.complementaryColor: Color
  @Composable
  get() = if (isLight) Complementary else Accent

val Colors.iconColor: Color
  @Composable
  get() = if (isLight) Color.Black else Color.White

val Colors.cardBorderColor: Color
  @Composable
  get() = if (isLight) SteelTeal else SteelTeal

val Colors.topAppBarBackgroundColor: Color
  @Composable
  get() = if (isLight) Purple500 else MaterialTheme.colors.surface

val Colors.topAppBarContentColor: Color
  @Composable
  get() = Color.White

val Colors.drawerBackgroundColor: Color
  @Composable
  get() = if (isLight) Color.White else Color.Black
