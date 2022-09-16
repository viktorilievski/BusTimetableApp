package mk.vozenred.bustimetableapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mk.vozenred.bustimetableapp.data.model.FavoriteRelation
import mk.vozenred.bustimetableapp.data.model.Relation

@Dao
interface FavoriteRelationsDao {

    @Query("SELECT * FROM favorite_relations_table ORDER BY departureTime ASC")
    fun getAllFavoriteRelations(): Flow<List<FavoriteRelation>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteRelation(relation: Relation)
}