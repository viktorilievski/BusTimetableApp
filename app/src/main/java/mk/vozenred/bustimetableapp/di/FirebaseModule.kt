package mk.vozenred.bustimetableapp.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.ASCENDING
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mk.vozenred.bustimetableapp.data.repositories.firebase.FirebaseRemoteConfigDb
import mk.vozenred.bustimetableapp.util.Constants.DEPARTURE_TIME
import mk.vozenred.bustimetableapp.util.Constants.RELATIONS_COLLECTION
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfigDb {
        return FirebaseRemoteConfigDb()
    }

    @Singleton
    @Provides
    fun provideRelationsByDepartureTime() = FirebaseFirestore.getInstance()
        .collection(RELATIONS_COLLECTION)
        .orderBy(DEPARTURE_TIME, ASCENDING)
}