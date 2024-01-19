package com.iamnaran.firefly.domain.usecase

import android.content.Context
import com.iamnaran.firefly.R
import com.iamnaran.firefly.data.repository.auth.AuthRepository
import com.iamnaran.firefly.di.qualifiers.MainDispatcher
import com.iamnaran.firefly.domain.dto.User
import com.iamnaran.firefly.utils.exception.FireflyException
import com.iamnaran.firefly.utils.extension.FlowUseCase
import com.iamnaran.firefly.utils.extension.asFlow
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAuthUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    @MainDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
) : FlowUseCase<GetAuthUseCase.Params, User>(coroutineDispatcher) {
    override fun execute(params: Params?): Flow<User> = if (params != null) {
        authRepository.doLogin(params.username,params.password)
    } else {
        FireflyException.SnackBarException(message = context.getString(R.string.unable_to_login)).asFlow()
    }

    data class Params(
        val username: String,
        val password: String,
    )
}

// with no params
//@Singleton
//class GetNoParamsUseCase @Inject constructor(
//    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
//    private val repo: Repo,
//) : FlowUseCase<Unit, Model>(mainDispatcher) {
//    override fun execute(params: Unit?): Flow<Model> = repo.getData()
//}