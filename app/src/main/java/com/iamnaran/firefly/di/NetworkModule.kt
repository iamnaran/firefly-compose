package com.iamnaran.firefly.di

import com.iamnaran.firefly.BuildConfig
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.data.remote.SupportAuthenticator
import com.iamnaran.firefly.data.remote.SupportInterceptor
import com.iamnaran.firefly.data.remote.endpoint.LoginApi
import com.iamnaran.firefly.data.remote.endpoint.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = BuildConfig.BASE_URL


    @Provides
    @Singleton
    fun provideHttpLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideSupportInterceptor(): SupportInterceptor {
        return SupportInterceptor()
    }

    @Provides
    @Singleton
    fun provideUnAuthorizedInterceptor(preferenceHelper: PreferenceHelper): SupportAuthenticator {
        return SupportAuthenticator(preferenceHelper)
    }


    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("BaseUrl") baseUrl: String,
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        supportAuthenticator: SupportAuthenticator,
        supportInterceptor: SupportInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(supportInterceptor)
            .authenticator(supportAuthenticator)
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginApiService(retrofit: Retrofit): LoginApi =
        retrofit.create(LoginApi::class.java)

    @Singleton
    @Provides
    fun provideProductApiService(retrofit: Retrofit): ProductApi =
        retrofit.create(ProductApi::class.java)

}