package com.example.wallet.core.presentation.util.dispatcher

import kotlinx.coroutines.Dispatchers

class ApplicationDispatcherImpl : ApplicationDispatcher {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val default = Dispatchers.Default
    override val unconfined = Dispatchers.Unconfined
}