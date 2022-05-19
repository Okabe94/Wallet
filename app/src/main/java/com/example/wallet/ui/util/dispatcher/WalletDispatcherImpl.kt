package com.example.wallet.ui.util.dispatcher

import kotlinx.coroutines.Dispatchers

class WalletDispatcherImpl : WalletDispatcher {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val default = Dispatchers.Default
    override val unconfined = Dispatchers.Unconfined
}