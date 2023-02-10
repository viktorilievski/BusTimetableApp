package mk.vozenred.bustimetableapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import mk.vozenred.bustimetableapp.util.Constants.RELATIONS_DATABASE_TABLE

@Entity(tableName = RELATIONS_DATABASE_TABLE)
data class Relation(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val companyName: String = "",
  val startPoint: String = "",
  val endPoint: String = "",
  val departureTime: String = "",
  val arrivalTime: String = "",
  val note: String = "",
  val isRelationFavorite: Boolean = false
) {
  override fun toString(): String {
    return "Релација:\n" +
        "Име на компанија: ${companyName}\n" +
        "Почетна точка: ${startPoint}\n" +
        "Крајна точка: ${endPoint}\n" +
        "Време на поаѓање: ${departureTime}\n" +
        "Време на пристигање: ${arrivalTime}\n" +
        "Режин на возење: ${note}\n"
  }
}