package mk.vozenred.bustimetableapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mk.vozenred.bustimetableapp.data.model.Relation

@Dao
interface RelationsDao {

  @Query("SELECT * FROM relations_table WHERE id = :relationId")
  fun getRelation(relationId: Int): Flow<Relation>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun addRelation(relation: Relation)

  @Query("SELECT DISTINCT startPoint FROM relations_table ORDER BY startPoint ASC")
  fun getAllStartingPointsAlphaSorted(): Flow<List<String>>

  @Query("SELECT DISTINCT startPoint FROM relations_table ORDER BY startPoint DESC")
  fun getAllStartingPointsAlphaSortedInv(): Flow<List<String>>

  @Query("SELECT DISTINCT endPoint FROM relations_table WHERE startPoint LIKE :startPointSelected ORDER BY endPoint ASC")
  fun getAllEndPointsAlphaSorted(startPointSelected: String): Flow<List<String>>

  @Query("SELECT DISTINCT endPoint FROM relations_table WHERE startPoint LIKE :startPointSelected ORDER BY endPoint DESC")
  fun getAllEndPointsAlphaSortedInv(startPointSelected: String): Flow<List<String>>

  @Query("SELECT DISTINCT endPoint FROM relations_table WHERE startPoint LIKE :startPointSelected ORDER BY endPoint ASC")
  fun getEndPointsForSelected(startPointSelected: String): Flow<List<String>>

  @Query("SELECT DISTINCT companyName FROM relations_table WHERE startPoint LIKE :starPoint AND endPoint LIKE :endPoint ORDER BY companyName ASC")
  fun getCompaniesForRelation(starPoint: String, endPoint: String): Flow<List<String>>

  @Query("SELECT * FROM relations_table WHERE startPoint LIKE :startPoint AND endPoint LIKE :endPoint AND (companyName LIKE :companyName OR :companyName is null) AND (departureTime > :currentTime OR :currentTime is null) ORDER BY departureTime ASC")
  fun getRelationsForCompany(
    startPoint: String,
    endPoint: String,
    companyName: String?,
    currentTime: String?
  ): List<Relation>

  @Query("UPDATE relations_table SET isRelationFavorite = :isRelationFavorite WHERE id = :relationId")
  suspend fun setRelationFavoriteStatus(relationId: Int, isRelationFavorite: Boolean)

  @Query("SELECT * FROM relations_table WHERE isRelationFavorite == 1")
  fun getFavoriteRelations(): List<Relation>

  @Query("SELECT DISTINCT startPoint FROM relations_table GROUP BY startPoint ORDER BY COUNT(startPoint) DESC")
  fun getStartPointsMaxRelationsSorted(): Flow<List<String>>

  @Query("SELECT DISTINCT endPoint FROM relations_table WHERE startPoint LIKE :startPointSelected GROUP BY endPoint ORDER BY COUNT(endPoint) DESC")
  fun getEndPointsMaxRelationsSorted(startPointSelected: String): Flow<List<String>>

  @Query("SELECT DISTINCT startPoint FROM relations_table GROUP BY startPoint ORDER BY COUNT(startPoint) ASC")
  fun getStartPointsMinRelationsSorted(): Flow<List<String>>

  @Query("SELECT DISTINCT endPoint FROM relations_table WHERE startPoint LIKE :startPointSelected GROUP BY endPoint ORDER BY COUNT(endPoint) ASC")
  fun getEndPointsMinRelationsSorted(startPointSelected: String): Flow<List<String>>
}