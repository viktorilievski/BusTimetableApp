package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.ui.screens.settings.SettingsScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SettingsScreenViewModel
import mk.vozenred.bustimetableapp.util.Constants.SETTINGS_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsComposable(
  settingsScreenViewModel: SettingsScreenViewModel,
  onBackArrowClick: () -> Unit
) {
  composable(route = SETTINGS_SCREEN) {
    SettingsScreen(
      settingsScreenViewModel = settingsScreenViewModel,
      onBackArrowClick = onBackArrowClick
    )
  }
}