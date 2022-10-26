package mk.vozenred.bustimetableapp.ui.theme

import android.hardware.lights.Light
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Accent = Color(0xFF0CCA4A)
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
get() = if (isLight) Accent else Accent

val Colors.cardBackgroundColor: Color
@Composable
get() = if (isLight) AlmostPureWhite else AlmostPureWhite

val Colors.relationBetweenIconColor: Color
@Composable
get() = if (isLight) LightGray else LightGray

val Colors.cardBorderColor: Color
@Composable
get() = if (isLight) SteelTeal else SteelTeal

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) Purple500 else Color.Black

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.White else LightGray