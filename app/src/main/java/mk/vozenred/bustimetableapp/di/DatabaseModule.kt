package mk.vozenred.bustimetableapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mk.vozenred.bustimetableapp.data.RelationsDatabase
import mk.vozenred.bustimetableapp.util.Constants.RELATIONS_DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RelationsDatabase::class.java,
        RELATIONS_DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideRelationsDao(database: RelationsDatabase) = database.relationsDao()

    @Singleton
    @Provides
    fun provideFavoritesDao(database: RelationsDatabase) = database.favoritesDao()
}