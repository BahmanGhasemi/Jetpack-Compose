package com.example.jetpackcompose._05_persistant_storage

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.SharedPreferencesCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("My_Ppref", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideSessionCache(pref: SharedPreferences): SessionCache {
        return SessionCacheImpl(pref)
    }
}