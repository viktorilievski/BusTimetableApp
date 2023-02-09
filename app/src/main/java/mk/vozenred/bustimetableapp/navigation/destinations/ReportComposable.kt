package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.util.Constants.REPORT_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.reportComposable() {
  composable(route = REPORT_SCREEN) {
    // report screen composable here
  }
}