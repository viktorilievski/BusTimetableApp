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

  fun getRelations(departure: String, arrival: String): Flow<List<Relation>> {
    return relationsDao.getRelations(departure, arrival)
  }

  fun getRelation(relationId: Int): Flow<Relation> {
    return relationsDao.getRelation(relationId)
  }

  suspend fun addRelation(relation: Relation) {
    relationsDao.addRelation(relation = relation)
  }

  fun getAllStartingPoints(): Flow<List<String>> {
    return relationsDao.getAllStartingPoints()
  }

  fun getStartPointsForEnteredText(enteredText: String): Flow<List<String>> {
    return relationsDao.getStartPointsForEnteredText(enteredText)
  }

  fun getEndPointsForSelectedStartPoint(selectedStartPoint: String): Flow<List<String>> {
    return relationsDao.getEndPointsForSelected(selectedStartPoint)
  }

  fun getEndPointsForEnteredText(
    selectedStartPoint: String,
    enteredText: String
  ): Flow<List<String>> {
    return relationsDao.getEndPointsForEnteredText(selectedStartPoint, enteredText)
  }

  fun getCompaniesForRelation(startPoint: String, endPoint: String): Flow<List<String>> {
    return relationsDao.getCompaniesForRelation(startPoint, endPoint)
  }

  fun getRelationsForSelectedCompany(
    startPoint: String,
    endPoint: String,
    companyName: String
  ): Flow<List<Relation>> {
    return relationsDao.getRelationsForCompany(startPoint, endPoint, companyName)
  }
}