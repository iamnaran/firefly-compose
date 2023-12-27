package com.iamnaran.firefly.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.iamnaran.firefly.data.preference.PrefKeys
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.data.preference.PreferenceHelperImpl
import com.iamnaran.firefly.di.qualifiers.PreferenceInfoQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PrefDataStoreModule {

    @Provides
    @PreferenceInfoQualifier
    fun providePreferenceName(): String {
        return PrefKeys.PREF_FILE_NAME
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context, @PreferenceInfoQualifier prefName: String) : DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { context.preferencesDataStoreFile(prefName) }
        )
    }


    @Provides
    @Singleton
    fun providePreferencesHelper(preferenceHelper: PreferenceHelperImpl): PreferenceHelper {
        return preferenceHelper
    }
}