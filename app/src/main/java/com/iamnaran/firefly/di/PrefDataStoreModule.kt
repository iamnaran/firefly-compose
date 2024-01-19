package com.iamnaran.firefly.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.iamnaran.firefly.data.preference.datastore.PrefKeys
import com.iamnaran.firefly.data.preference.datastore.PrefDataStoreHelper
import com.iamnaran.firefly.data.preference.datastore.PrefDataStoreHelperImpl
import com.iamnaran.firefly.di.qualifiers.DataStorePrefInfoQualifier
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
    @DataStorePrefInfoQualifier
    fun providePrefDatastoreName(): String {
        return PrefKeys.PREF_FILE_NAME
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context, @DataStorePrefInfoQualifier prefName: String) : DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { context.preferencesDataStoreFile(prefName) }
        )
    }


    @Provides
    @Singleton
    fun providePrefDataStoreHelper(prefDataStoreHelper: PrefDataStoreHelperImpl): PrefDataStoreHelper {
        return prefDataStoreHelper
    }
}