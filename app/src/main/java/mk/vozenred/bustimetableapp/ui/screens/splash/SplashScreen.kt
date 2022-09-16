package mk.vozenred.bustimetableapp.ui.screens.splash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import mk.vozenred.bustimetableapp.ui.theme.splashScreenBackgroundColor
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel
import mk.vozenred.bustimetableapp.util.Constants.SPLASH_SCREEN_DELAY


@Composable
fun SplashScreen(
    navigateToSearchScreen: () -> Unit,
    splashScreenViewModel: SplashScreenViewModel
) {
    val localDbVersion by splashScreenViewModel.dataStoreValue.collectAsState()

    LaunchedEffect(key1 = true) {
        val firestoreDbVersion = splashScreenViewModel.readFirestoreDatabaseVersion()
        if (localDbVersion == null || localDbVersion != firestoreDbVersion) {
            splashScreenViewModel.fetchAndStoreRelationsToLocalDb()
        } else {
            splashScreenViewModel.setLoadingState(false)
        }
        delay(SPLASH_SCREEN_DELAY)
        splashScreenViewModel.loading.observeForever {
            when (it) {
                true -> Log.d("SplashScreen", "Loading data from Firestore!")
                false -> {
                    Log.d("SplashScreen", "Data loaded successfully!")
                    navigateToSearchScreen()
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.splashScreenBackgroundColor)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Splash Screen: local db version $localDbVersion")
        }
    }
}
