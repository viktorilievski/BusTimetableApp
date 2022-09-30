package mk.vozenred.bustimetableapp.data.repositories.local

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import mk.vozenred.bustimetableapp.data.FavoriteRelationsDao
import mk.vozenred.bustimetableapp.data.model.FavoriteRelation
import mk.vozenred.bustimetableapp.data.model.Relation
import javax.inject.Inject

@ViewModelScoped
class FavoriteRelationsRepository @Inject constructor(
    private val favoriteRelationsDao: FavoriteRelationsDao
) {

    fun getAllFavoriteRelations(): Flow<List<FavoriteRelation>> {
        return favoriteRelationsDao.getAllFavoriteRelations()
    }

    suspend fun addFavoriteRelation(relation: Relation) {
        return favoriteRelationsDao.addFavoriteRelation(relation = relation)
    }
}