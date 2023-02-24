package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.ui.screens.search_relations.SearchRelationsScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.Constants.SEARCH_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchComposable(
  navigateToListRelations: () -> Unit,
  navigateToStartDestination: () -> Unit,
  navigateToEndDestination: () -> Unit,
  navigateToContactScreen: () -> Unit,
  navigateToFavoriteRelationsScreen: () -> Unit,
  sharedViewModel: SharedViewModel
) {
  composable(
    route = SEARCH_SCREEN,
  ) {
    SearchRelationsScreen(
      navigateToRelationsScreen = navigateToListRelations,
      navigateToStartDestinationScreen = navigateToStartDestination,
      navigateToEndDestinationScreen = navigateToEndDestination,
      navigateToContactScreen = navigateToContactScreen,
      navigateToFavoriteRelationsScreen = navigateToFavoriteRelationsScreen,
      sharedViewModel = sharedViewModel
    )
  }
}