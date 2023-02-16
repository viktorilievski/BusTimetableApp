package mk.vozenred.bustimetableapp.ui.screens.no_connection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.ui.theme.*
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel

@Composable
fun NoConnectionScreen(
  navigateToSplashScreen: () -> Unit,
  navigateToSearchScreen: () -> Unit,
  splashScreenViewModel: SplashScreenViewModel
) {
  val localDbVersionIsPresent =
    splashScreenViewModel.dataStoreValue.collectAsState().value != null

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(LARGEST_PADDING),
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
  ) {
    Image(
      painter = painterResource(id = R.drawable.ic_no_connection),
      contentDescription = "No Connection",
      modifier = Modifier.size(DRAWABLE_SIZE_BIG),
      colorFilter = ColorFilter.tint(MaterialTheme.colors.iconColor)
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
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
        Icon(imageVector = Icons.Filled.Refresh, contentDescription = "")
      }
      if (localDbVersionIsPresent) {
        Button(onClick = { onContinueButtonClick() }) {
          Text(text = "Offline mode")
          Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
          Icon(painter = painterResource(id = R.drawable.ic_no_connection), contentDescription = "")
        }
      }
    }

  }
}

@Preview
@Composable
fun NoConnectionScreenContentPreview() {
  BusTimetableAppTheme {
    NoConnectionScreenContent(
      onRetryButtonClick = {},
      onContinueButtonClick = {},
      localDbVersionIsPresent = true
    )
  }
}