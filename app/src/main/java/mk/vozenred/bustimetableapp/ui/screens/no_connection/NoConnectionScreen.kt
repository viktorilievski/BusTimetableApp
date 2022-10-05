package mk.vozenred.bustimetableapp.ui.screens.no_connection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.ui.theme.DRAWABLE_SIZE_BIG
import mk.vozenred.bustimetableapp.ui.theme.LARGE_PADDING
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel

@Composable
fun NoConnectionScreen(
    navigateToSplashScreen: () -> Unit,
    navigateToSearchScreen: () -> Unit,
    splashScreenViewModel: SplashScreenViewModel
) {
    val localDbVersionIsPresent =
        splashScreenViewModel.dataStoreValue.collectAsState().value != null

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        NoConnectionScreenContent(
            onRetryButtonClick = {
                if (splashScreenViewModel.networkStatus.value) {
                    navigateToSplashScreen()
                }
            },
            onContinueButtonClick = {
                navigateToSearchScreen()
            },
            localDbVersionIsPresent = localDbVersionIsPresent
        )
    }
}

@Composable
fun NoConnectionScreenContent(
    onRetryButtonClick: () -> Unit,
    onContinueButtonClick: () -> Unit,
    localDbVersionIsPresent: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(LARGE_PADDING)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_no_connection),
            contentDescription = "No Connection",
            modifier = Modifier.size(DRAWABLE_SIZE_BIG)
        )
        var noteText = ""
        noteText = if (localDbVersionIsPresent) {
            stringResource(id = R.string.no_connection_with_db)
        } else {
            stringResource(id = R.string.no_connection_first_time)
        }
        Text(text = noteText, textAlign = TextAlign.Center)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { onRetryButtonClick() }
            ) {
                Text(text = stringResource(R.string.retry_connection_button_text))
            }
            if (localDbVersionIsPresent) {
                Button(onClick = { onContinueButtonClick() }) {
                    Text(text = "Offline mode")
                }
            }
        }

    }
}

@Preview
@Composable
fun NoConnectionScreenContentPreview() {
    NoConnectionScreenContent(
        onRetryButtonClick = {},
        onContinueButtonClick = {},
        localDbVersionIsPresent = false
    )
}