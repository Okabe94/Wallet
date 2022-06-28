package com.example.wallet.feature_main.data.time

import com.example.wallet.feature_main.domain.time.Time
import com.example.wallet.feature_main.domain.time.TimeManager
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class DefaultTimeManager @Inject constructor(): TimeManager {
    override fun now(clock: Clock): Time = DefaultTime(clock)

    override fun now(): Time = DefaultTime(Clock.systemDefaultZone())

    override fun now(millis: Long): Time {
        val clock = Clock.fixed(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
        return DefaultTime(clock)
    }

    override fun daysBetween(a: Time, b: Time) =
        ChronoUnit.DAYS.between(Instant.ofEpochMilli(a.millis()), Instant.ofEpochMilli(b.millis()))

    override fun monthsBetween(a: Time, b: Time) =
        ChronoUnit.MONTHS.between(Instant.ofEpochMilli(a.millis()), Instant.ofEpochMilli(b.millis()))
}