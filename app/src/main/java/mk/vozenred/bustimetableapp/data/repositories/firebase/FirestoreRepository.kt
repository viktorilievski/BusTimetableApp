package mk.vozenred.bustimetableapp.data.repositories.firebase

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import mk.vozenred.bustimetableapp.data.RelationsDao
import mk.vozenred.bustimetableapp.data.model.Relation
import mk.vozenred.bustimetableapp.util.FirestoreDataOrException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreRepository @Inject constructor(
    private val relationsByDepartureTime: Query
){
    suspend fun getRelationsFromFirestore(): FirestoreDataOrException<List<Relation>, Exception> {
        val dataOrException = FirestoreDataOrException<List<Relation>, Exception>()
        try {
            dataOrException.data = relationsByDepartureTime.get().await().map { document ->
                document.toObject(Relation::class.java)
            }
        } catch (exception: FirebaseFirestoreException) {
            dataOrException.e = exception
        }
        return dataOrException
    }

}