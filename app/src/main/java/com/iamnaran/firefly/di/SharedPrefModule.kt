package com.iamnaran.firefly.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.iamnaran.firefly.data.preference.PrefConstants
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.data.preference.PreferenceHelperImpl
import com.iamnaran.firefly.di.qualifiers.SharedPrefInfoQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.IOException
import java.security.GeneralSecurityException
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {


    @Provides
    @SharedPrefInfoQualifier
    fun provideSharedPreferenceName(): String {
        return PrefConstants.PREF_FILE_NAME
    }

//    @Provides
//    fun provideNormalSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
//        PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun provideSecureSharedPreferences(
        @ApplicationContext context: Context,
        @SharedPrefInfoQualifier fileName: String
    ): SharedPreferences {
        val sharedPreferences: SharedPreferences = try {
            val masterKey: MasterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
            EncryptedSharedPreferences.create(
                context,
                fileName,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: GeneralSecurityException) {
            context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        } catch (e: IOException) {
            context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        }
        return sharedPreferences
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(sharedPrefHelper: PreferenceHelperImpl): PreferenceHelper {
        return sharedPrefHelper
    }
}