package com.example.movieappinkotlin

import android.app.Application
import androidx.room.Room
import javax.inject.Singleton
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun providesAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "mymovies.db"
        ).allowMainThreadQueries().build()
    }
}