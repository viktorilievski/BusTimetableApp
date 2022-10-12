package mk.vozenred.bustimetableapp.ui.screens.relations

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import mk.vozenred.bustimetableapp.components.topbars.FilterDropdownMenu
import mk.vozenred.bustimetableapp.components.topbars.RelationsTopAppBar
import mk.vozenred.bustimetableapp.ui.screens.relations.composables.RelationListRowItem
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel

@Composable
fun RelationsScreen(
    sharedViewModel: SharedViewModel
) {
    val relations by sharedViewModel.relations.collectAsState()
    val selectedFromRelation = sharedViewModel.startPointSelected.value
    val selectedToRelation = sharedViewModel.endPointSelected.value
    val companiesList = sharedViewModel.companiesForRelation.value
    val selectedCompany = sharedViewModel.selectedCompany.value

    var filterExpanded by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        sharedViewModel.getRelations()
        sharedViewModel.getCompaniesForSelectedRelation()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Row {
                if (companiesList.size == 1) {
                    RelationsTopAppBar(
                        title = "$selectedFromRelation - $selectedToRelation",
                        filterButtonEnabled = false,
                        onFilterClicked = {},
                        onDrawerIconClick = {}
                    )
                } else {
                    RelationsTopAppBar(
                        title = "$selectedFromRelation - $selectedToRelation",
                        filterButtonEnabled = true,
                        onFilterClicked = {
                            filterExpanded = true
                        },
                        onDrawerIconClick = {}
                    )
                    FilterDropdownMenu(
                        expanded = filterExpanded,
                        onDismissRequested = { filterExpanded = false },
                        onMenuItemClicked = { companyNameClicked ->
                            sharedViewModel.getRelationsForSelectedCompany(companyNameClicked)
                            sharedViewModel.setSelectedCompany(companyNameClicked)
                            filterExpanded = false
                        },
                        onShowAllRelationsClicked = {
                            sharedViewModel.getRelations()
                            filterExpanded = false
                        },
                        companies = companiesList,
                        selectedCompany = selectedCompany
                    )
                }

            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(relations) { relation ->
                RelationListRowItem(
                    relation = relation,
                    onFavoriteButtonClick = { relationId ->
                        sharedViewModel.setRelationFavoriteStatus(
                            relationId = relationId,
                            isRelationFavorite = !relation.isRelationFavorite
                        )
                    }
                )
            }
        }
    }
}