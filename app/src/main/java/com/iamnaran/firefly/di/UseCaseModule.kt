package com.iamnaran.firefly.di

import com.iamnaran.firefly.data.repository.auth.AuthRepository
import com.iamnaran.firefly.domain.usecase.AuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(repository: AuthRepository): AuthUseCase =
        AuthUseCase(repository)


}