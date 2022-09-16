package mk.vozenred.bustimetableapp.ui.screens.select_relation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.ui.screens.select_relation.composables.PointListRowItemComposable
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel

@Composable
fun SelectToDestinationScreen(
    sharedViewModel: SharedViewModel,
    navigateToSearchScreen: () -> Unit
) {

    val endPoints by sharedViewModel.endPoints

    LaunchedEffect(key1 = true) {
        sharedViewModel.getEndPointsForSelectedStartPoint()
    }

    Scaffold(
        topBar = {},
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        content = {
            LazyColumn() {
                items(endPoints) {
                    PointListRowItemComposable(pointName = it) {
                        sharedViewModel.setEndPoint(it)
                        navigateToSearchScreen()
                    }
                    Divider(color = Color.Black, thickness = 5.dp)
                }
            }
        },
        bottomBar = {}
    )
}