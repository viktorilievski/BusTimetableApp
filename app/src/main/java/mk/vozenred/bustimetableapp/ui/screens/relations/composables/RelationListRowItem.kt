package mk.vozenred.bustimetableapp.ui.screens.relations.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.data.model.Relation
import mk.vozenred.bustimetableapp.ui.theme.*

@Composable
fun RelationListRowItem(
  relation: Relation,
  onReportRelationClicked: (Int) -> Unit,
  onFavoriteClicked: (Int, Boolean) -> Unit
) {

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(LARGEST_PADDING),
    shape = RoundedCornerShape(CARD_CORNER_RADIUS),
    backgroundColor = MaterialTheme.colors.accentColor,
    elevation = CARD_ELEVATION
  ) {
    Row(
      Modifier
        .padding(horizontal = CARD_CONTENT_PADDING / 2, vertical = LARGE_PADDING)
    ) {
      Column(verticalArrangement = Arrangement.SpaceBetween) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = LARGEST_PADDING),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(6f)
          ) {
            Text(
              text = relation.companyName,
              fontSize = TEXT_SIZE_LARGE,
            )
          }
          Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.weight(1f)
          ) {
            IconButton(onClick = { onFavoriteClicked(relation.id, !relation.isRelationFavorite) }) {
              if (relation.isRelationFavorite) {
                Icon(
                  imageVector = Icons.Filled.Favorite,
                  contentDescription = stringResource(R.string.report_relation_icon),
                )
              } else {
                Icon(
                  imageVector = Icons.Filled.FavoriteBorder,
                  contentDescription = stringResource(R.string.report_relation_icon),
                )
              }
            }
          }
        }
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          RelationPointWithIcon(
            point = relation.startPoint,
            painterResourceId = R.drawable.ic_start_point_relation2
          )
          Text(
            text = relation.departureTime,
            fontSize = TEXT_SIZE_MEDIUM,
          )
        }
        Row(
        ) {
          Image(
            painter = painterResource(id = R.drawable.ic_between_points_relation),
            contentDescription = stringResource(R.string.between_relations_icon),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.iconColor)
          )
        }
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          RelationPointWithIcon(
            point = relation.endPoint,
            painterResourceId = R.drawable.ic_end_point_relation
          )
          Text(
            text = relation.arrivalTime,
            fontSize = TEXT_SIZE_MEDIUM,
          )
        }
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = LARGEST_PADDING),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            modifier = Modifier
              .weight(6f)
          ) {
            Image(
              painter = painterResource(id = R.drawable.ic_info_outlined),
              contentDescription = stringResource(R.string.note_info_icon),
              colorFilter = ColorFilter.tint(MaterialTheme.colors.iconColor)
            )
            Text(
              modifier = Modifier.padding(start = SMALL_PADDING),
              text = relation.note,
            )
          }
          Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(onClick = { onReportRelationClicked(relation.id) }) {
              Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = stringResource(R.string.report_relation_icon)
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun RelationPointWithIcon(point: String, painterResourceId: Int) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Image(
      painter = painterResource(id = painterResourceId),
      contentDescription = stringResource(R.string.start_point_relation_icon),
      colorFilter = ColorFilter.tint(MaterialTheme.colors.complementaryColor)
    )
    Text(
      text = point,
      modifier = Modifier
        .padding(start = SMALL_PADDING),
    )
  }
}

@Composable
@Preview
fun RelationPointWithIconPreview() {
  RelationPointWithIcon(point = "Скопје", painterResourceId = R.drawable.ic_start_point_relation2)
}

@Composable
@Preview
fun RelationListRowItemPreview() {
  BusTimetableAppTheme(darkTheme = true) {
    RelationListRowItem(
      relation = Relation(
        companyName = "МАМЛИ - ТРАВЕЛ",
        startPoint = "Скопје",
        endPoint = "Куманово",
        departureTime = "11:00",
        arrivalTime = "12:00",
        note = "This is a note",
        isRelationFavorite = false
      ),
      onReportRelationClicked = {},
      onFavoriteClicked = { _, _ -> }
    )
  }
}