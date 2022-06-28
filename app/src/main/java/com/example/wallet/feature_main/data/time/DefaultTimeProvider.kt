package com.example.wallet.feature_main.data.time

import com.example.wallet.feature_main.domain.time.Time
import com.example.wallet.feature_main.domain.time.TimeProvider
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class DefaultTimeProvider @Inject constructor() : TimeProvider {

    override fun now(clock: Clock): Time = DefaultTime(clock)

    override fun now(): Time = DefaultTime(Clock.systemDefaultZone())

    override fun now(millis: Long): Time =
        DefaultTime(Clock.fixed(Instant.ofEpochMilli(millis), ZoneId.systemDefault()))

    override fun daysBetween(a: Time, b: Time) = ChronoUnit.DAYS.between(
        Instant.ofEpochMilli(a.millis()), Instant.ofEpochMilli(b.millis())
    )

    override fun monthsBetween(a: Time, b: Time) = ChronoUnit.MONTHS.between(
        Instant.ofEpochMilli(a.millis()), Instant.ofEpochMilli(b.millis())
    )

    internal class DefaultTime @Inject constructor(clock: Clock) : Time {

        private val dateTime = ZonedDateTime.now(clock)

        override fun format() = dateTime.toString()

        override fun millis() = dateTime.toInstant().toEpochMilli()

        override fun rollMonths(months: Long): Time {
            val clock = Clock.fixed(dateTime.plusMonths(months).toInstant(), ZoneId.systemDefault())
            return DefaultTime(clock)
        }
    }
}