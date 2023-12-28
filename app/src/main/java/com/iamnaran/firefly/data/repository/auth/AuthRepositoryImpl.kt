package com.iamnaran.firefly.data.repository.auth

import com.iamnaran.firefly.data.remote.BaseApiResponse
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.dto.User
import com.iamnaran.firefly.data.remote.endpoint.LoginApi
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
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val externalScope: CoroutineScope,
    ) : AuthRepository, BaseApiResponse() {

    override suspend fun postLogin(username: String, password: String): Flow<Resource<User>> {
        return flow {
            emit(safeApiCall { loginApi.serverLogin(username,password) })
        }.flowOn(ioDispatcher)
    }
}