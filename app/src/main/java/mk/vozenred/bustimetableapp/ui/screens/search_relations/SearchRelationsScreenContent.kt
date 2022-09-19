package mk.vozenred.bustimetableapp.ui.screens.search_relations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.components.SelectRelationField

@Composable
fun SearchRelationsScreenContent(
    startPoint: String,
    endPoint: String,
    navigateToStartDestinationScreen: () -> Unit,
    navigateToEndDestinationScreen: () -> Unit,
    navigateToRelationsScreen: () -> Unit
) {
    val isStartPointSelected = startPoint.isNotEmpty()
    val isEndPointSelected = endPoint.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            SelectRelationField(
                label = "LEAVING FROM",
                selectedPoint = startPoint,
                onFieldClick = navigateToStartDestinationScreen,
                isStartPointSelected = true
            )
            SelectRelationField(
                label = "GOING TO",
                selectedPoint = endPoint,
                onFieldClick = navigateToEndDestinationScreen,
                isStartPointSelected = isStartPointSelected
            )
        Button(
            onClick = { navigateToRelationsScreen() },
            enabled = isEndPointSelected
        ) {
            Text(text = "Search")
        }
    }
}

@Composable
@Preview
fun SearchRelationsScreenContentPreview() {
    SearchRelationsScreenContent(
        startPoint = "Skopje",
        endPoint = "Kumanovo",
        navigateToEndDestinationScreen = {},
        navigateToStartDestinationScreen = {},
        navigateToRelationsScreen = {}
    )
}