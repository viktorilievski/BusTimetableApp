package mk.vozenred.bustimetableapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.ui.theme.LARGEST_PADDING
import mk.vozenred.bustimetableapp.ui.theme.LARGE_PADDING

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
    if (isStartPointSelected) {
        textColor = Color.Black
        backgroundColor = Color.LightGray
    } else {
        textColor = Color.Gray
        backgroundColor = Color.LightGray
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
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