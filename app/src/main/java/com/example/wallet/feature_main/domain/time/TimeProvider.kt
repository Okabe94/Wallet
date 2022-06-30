package com.example.wallet.feature_main.domain.time

import java.time.Clock

interface TimeProvider {
    fun now(): Time
    fun now(millis: Long): Time
    fun now(clock: Clock): Time
}