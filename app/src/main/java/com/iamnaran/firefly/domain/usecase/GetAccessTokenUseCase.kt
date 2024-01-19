package com.iamnaran.firefly.domain.usecase

import com.iamnaran.firefly.data.preference.datastore.PrefDataStoreHelper
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val prefDataStoreHelper: PrefDataStoreHelper
) {

    operator fun invoke() =
        prefDataStoreHelper.getAccessToken()


}