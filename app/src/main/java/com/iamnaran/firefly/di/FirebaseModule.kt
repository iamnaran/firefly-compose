package com.iamnaran.firefly.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.iamnaran.firefly.ui.auth.core.GoogleAuthClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideSignInClient( @ApplicationContext context: Context): SignInClient = Identity.getSignInClient(context)

    @Provides
    @Singleton
    fun provideFirebaseGoogleAuthClient(
        @ApplicationContext context: Context,
        signInClient: SignInClient,
        firebaseAuth: FirebaseAuth,
    ) = GoogleAuthClient(context, signInClient, firebaseAuth)

}