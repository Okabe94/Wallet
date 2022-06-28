package com.example.wallet.feature_main.data.time

import com.example.wallet.feature_main.domain.time.Time
import java.time.Clock
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject

class DefaultTime @Inject constructor(clock: Clock) : Time {

    private val dateTime = ZonedDateTime.now(clock)

    override fun format() = dateTime.toString()

    override fun millis() = dateTime.toInstant().toEpochMilli()

    override fun rollMonths(months: Long): Time {
        val clock = Clock.fixed(dateTime.plusMonths(months).toInstant(), ZoneId.systemDefault())
        return DefaultTime(clock)
    }
}
