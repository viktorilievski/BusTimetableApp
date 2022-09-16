package mk.vozenred.bustimetableapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectRelationField(
    label: String,
    selectedPoint: String,
    onFieldClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, Color.Red),
        onClick = { onFieldClick() }
    ) {
        Column(modifier = Modifier
            .background(Color.Gray)
            .padding(26.dp)
        ) {
            Text(text = label)
            Text(text = selectedPoint)
        }
    }
}

@Composable
@Preview
fun SelectRelationFieldPreview() {
    SelectRelationField(label = "LEAVING FROM", selectedPoint = "Skopje", onFieldClick = {})
}