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
import mk.vozenred.bustimetableapp.ui.theme.BusTimetableAppTheme
import mk.vozenred.bustimetableapp.ui.theme.DRAWABLE_SIZE_BIG
import mk.vozenred.bustimetableapp.ui.theme.LARGEST_PADDING
import mk.vozenred.bustimetableapp.ui.theme.iconColor
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel

@Composable
fun NoConnectionScreen(
  navigateToSplashScreen: () -> Unit,
  navigateToSearchScreen: () -> Unit,
  splashScreenViewModel: SplashScreenViewModel
) {
  val localDbVersionIsPresent =
    splashScreenViewModel.dataStoreDatabaseVersion.collectAsState().value != null

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

    val noteText = if (localDbVersionIsPresent) {
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
        Icon(
          imageVector = Icons.Filled.Refresh,
          contentDescription = stringResource(R.string.refresh_icon)
        )
      }
      if (localDbVersionIsPresent) {
        Button(onClick = { onContinueButtonClick() }) {
          Text(text = stringResource(R.string.offline_mode_button_text))
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