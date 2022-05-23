package com.example.wallet.data.util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface ApplicationDispatcher {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}