package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import mk.vozenred.bustimetableapp.ui.screens.contact.ContactScreen
import mk.vozenred.bustimetableapp.util.Constants.CONTACT_SCREEN

fun NavGraphBuilder.contactComposable(
  navigateToSearchScreen: () -> Unit,
  navigateToReportScreen: () -> Unit
) {
  composable(route = CONTACT_SCREEN) {
    // contact screen composable here
    ContactScreen(
      navigateToReportScreen = navigateToReportScreen,
      navigateToSearchScreen = navigateToSearchScreen
    )
  }
}