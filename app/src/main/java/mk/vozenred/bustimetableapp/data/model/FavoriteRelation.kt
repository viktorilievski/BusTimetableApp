package mk.vozenred.bustimetableapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import mk.vozenred.bustimetableapp.util.Constants.FAVORITE_RELATIONS_TABLE

@Entity(tableName = FAVORITE_RELATIONS_TABLE)
data class FavoriteRelation(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val companyName: String,
  val startPoint: String,
  val endPoint: String,
  val departureTime: String,
  val arrivalTime: String,
  val note: String
)