package mk.vozenred.bustimetableapp.data.repositories.local

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import mk.vozenred.bustimetableapp.data.FavoriteRelationsDao
import mk.vozenred.bustimetableapp.data.model.FavoriteRelation
import mk.vozenred.bustimetableapp.data.model.Relation
import javax.inject.Inject

//todo: to be implemented in near future
@ViewModelScoped
class FavoriteRelationsRepository @Inject constructor(
    private val favoriteRelationsDao: FavoriteRelationsDao
) {

    fun getAllFavoriteRelations(): Flow<List<FavoriteRelation>> {
        return favoriteRelationsDao.getAllFavoriteRelations()
    }

    fun fetchFavoriteRelation(relationId: Int): FavoriteRelation? {
        return favoriteRelationsDao.fetchFavoriteRelation(relationId)
    }

    suspend fun removeRelationFromFavorite(relationId: Int) {
        return favoriteRelationsDao.removeRelationFromFavorite(relationId)
    }

    suspend fun addFavoriteRelation(relation: FavoriteRelation) {
        return favoriteRelationsDao.addFavoriteRelation(relation = relation)
    }
}