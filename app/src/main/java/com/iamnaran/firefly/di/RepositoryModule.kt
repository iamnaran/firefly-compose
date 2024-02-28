package com.iamnaran.firefly.di

import com.iamnaran.firefly.data.repository.auth.AuthRepository
import com.iamnaran.firefly.data.repository.auth.AuthRepositoryImpl
import com.iamnaran.firefly.data.repository.product.ProductRepository
import com.iamnaran.firefly.data.repository.product.ProductRepositoryImpl
import com.iamnaran.firefly.data.repository.recipe.RecipeRepository
import com.iamnaran.firefly.data.repository.recipe.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(productRepository: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    abstract fun bindRecipeRepository(recipeRepository: RecipeRepositoryImpl): RecipeRepository
}