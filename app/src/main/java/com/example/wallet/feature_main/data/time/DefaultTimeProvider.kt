package com.example.wallet.feature_main.data.time

import com.example.wallet.feature_main.domain.time.Time
import com.example.wallet.feature_main.domain.time.TimeProvider
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class DefaultTimeProvider : TimeProvider {

    override fun now(): Time = DefaultTime(Clock.systemDefaultZone())

    override fun now(clock: Clock): Time = DefaultTime(clock)

    override fun now(millis: Long): Time = DefaultTime(
        Clock.fixed(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
    )

    internal class DefaultTime(clock: Clock) : Time {

        private val dateTime = ZonedDateTime.now(clock)

        override fun format() = dateTime.toString()

        override fun millis() = dateTime.toInstant().toEpochMilli()

        override fun rollMonths(months: Long): Time {
            val nextDate = dateTime.plusMonths(months).toInstant()
            return DefaultTime(Clock.fixed(nextDate, ZoneId.systemDefault()))
        }
    }
}