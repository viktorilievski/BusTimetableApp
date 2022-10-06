package mk.vozenred.bustimetableapp.ui.screens.relations.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.data.model.Relation
import mk.vozenred.bustimetableapp.ui.theme.LARGE_PADDING

@Composable
fun RelationListRowItem(
    relation: Relation
) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, Color.Red)
    ) {
        Column(
            modifier = Modifier
                .background(Color.Gray)
                .padding(26.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = LARGE_PADDING, bottom = LARGE_PADDING)
            ) {
                Text(text = relation.companyName)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = relation.startPoint)
                Text(text = relation.departureTime)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = relation.endPoint)
                Text(text = relation.arrivalTime)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = LARGE_PADDING, bottom = LARGE_PADDING)
            ) {
                Text(text = relation.note)
            }

        }
    }
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