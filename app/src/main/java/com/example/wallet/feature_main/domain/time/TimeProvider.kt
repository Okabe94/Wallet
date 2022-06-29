package com.example.wallet.feature_main.domain.time

import java.time.Clock

abstract class TimeProvider(protected val clock: Clock) {
    abstract fun now(): Time
    abstract fun now(millis: Long): Time
    abstract fun now(clock: Clock): Time
}