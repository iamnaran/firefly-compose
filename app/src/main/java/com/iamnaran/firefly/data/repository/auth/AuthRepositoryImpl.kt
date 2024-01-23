package com.iamnaran.firefly.data.repository.auth

import com.iamnaran.firefly.data.local.AppDatabase
import com.iamnaran.firefly.data.local.entities.UserEntity
import com.iamnaran.firefly.data.remote.BaseApiResponse
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.data.remote.service.AuthApi
import com.iamnaran.firefly.domain.dto.UserResponse
import com.iamnaran.firefly.data.remote.service.LoginApi
import com.iamnaran.firefly.di.qualifiers.DefaultDispatcher
import com.iamnaran.firefly.di.qualifiers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val authApi: AuthApi,
    private val appDatabase: AppDatabase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val externalScope: CoroutineScope,
) : AuthRepository, BaseApiResponse() {

    override suspend fun postLogin(
        username: String,
        password: String
    ): Flow<Resource<UserResponse>> {
        return flow {
            emit(safeApiCall { loginApi.serverLogin(username, password) })
        }.flowOn(ioDispatcher)
    }

    override fun doLogin(username: String, password: String): Flow<UserResponse> {
        return authApi.authApi(username, password)
    }

    override suspend fun storeLoggedInUser(user: UserEntity) {
        appDatabase.userDao().insertUser(user)
    }

    override suspend fun getUserById(userId: Long): Flow<UserEntity> {
        return appDatabase.userDao().getUserById(userId)
    }
}