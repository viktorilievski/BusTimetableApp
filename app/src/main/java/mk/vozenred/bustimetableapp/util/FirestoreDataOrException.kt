package mk.vozenred.bustimetableapp.util

data class FirestoreDataOrException<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)