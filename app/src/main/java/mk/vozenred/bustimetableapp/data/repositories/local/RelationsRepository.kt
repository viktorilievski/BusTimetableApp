package mk.vozenred.bustimetableapp.data.repositories.local

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import mk.vozenred.bustimetableapp.data.RelationsDao
import mk.vozenred.bustimetableapp.data.model.Relation
import javax.inject.Inject

@ViewModelScoped
class RelationsRepository @Inject constructor(
  private val relationsDao: RelationsDao
) {

  fun getRelations(departure: String, arrival: String, companyName: String?, currentTime: String?): List<Relation> {
    return relationsDao.getRelationsForCompany(departure, arrival, companyName, currentTime)
  }

  fun getFavoriteRelations(): List<Relation> {
    return relationsDao.getFavoriteRelations()
  }

  suspend fun removeRelationFromFavorite(relationId: Int) {
    relationsDao.setRelationFavoriteStatus(relationId = relationId, isRelationFavorite = false)
  }

  fun getRelation(relationId: Int): Flow<Relation> {
    return relationsDao.getRelation(relationId)
  }

  suspend fun addRelation(relation: Relation) {
    relationsDao.addRelation(relation = relation)
  }

  fun getStartAllPointsAlphaSorted(): Flow<List<String>> {
    return relationsDao.getAllStartingPointsAlphaSorted()
  }

  fun getStartAllPointsAlphaSortedInv(): Flow<List<String>> {
    return relationsDao.getAllStartingPointsAlphaSortedInv()
  }

  fun getStartPointsMaxRelationsSorted(): Flow<List<String>> {
    return relationsDao.getStartPointsMaxRelationsSorted()
  }

  fun getStartPointsMinRelationsSorted(): Flow<List<String>> {
    return relationsDao.getStartPointsMinRelationsSorted()
  }

  fun getAllEndPointsAlphaSorted(startPointSelected: String): Flow<List<String>> {
    return relationsDao.getAllEndPointsAlphaSorted(startPointSelected)
  }

  fun getAllEndPointsAlphaSortedInv(startPointSelected: String): Flow<List<String>> {
    return relationsDao.getAllEndPointsAlphaSortedInv(startPointSelected)
  }

  fun getEndPointsMaxRelationsSorted(startPointSelected: String): Flow<List<String>> {
    return relationsDao.getEndPointsMaxRelationsSorted(startPointSelected)
  }

  fun getEndPointsMinRelationsSorted(startPointSelected: String): Flow<List<String>> {
    return relationsDao.getEndPointsMinRelationsSorted(startPointSelected)
  }

  suspend fun setRelationFavoriteStatus(relationId: Int, isRelationFavorite: Boolean) {
    relationsDao.setRelationFavoriteStatus(
      relationId = relationId,
      isRelationFavorite = isRelationFavorite
    )
  }

  fun getEndPointsForSelectedStartPoint(selectedStartPoint: String): Flow<List<String>> {
    return relationsDao.getEndPointsForSelected(selectedStartPoint)
  }

  fun getCompaniesForRelation(startPoint: String, endPoint: String): Flow<List<String>> {
    return relationsDao.getCompaniesForRelation(startPoint, endPoint)
  }
}