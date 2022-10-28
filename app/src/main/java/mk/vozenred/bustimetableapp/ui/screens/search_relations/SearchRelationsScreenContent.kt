package mk.vozenred.bustimetableapp.ui.screens.search_relations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.SelectRelationField
import mk.vozenred.bustimetableapp.ui.theme.*

@Composable
fun SearchRelationsScreenContent(
    startPoint: String,
    endPoint: String,
    paddingValue: PaddingValues,
    navigateToStartDestinationScreen: () -> Unit,
    navigateToEndDestinationScreen: () -> Unit,
    navigateToRelationsScreen: () -> Unit
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
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
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
            }
            Icon(
                modifier = Modifier.padding(horizontal = MEDIUM_PADDING),
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = stringResource(id = R.string.forward_arrow_icon)
            )
            Column(
                modifier = Modifier.weight(0.3f)
            ) {
                SelectRelationField(
                    label = "До:",
                    selectedPoint = endPoint,
                    onFieldClick = navigateToEndDestinationScreen,
                    isStartPointSelected = isStartPointSelected
                )
            }
        }
    }
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(LARGEST_PADDING)
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

@Composable
@Preview
fun SearchRelationsScreenContentPreview() {
    SearchRelationsScreenContent(
        startPoint = "Скопје",
        endPoint = "Куманово",
        paddingValue = PaddingValues(),
        navigateToEndDestinationScreen = {},
        navigateToStartDestinationScreen = {},
        navigateToRelationsScreen = {}
    )
}