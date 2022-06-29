package com.example.wallet.core.presentation.util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface ApplicationDispatcher {
    val IO: CoroutineDispatcher
    val MAIN: CoroutineDispatcher
    val DEFAULT: CoroutineDispatcher
    val UNCONFINED: CoroutineDispatcher
}