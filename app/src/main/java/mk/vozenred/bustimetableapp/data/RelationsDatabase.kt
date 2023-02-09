package mk.vozenred.bustimetableapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import mk.vozenred.bustimetableapp.data.model.FavoriteRelation
import mk.vozenred.bustimetableapp.data.model.Relation

@Database(entities = [Relation::class, FavoriteRelation::class], version = 2, exportSchema = false)
abstract class RelationsDatabase : RoomDatabase() {
  abstract fun relationsDao(): RelationsDao
  abstract fun favoritesDao(): FavoriteRelationsDao
}