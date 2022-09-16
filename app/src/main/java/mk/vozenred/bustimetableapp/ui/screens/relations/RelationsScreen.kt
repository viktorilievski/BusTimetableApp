package mk.vozenred.bustimetableapp.ui.screens.relations

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import mk.vozenred.bustimetableapp.ui.screens.relations.composables.RelationListRowItem
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel

@Composable
fun RelationsScreen(
    sharedViewModel: SharedViewModel
) {
    val relations by sharedViewModel.relations.collectAsState()

    LaunchedEffect(key1 = true) {
        sharedViewModel.getRelations()
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn() {
            items(relations) { relation ->
                RelationListRowItem(relation = relation)
            }
        }
    }
}