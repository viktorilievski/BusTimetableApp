package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.ui.screens.favorite_relations.FavoriteRelationsScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.FavoriteRelationsViewModel
import mk.vozenred.bustimetableapp.util.Constants.FAVORITES_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.favoritesComposable(
  favoriteRelationsViewModel: FavoriteRelationsViewModel,
  navigateToSearchScreen: () -> Unit,
  navigateToContactScreen: () -> Unit,
  navigateToReportScreen: (Int) -> Unit
) {
  composable(
    route = FAVORITES_SCREEN
  ) {
    FavoriteRelationsScreen(
      favoriteRelationsViewModel = favoriteRelationsViewModel,
      navigateToContactScreen = navigateToContactScreen,
      navigateToSearchScreen = navigateToSearchScreen,
      navigateToReportScreen = navigateToReportScreen
    )
  }
}