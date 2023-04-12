package mk.vozenred.bustimetableapp.components.drawer

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerBodyItem(
  val icon: ImageVector,
  val title: String,
  val onItemClick: () -> Unit
)