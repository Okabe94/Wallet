package com.example.wallet.feature_main.domain.usecase

data class RecurrentUseCases(
    val updateRecurrent: UpdateRecurrentUseCase,
    val getRecurrent: GetPendingUpdateUseCase
)