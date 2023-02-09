package mk.vozenred.bustimetableapp.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mk.vozenred.bustimetableapp.data.repositories.firebase.FirebaseRemoteConfigDb
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
}