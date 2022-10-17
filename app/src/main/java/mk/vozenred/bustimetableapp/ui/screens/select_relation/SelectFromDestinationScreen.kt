package mk.vozenred.bustimetableapp.ui.screens.select_relation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.ui.screens.select_relation.composables.PointListRowItemComposable
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel

@Composable
fun SelectFromDestinationScreen(
    sharedViewModel: SharedViewModel,
    navigateToSearchScreen: (shouldClearBackstack: Boolean) -> Unit
) {
    val startPoints by sharedViewModel.startPoints
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllStartPoints()
    }
    Scaffold(
        topBar = {},
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        content = {
            LazyColumn(modifier = Modifier.padding(it)) {
                items(startPoints) { startPoint ->
                    PointListRowItemComposable(pointName = startPoint) {
                        sharedViewModel.setStartPoint(startPoint)
                        sharedViewModel.clearEndPoint()
                        navigateToSearchScreen(true)
                    }
                    Divider(color = Color.Black, thickness = 5.dp)
                }
            }
        },
        bottomBar = {}
    )
}