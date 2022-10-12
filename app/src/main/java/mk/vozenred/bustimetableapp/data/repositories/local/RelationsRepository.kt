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

    fun fetchRelation(relationId: Int): Relation {
        return relationsDao.fetchRelation(relationId)
    }

    fun getAllRelations(): Flow<List<Relation>> {
        return relationsDao.getAllRelations()
    }

    fun getRelations(departure: String, arrival: String): Flow<List<Relation>> {
        return relationsDao.getRelations(departure, arrival)
    }

    suspend fun addRelation(relation: Relation) {
        relationsDao.addRelation(relation = relation)
    }

    fun getAllStartingPoints(): Flow<List<String>> {
        return relationsDao.getAllStartingPoints()
    }

    fun getAllEndPoints(): Flow<List<String>> {
        return relationsDao.getAllEndPoints()
    }

    fun getEndPointsForSelectedStartPoint(selectedStartPoint: String): Flow<List<String>> {
        return relationsDao.getEndPointsForSelected(selectedStartPoint)
    }

    fun getCompaniesForRelation(startPoint: String, endPoint: String): Flow<List<String>> {
        return relationsDao.getCompaniesForRelation(startPoint, endPoint)
    }

    fun getRelationsForSelectedCompany(startPoint: String, endPoint: String, companyName: String): Flow<List<Relation>> {
        return relationsDao.getRelationsForCompany(startPoint, endPoint, companyName)
    }

    suspend fun setRelationFavoriteStatus(relationId: Int, isRelationFavorite: Boolean) {
        relationsDao.setRelationFavoriteStatus(relationId, isRelationFavorite)
    }
}