package com.iamnaran.firefly.di

import android.content.Context
import android.content.SharedPreferences
import android.os.Build.VERSION.SDK_INT
import androidx.preference.PreferenceManager
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iamnaran.firefly.data.preference.PrefConstants
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.data.preference.PreferenceHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context?): Context? {
        return context
    }


    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return PrefConstants.PREF_FILE_NAME
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun providePreferencesHelper(preferenceHelper: PreferenceHelperImpl): PreferenceHelper {
        return preferenceHelper
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .okHttpClient { okHttpClient }
            .build()
    }
}