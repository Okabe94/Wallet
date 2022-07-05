package com.example.wallet.core.util

import com.example.wallet.feature_main.data.time.DefaultTimeProvider
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

fun createFakeTimeProvider(date: String) = DefaultTimeProvider(
    Clock.fixed(Instant.parse(date), ZoneOffset.UTC)
)
