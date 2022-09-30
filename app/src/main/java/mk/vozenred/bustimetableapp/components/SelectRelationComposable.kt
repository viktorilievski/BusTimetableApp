package mk.vozenred.bustimetableapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectRelationField(
    label: String,
    selectedPoint: String,
    onFieldClick: () -> Unit,
    isStartPointSelected: Boolean
) {
    val textColor: Color
    val backgroundColor: Color
    val borderColor: Color
    if (isStartPointSelected) {
        textColor = Color.Black
        backgroundColor = Color.Gray
        borderColor = Color.Red
    } else {
        textColor = Color.Gray
        backgroundColor = Color.LightGray
        borderColor = Color.Gray
    }
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, borderColor),
        enabled = isStartPointSelected,
        onClick = { onFieldClick() }
    ) {
        Column(
            modifier = Modifier
                .background(backgroundColor)
                .padding(26.dp)
        ) {
            Text(text = label, color = textColor)
            Text(text = selectedPoint, color = textColor)
        }
    }
}

@Composable
@Preview
fun SelectRelationFieldPreview() {
    SelectRelationField(
        label = "LEAVING FROM",
        selectedPoint = "Skopje",
        onFieldClick = {},
        isStartPointSelected = false
    )
}