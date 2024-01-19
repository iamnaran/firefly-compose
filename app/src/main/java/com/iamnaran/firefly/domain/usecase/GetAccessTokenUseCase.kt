package com.iamnaran.firefly.domain.usecase

import com.iamnaran.firefly.data.preference.PreferenceHelper
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val preferenceHelper: PreferenceHelper
) {

    operator fun invoke() =
        preferenceHelper.getAccessToken()


}