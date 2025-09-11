package com.iamnaran.firefly.domain.usecase.auth

import android.content.Context
import com.iamnaran.firefly.R
import com.iamnaran.firefly.data.dto.UserResponse
import com.iamnaran.firefly.data.repository.auth.AuthRepository
import com.iamnaran.firefly.di.IoCoroutineScope
import com.iamnaran.firefly.utils.exception.FireflyException
import com.iamnaran.firefly.utils.extension.asFlow
import com.iamnaran.firefly.utils.helper.FlowUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(
    @param:ApplicationContext private val context: Context,
    @param:IoCoroutineScope   private val coroutineScope: CoroutineScope,
    private val authRepository: AuthRepository
) : FlowUseCase<GetAuthUseCase.Params, UserResponse>(coroutineScope) {

    override fun execute(params: Params?): Flow<UserResponse> = if (params != null) {
        authRepository.doLogin(params.username, params.password)
    } else {
        FireflyException.SnackBarException(message = context.getString(R.string.unable_to_login))
            .asFlow()
    }

    data class Params(
        val username: String,
        val password: String,
    )
}

// todo Not is used just for testing purpose
// with no params
//@Singleton
//class GetNoParamsUseCase @Inject constructor(
//    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
//    private val repo: Repo,
//) : FlowUseCase<Unit, Model>(mainDispatcher) {
//    override fun execute(params: Unit?): Flow<Model> = repo.getData()
//}