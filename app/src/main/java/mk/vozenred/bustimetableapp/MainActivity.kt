package mk.vozenred.bustimetableapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import mk.vozenred.bustimetableapp.navigation.SetupNavigation
import mk.vozenred.bustimetableapp.ui.theme.BusTimetableAppTheme
import mk.vozenred.bustimetableapp.ui.viewmodels.FavoriteRelationsViewModel
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel
import mk.vozenred.bustimetableapp.util.NetworkConnectionLiveData

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private lateinit var navController: NavHostController
  private val sharedViewModel: SharedViewModel by viewModels()
  private val splashScreenViewModel: SplashScreenViewModel by viewModels()
  private val favoriteRelationsViewModel: FavoriteRelationsViewModel by viewModels()

  @OptIn(ExperimentalAnimationApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    checkInternetConnection()
    setContent {
      BusTimetableAppTheme {
        navController = rememberAnimatedNavController()
        SetupNavigation(
          navController = navController,
          sharedViewModel = sharedViewModel,
          favoriteRelationsViewModel = favoriteRelationsViewModel,
          splashScreenViewModel = splashScreenViewModel
        )
      }
    }
  }

  private fun checkInternetConnection() {
    val connection = NetworkConnectionLiveData(this)
    connection.observe(this) { isConnected ->
      splashScreenViewModel.networkStatus.value = isConnected
    }
  }
}