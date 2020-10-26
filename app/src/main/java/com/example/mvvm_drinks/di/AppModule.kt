package com.example.mvvm_drinks.di

import android.content.Context
import androidx.room.Room
import com.example.mvvm_drinks.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, AppDataBase::class.java,
        "movie_db_room").build()

    @Singleton
    @Provides
    fun provideMovieDao(dataBase: AppDataBase) = dataBase.movieDao()
}