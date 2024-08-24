package com.iamnaran.firefly.ui.main.interest.components.sheet

import com.iamnaran.firefly.domain.dto.InterestModel


data class AccountState(
    var interests: List<InterestModel> = emptyList()
)