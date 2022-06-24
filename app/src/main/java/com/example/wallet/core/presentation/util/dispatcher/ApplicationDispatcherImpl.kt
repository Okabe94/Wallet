package com.example.wallet.core.presentation.util.dispatcher

import kotlinx.coroutines.Dispatchers

class ApplicationDispatcherImpl : ApplicationDispatcher {
    override val IO = Dispatchers.IO
    override val MAIN = Dispatchers.Main
    override val DEFAULT = Dispatchers.Default
    override val UNCONFINED = Dispatchers.Unconfined
}