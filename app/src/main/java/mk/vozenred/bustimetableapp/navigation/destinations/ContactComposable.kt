package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.ui.screens.contact.ContactScreen
import mk.vozenred.bustimetableapp.util.Constants.CONTACT_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.contactComposable(
  navigateToSearchScreen: () -> Unit
) {
  composable(route = CONTACT_SCREEN) {
    // contact screen composable here
    ContactScreen(
      navigateToSearchScreen = navigateToSearchScreen
    )
  }
}