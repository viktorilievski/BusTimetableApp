package mk.vozenred.bustimetableapp.ui.screens.splash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import mk.vozenred.bustimetableapp.ui.viewmodels.LoadingState
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel
import mk.vozenred.bustimetableapp.util.Constants.SPLASH_SCREEN_DELAY


@Composable
fun SplashScreen(
    navigateToSearchScreen: () -> Unit,
    splashScreenViewModel: SplashScreenViewModel
) {
    val localDbVersion by splashScreenViewModel.dataStoreValue.collectAsState()

    //todo: change screen info depending on network status
    var backgroundColor by remember {
        mutableStateOf(Color.Green)
    }
    var textInfo by remember {
        mutableStateOf("")
    }

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
                    backgroundColor = Color.Red
                    textInfo = "Internet connection problem"
                }
                LoadingState.Loading -> {
                    Log.d("SplashScreen", "Loading data from Firestore!")
                    // set loading widget TRUE
                    backgroundColor = Color.Blue
                    textInfo = "Loading data..."
                }
                LoadingState.Success -> {
                    Log.d("SplashScreen", "Data loaded successfully!")
                    backgroundColor = Color.Green
                    textInfo = "Data loaded successfully!"
                    navigateToSearchScreen()
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = textInfo)
        }
    }
}