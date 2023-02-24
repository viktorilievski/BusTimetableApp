package mk.vozenred.bustimetableapp.ui.screens.favorite_relations

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.launch
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.topbars.DrawerContent
import mk.vozenred.bustimetableapp.components.topbars.DrawerTopAppBar
import mk.vozenred.bustimetableapp.data.model.Relation
import mk.vozenred.bustimetableapp.ui.screens.relations.composables.RelationListRowItem
import mk.vozenred.bustimetableapp.ui.theme.CARD_CORNER_RADIUS
import mk.vozenred.bustimetableapp.ui.theme.CARD_ELEVATION
import mk.vozenred.bustimetableapp.ui.theme.LARGE_PADDING
import mk.vozenred.bustimetableapp.ui.theme.MEDIUM_PADDING
import mk.vozenred.bustimetableapp.ui.viewmodels.FavoriteRelationsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteRelationsScreen(
  favoriteRelationsViewModel: FavoriteRelationsViewModel,
  navigateToContactScreen: () -> Unit,
  navigateToSearchScreen: () -> Unit,
  navigateToReportScreen: (Int) -> Unit
) {

  val favoriteRelations by favoriteRelationsViewModel.favoriteRelations.collectAsState(initial = mutableListOf())
  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()

  var showRemoveRelationDialog by remember {
    mutableStateOf(false)
  }

  var favoriteRelationId by remember {
    mutableStateOf(-1)
  }

  LaunchedEffect(key1 = Unit) {
    favoriteRelationsViewModel.getAllFavoriteRelations()
  }

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      DrawerTopAppBar(
        stringResource(R.string.favorite_relations_top_app_bar_title),
        onDrawerIconClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.open()
          }
        }
      )
    },
    modifier = Modifier.fillMaxSize(),
    drawerContent = {
      DrawerContent(
        title = stringResource(id = R.string.favorite_relations_top_app_bar_title),
        onCloseDrawerClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
        },
        navigateToSearchScreen = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
          navigateToSearchScreen()
        },
        navigateToContactScreen = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
          navigateToContactScreen()
        },
        navigateToFavoriteRelationsScreen = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
        }
      )
    }
  ) {
    FavoriteRelationsContent(
      favoriteRelations = favoriteRelations,
      onReportRelationClicked = {
        navigateToReportScreen(it)
      },
      onFavoriteClicked = {
        showRemoveRelationDialog = true
        favoriteRelationId = it
      },
      isDialogShown = showRemoveRelationDialog,
      onDialogRemoveButtonClicked = {
        favoriteRelationsViewModel.removeFavoriteRelation(favoriteRelationId)
        showRemoveRelationDialog = false
      },
      onDialogDismissButtonClicked = {
        showRemoveRelationDialog = false
      }
    )
  }
}

@Composable
fun FavoriteRelationsContent(
  favoriteRelations: List<Relation>,
  onReportRelationClicked: (Int) -> Unit,
  onFavoriteClicked: (Int) -> Unit,
  isDialogShown: Boolean,
  onDialogRemoveButtonClicked: () -> Unit,
  onDialogDismissButtonClicked: () -> Unit,
) {
  if (favoriteRelations.isNotEmpty()) {
    LazyColumn(
      modifier = Modifier.padding()
    ) {
      items(items = favoriteRelations, key = { item: Relation -> item.id }) { relation ->
        RelationListRowItem(
          relation = relation,
          onReportRelationClicked = { relationId ->
            onReportRelationClicked(relationId)
          },
          onFavoriteClicked = { relationId, _ ->
            onFavoriteClicked(relationId)
          }
        )
      }

    }
  } else {
    NoRelationsWidget()
  }
  if (isDialogShown) {
    DialogRemoveFavoriteRelation(
      onRemoveButtonClicked = {
        onDialogRemoveButtonClicked()
      },
      onDismissButtonClicked = {
        onDialogDismissButtonClicked()
      },
      onOutsideDialogClick = {
        onDialogDismissButtonClicked()
      }
    )
  }
}

@Composable
fun NoRelationsWidget() {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(text = stringResource(R.string.no_favorite_relations_text))
  }
}

@Composable
fun DialogRemoveFavoriteRelation(
  onRemoveButtonClicked: () -> Unit,
  onDismissButtonClicked: () -> Unit,
  onOutsideDialogClick: () -> Unit,
) {
  Dialog(
    onDismissRequest = { onOutsideDialogClick() },
  ) {
    Card(
      shape = RoundedCornerShape(CARD_CORNER_RADIUS / 2),
      modifier = Modifier
        .fillMaxWidth(),
      elevation = CARD_ELEVATION
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = LARGE_PADDING)
          .background(MaterialTheme.colors.background)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White.copy(alpha = 0.8F)),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
        ) {
          Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.application_logo),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
          )
        }

        Text(
          text = stringResource(R.string.remove_favorite_relation_dialog_title),
          modifier = Modifier.padding(8.dp), fontSize = 20.sp
        )

        Row(Modifier.padding(top = 10.dp)) {
          OutlinedButton(
            onClick = { onDismissButtonClicked() },
            Modifier
              .fillMaxWidth()
              .padding(
                horizontal = LARGE_PADDING,
                vertical = MEDIUM_PADDING
              )
              .weight(1F)
          ) {
            Text(text = stringResource(R.string.favorite_relation_dialog_cancel))
          }
          Button(
            onClick = { onRemoveButtonClicked() },
            Modifier
              .fillMaxWidth()
              .padding(
                horizontal = LARGE_PADDING,
                vertical = MEDIUM_PADDING
              )
              .weight(1F)
          ) {
            Text(text = stringResource(R.string.favorite_relation_dialog_remove))
          }
        }
      }
    }
  }
}

@Composable
@Preview
fun DialogRemoveFavoriteRelationPreview() {
  DialogRemoveFavoriteRelation(
    onRemoveButtonClicked = {},
    onDismissButtonClicked = {},
    onOutsideDialogClick = {}
  )
}