package mk.vozenred.bustimetableapp.ui.screens.search_relations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.SelectRelationField
import mk.vozenred.bustimetableapp.ui.theme.LARGEST_PADDING
import mk.vozenred.bustimetableapp.ui.theme.MEDIUM_PADDING
import mk.vozenred.bustimetableapp.ui.theme.TEXT_SIZE_MEDIUM

@Composable
fun SearchRelationsScreenContent(
  startPoint: String,
  endPoint: String,
  paddingValue: PaddingValues,
  navigateToStartDestinationScreen: () -> Unit,
  navigateToEndDestinationScreen: () -> Unit,
  navigateToRelationsScreen: () -> Unit,
  onSwapCitiesIconClicked:() -> Unit
) {
  val isStartPointSelected = startPoint.isNotEmpty()
  val isEndPointSelected = endPoint.isNotEmpty()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colors.background)
      .padding(LARGEST_PADDING),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceEvenly
    ) {
      Text(
        text = stringResource(R.string.search_screen_title),
        textAlign = TextAlign.Center
      )
      Text(
        text = stringResource(R.string.search_screen_body),
        textAlign = TextAlign.Center
      )
    }
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .weight(3f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceEvenly
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(
          modifier = Modifier.weight(0.3f)
        ) {
          SelectRelationField(
            label = "Од:",
            selectedPoint = startPoint,
            onFieldClick = navigateToStartDestinationScreen,
            isStartPointSelected = true
          )
          Divider(modifier = Modifier.padding(vertical = MEDIUM_PADDING))
          SelectRelationField(
            label = "До:",
            selectedPoint = endPoint,
            onFieldClick = navigateToEndDestinationScreen,
            isStartPointSelected = isStartPointSelected
          )
        }
        IconButton(
          onClick = { onSwapCitiesIconClicked() },
          enabled = isEndPointSelected,
          modifier = Modifier.padding(start = 10.dp)
        ) {
          Icon(painter = painterResource(id = R.drawable.ic_baseline_swap_vert_24), contentDescription = "")
        }
      }
    }
    Column(
      verticalArrangement = Arrangement.Bottom,
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      Row() {
        Button(
          modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(bottom = LARGEST_PADDING),
          onClick = { navigateToRelationsScreen() },
          enabled = isEndPointSelected,
          shape = RoundedCornerShape(24.dp)
        ) {
          Text(
            text = "БАРАЈ",
            fontSize = TEXT_SIZE_MEDIUM
          )
        }
      }
    }
  }
}

  @Composable
  @Preview
  fun SearchRelationsScreenContentPreview() {
    SearchRelationsScreenContent(
      startPoint = "Скопје",
      endPoint = "Куманово",
      paddingValue = PaddingValues(),
      navigateToEndDestinationScreen = {},
      navigateToStartDestinationScreen = {},
      navigateToRelationsScreen = {},
      onSwapCitiesIconClicked = {}
    )
  }