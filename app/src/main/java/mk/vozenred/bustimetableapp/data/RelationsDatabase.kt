package mk.vozenred.bustimetableapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import mk.vozenred.bustimetableapp.data.model.Relation

@Database(entities = [Relation::class], version = 3, exportSchema = false)
abstract class RelationsDatabase : RoomDatabase() {
  abstract fun relationsDao(): RelationsDao
}