package com.iamnaran.firefly.domain.usecase

import com.iamnaran.firefly.data.preference.PreferenceHelper
import javax.inject.Inject

class GetLoginStatusUseCase @Inject constructor(
    private val preferenceHelper: PreferenceHelper
) {

    suspend operator fun invoke() =
        preferenceHelper.getLoggedInStatus()


}