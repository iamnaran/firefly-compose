package com.iamnaran.firefly.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefsModule {

//    @Provides
//    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
//        PreferenceManager.getDefaultSharedPreferences(context)
}