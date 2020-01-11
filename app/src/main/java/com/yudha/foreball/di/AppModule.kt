package com.yudha.foreball.di

import android.app.Application
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.yudha.foreball.data.*
import com.yudha.foreball.service.MatchApi
import com.yudha.foreball.service.MatchApiFactory
import com.yudha.foreball.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideMatchApi(): MatchApi = MatchApiFactory.initAPI()

    @Provides
    @Singleton
    fun provideRepository(api: MatchApi, leagueDao: LeagueDao, matchDao: MatchDao): Repository =
        Repository(api, leagueDao, matchDao)

    @Provides
    @Singleton
    fun provideLeagueDao(db: Database): LeagueDao = db.leagueDao()

    @Provides
    @Singleton
    fun provideMatchDao(db: Database): MatchDao = db.matchDao()

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Database =
        Room.databaseBuilder(app, Database::class.java, "foreball_db").build()

    @Provides
    fun provideSearchModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideSettings(app: Application): Settings = Settings(PreferenceManager.getDefaultSharedPreferences(app))

}
