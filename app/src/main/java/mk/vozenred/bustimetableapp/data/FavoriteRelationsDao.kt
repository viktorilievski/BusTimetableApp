package mk.vozenred.bustimetableapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mk.vozenred.bustimetableapp.data.model.FavoriteRelation
import mk.vozenred.bustimetableapp.data.model.Relation

//todo: to be implemented in near future
@Dao
interface FavoriteRelationsDao {

    @Query("SELECT * FROM favorite_relations_table ORDER BY departureTime ASC")
    fun getAllFavoriteRelations(): Flow<List<FavoriteRelation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteRelation(relation: FavoriteRelation)

    @Query("DELETE FROM favorite_relations_table WHERE id = :relationId")
    suspend fun removeRelationFromFavorite(relationId: Int)

    @Query("SELECT * FROM favorite_relations_table WHERE id = :relationId")
    fun fetchFavoriteRelation(relationId: Int): FavoriteRelation?
}