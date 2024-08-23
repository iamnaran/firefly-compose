package com.iamnaran.firefly.ui.main.interest

import com.iamnaran.firefly.domain.dto.InterestModel


data class InterestState(
    var interests: List<InterestModel> = emptyList()
)