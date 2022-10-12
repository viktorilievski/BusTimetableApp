package mk.vozenred.bustimetableapp.ui.screens.relations.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.data.model.Relation
import mk.vozenred.bustimetableapp.ui.theme.*

@Composable
fun RelationListRowItem(
    relation: Relation
) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(CARD_CORNER_RADIUS),
        border = BorderStroke(2.dp, MaterialTheme.colors.cardBorderColor)
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colors.cardBackgroundColor)
                .padding(horizontal = LARGE_PADDING, vertical = LARGE_PADDING)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.3f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_bus_standard),
                    contentDescription = stringResource(
                        R.string.standard_bus_icon
                    ),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.relationBetweenIconColor),
                    modifier = Modifier.size(DRAWABLE_SIZE_MEDIUM)
                )
            }
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Row(
                    modifier = Modifier
                        .padding(bottom = LARGE_PADDING)
                ) {
                    Text(text = relation.companyName, fontSize = TEXT_SIZE_LARGE)
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
                        fontSize = TEXT_SIZE_MEDIUM
                    )
                }
                Row(
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_between_points_relation),
                        contentDescription = stringResource(R.string.between_relations_icon),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.relationBetweenIconColor)
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
                        fontSize = TEXT_SIZE_MEDIUM
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = LARGE_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_info_outlined),
                        contentDescription = stringResource(R.string.note_info_icon)
                    )
                    Text(
                        modifier = Modifier.padding(start = SMALL_PADDING),
                        text = relation.note
                    )
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
            colorFilter = ColorFilter.tint(MaterialTheme.colors.accentColor)
        )
        Text(
            text = point, modifier = Modifier
                .padding(start = SMALL_PADDING)
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
    RelationListRowItem(
        relation = Relation(
            companyName = "Штуз",
            startPoint = "Скопје",
            endPoint = "Куманово",
            departureTime = "11:00",
            arrivalTime = "12:00",
            note = "This is a note"
        )
    )
}