package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.ui.screens.info.InfoScreen
import mk.vozenred.bustimetableapp.util.Constants.INFO_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.infoComposable(
  navigateFromDrawerTo: (String) -> Unit,

  ) {
  composable(
    route = INFO_SCREEN
  ) {
    InfoScreen(
      navigateFromDrawerTo = navigateFromDrawerTo
    )
  }
}