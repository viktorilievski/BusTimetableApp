package mk.vozenred.bustimetableapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mk.vozenred.bustimetableapp.navigation.SetupNavigation
import mk.vozenred.bustimetableapp.ui.theme.BusTimetableAppTheme
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel
import mk.vozenred.bustimetableapp.util.NetworkConnectionLiveData

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private lateinit var navController: NavHostController
  private val sharedViewModel: SharedViewModel by viewModels()
  private val splashScreenViewModel: SplashScreenViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    checkInternetConnection()
    setContent {
      BusTimetableAppTheme {
        navController = rememberNavController()
        SetupNavigation(
          navController = navController,
          sharedViewModel = sharedViewModel,
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