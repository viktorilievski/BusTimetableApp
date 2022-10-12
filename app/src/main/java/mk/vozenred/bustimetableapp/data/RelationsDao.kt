package mk.vozenred.bustimetableapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mk.vozenred.bustimetableapp.data.model.Relation

@Dao
interface RelationsDao {

    @Query("SELECT * FROM relations_table ORDER BY departureTime ASC")
    fun getAllRelations(): Flow<List<Relation>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRelation(relation: Relation)

    @Query(
        "SELECT * " +
                "FROM relations_table " +
                "WHERE startPoint LIKE :departure AND endPoint LIKE :arrival " +
                "ORDER BY departureTime ASC"
    )
    fun getRelations(departure: String, arrival: String): Flow<List<Relation>>

    @Query("DELETE FROM relations_table")
    suspend fun deleteAllRelations()

    @Query("SELECT DISTINCT startPoint FROM relations_table ORDER BY startPoint ASC")
    fun getAllStartingPoints(): Flow<List<String>>

    @Query("SELECT DISTINCT endPoint FROM relations_table")
    fun getAllEndPoints(): Flow<List<String>>

    @Query("SELECT DISTINCT endPoint FROM relations_table WHERE startPoint LIKE :startPointSelected ORDER BY endPoint ASC")
    fun getEndPointsForSelected(startPointSelected: String): Flow<List<String>>

    @Query("SELECT DISTINCT companyName FROM relations_table WHERE startPoint LIKE :starPoint AND endPoint LIKE :endPoint ORDER BY companyName ASC")
    fun getCompaniesForRelation(starPoint: String, endPoint: String): Flow<List<String>>

    @Query("SELECT * FROM relations_table WHERE startPoint LIKE :startPoint AND endPoint LIKE :endPoint AND companyName LIKE :companyName ORDER BY departureTime ASC")
    fun getRelationsForCompany(
        startPoint: String,
        endPoint: String,
        companyName: String
    ): Flow<List<Relation>>
}