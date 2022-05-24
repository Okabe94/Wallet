package com.example.wallet.core.presentation.util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface ApplicationDispatcher {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}