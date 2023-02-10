package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.util.Constants.FAVORITES_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.favoritesComposable() {
  composable(
    route = FAVORITES_SCREEN
  ) {
    // favorites composable design
  }
}