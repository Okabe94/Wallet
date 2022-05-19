package com.example.wallet.ui.util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface WalletDispatcher {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}