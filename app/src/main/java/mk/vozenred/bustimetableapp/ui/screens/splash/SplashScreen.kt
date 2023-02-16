package mk.vozenred.bustimetableapp.ui.screens.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.ui.theme.BusTimetableAppTheme
import mk.vozenred.bustimetableapp.ui.theme.LARGEST_PADDING
import mk.vozenred.bustimetableapp.ui.theme.SPLASH_SCREEN_LOGO_SIZE
import mk.vozenred.bustimetableapp.ui.viewmodels.LoadingState
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel
import mk.vozenred.bustimetableapp.util.Constants.SPLASH_SCREEN_DELAY


@Composable
fun SplashScreen(
  navigateToSearchScreen: () -> Unit,
  navigateToNoConnectionScreen: () -> Unit,
  splashScreenViewModel: SplashScreenViewModel
) {
  val localDbVersion by splashScreenViewModel.dataStoreDatabaseVersion.collectAsState()

  LaunchedEffect(key1 = true) {
    if (splashScreenViewModel.networkStatus.value) {
      delay(SPLASH_SCREEN_DELAY)
      val firestoreDbVersion = splashScreenViewModel.readFirestoreDatabaseVersion()
      if (localDbVersion == null || localDbVersion != firestoreDbVersion) {
        splashScreenViewModel.fetchAndStoreRelationsToLocalDb()
      } else {
        splashScreenViewModel.setLoadingState(LoadingState.Success)
      }
    } else {
      splashScreenViewModel.setLoadingState(LoadingState.Failed)
    }
    splashScreenViewModel.loading.observeForever {
      when (it) {
        LoadingState.Failed -> {
          Log.e(
            "SplashScreen",
            "Failed loading data. Please check your internet connection!"
          )
          navigateToNoConnectionScreen()
        }
        LoadingState.Loading -> {
          Log.d("SplashScreen", "Loading data from Firestore!")
        }
        LoadingState.Success -> {
          Log.d("SplashScreen", "Data loaded successfully!")
          navigateToSearchScreen()
        }
      }
    }
  }
  SplashScreenContent()

}

@Composable
fun SplashScreenContent() {
  Column(
    modifier = Modifier
      .fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      modifier = Modifier
        .size(SPLASH_SCREEN_LOGO_SIZE),
      painter = painterResource(id = R.drawable.ic_logo),
      contentDescription = stringResource(R.string.application_logo),
      colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
    )
    CircularProgressIndicator(
      modifier = Modifier
        .padding(top = LARGEST_PADDING)
    )
  }
}

@Composable
@Preview
fun SplashScreenContentPreview() {
  BusTimetableAppTheme {
    SplashScreenContent()
  }
}
