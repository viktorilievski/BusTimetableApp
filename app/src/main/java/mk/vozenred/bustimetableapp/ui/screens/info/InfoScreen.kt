package mk.vozenred.bustimetableapp.ui.screens.info

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import mk.vozenred.bustimetableapp.BuildConfig
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.drawer.DrawerContent
import mk.vozenred.bustimetableapp.components.topbars.DrawerTopAppBar
import mk.vozenred.bustimetableapp.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoScreen(
  navigateFromDrawerTo: (String) -> Unit,
) {
  val coroutineScope = rememberCoroutineScope()
  val scaffoldState = rememberScaffoldState()

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      DrawerTopAppBar(title = stringResource(id = R.string.info_screen_title),
        onDrawerIconClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.open()
          }
        })
    },
    modifier = Modifier.fillMaxSize(),
    drawerContent = {
      DrawerContent(
        title = stringResource(id = R.string.info_screen_title),
        onCloseDrawerClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
        },
        navigateFromDrawerTo = { navigateFromDrawerTo(it) }
      )
    },
    content = {
      InfoScreenContent(
        onGitHubImageClick = {
          coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar("GitHub clicked", "github", SnackbarDuration.Short)
          }

        },
        onLinkedInImageClick = {
          coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar("LinkedIn clicked", "linkedin", SnackbarDuration.Short)
          }
        }
      )
    }
  )
}

@Composable
fun InfoScreenContent(
  onGitHubImageClick: () -> Unit,
  onLinkedInImageClick: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colors.background)
      .padding(LARGE_PADDING)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      // todo: header
      Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = stringResource(id = R.string.about_us_image_cd),
        modifier = Modifier.clip(CircleShape)
      )
    }

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .weight(2f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center

    ) {
      // todo: body
      Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
    }

    Column(
      modifier = Modifier
        .weight(1f),
      verticalArrangement = Arrangement.Center
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          modifier = Modifier
            .clickable {
              onGitHubImageClick()
            }
            .size(DRAWABLE_SIZE_MEDIUM),
          painter = painterResource(id = R.drawable.github_logo),
          tint = MaterialTheme.colors.iconColor,
          contentDescription = stringResource(id = R.string.github_logo_cd)
        )
        Icon(
          modifier = Modifier
            .clickable {
              onLinkedInImageClick()
            }
            .size(DRAWABLE_SIZE_MEDIUM),
          painter = painterResource(id = R.drawable.linkedin_logo),
          tint = MaterialTheme.colors.iconColor,
          contentDescription = stringResource(id = R.string.linkedin_logo_cd)
        )
      }
    }
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.End,
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = stringResource(id = R.string.version_name, BuildConfig.VERSION_NAME),
        fontSize = TEXT_SIZE_VERY_SMALL,
        color = Color(0, 0, 0, 80)
      )
    }
  }
}


@Composable
@Preview
fun InfoScreenPreview() {
  BusTimetableAppTheme {
    InfoScreen(
      navigateFromDrawerTo = {}
    )
  }
}
