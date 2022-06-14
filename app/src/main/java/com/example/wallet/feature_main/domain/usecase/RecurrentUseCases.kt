package com.example.wallet.feature_main.domain.usecase

import javax.inject.Inject

data class RecurrentUseCases @Inject constructor(
    val updateRecurrent: UpdateRecurrentUseCase,
    val getRecurrent: GetPendingUpdateUseCase
)