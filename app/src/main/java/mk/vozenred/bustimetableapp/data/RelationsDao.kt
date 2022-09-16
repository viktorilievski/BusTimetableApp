package mk.vozenred.bustimetableapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mk.vozenred.bustimetableapp.data.model.FavoriteRelation
import mk.vozenred.bustimetableapp.data.model.Relation

@Dao
interface RelationsDao {

    @Query("SELECT * FROM relations_table ORDER BY departureTime ASC")
    fun getAllRelations(): Flow<List<Relation>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRelation(relation: Relation)

    @Query("SELECT * FROM relations_table WHERE startPoint LIKE :departure AND endPoint LIKE :arrival")
    fun getRelations(departure: String, arrival: String): Flow<List<Relation>>

    @Query("DELETE FROM relations_table")
    suspend fun deleteAllRelations()

    @Query("SELECT DISTINCT startPoint FROM relations_table")
    fun getAllStartingPoints(): Flow<List<String>>

    @Query("SELECT DISTINCT endPoint FROM relations_table")
    fun getAllEndPoints(): Flow<List<String>>

    @Query("SELECT DISTINCT endPoint FROM relations_table WHERE startPoint LIKE :startPointSelected")
    fun getEndPointsForSelected(startPointSelected: String): Flow<List<String>>
}