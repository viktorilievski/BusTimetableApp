package mk.vozenred.bustimetableapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import mk.vozenred.bustimetableapp.data.model.Relation
import mk.vozenred.bustimetableapp.data.repositories.local.RelationsRepository
import javax.inject.Inject

@HiltViewModel
class FavoriteRelationsViewModel @Inject constructor(
  private val relationsRepository: RelationsRepository
) : ViewModel() {

  private val _favoriteRelations: MutableSharedFlow<List<Relation>> = MutableSharedFlow()
  val favoriteRelations: SharedFlow<List<Relation>> = _favoriteRelations

  fun getAllFavoriteRelations() {
    viewModelScope.launch(Dispatchers.IO) {
      _favoriteRelations.emit(
        relationsRepository.getFavoriteRelations()
      )
    }
  }

  fun removeFavoriteRelation(relationId: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      relationsRepository.removeRelationFromFavorite(relationId = relationId)
      getAllFavoriteRelations()
    }
  }
}