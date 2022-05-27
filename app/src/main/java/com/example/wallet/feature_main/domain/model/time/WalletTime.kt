package com.example.wallet.feature_main.domain.model.time

import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class WalletTime : Time {

    private var dateTime: ZonedDateTime? = null

    override fun now() = apply { dateTime = ZonedDateTime.now() }

    override fun now(clock: Clock) = apply { dateTime = ZonedDateTime.now(clock) }

    override fun now(time: Long) = apply { dateTime = zonedFromTime(time) }

    override fun format() = dateTime.toString()

    override fun getTime() = dateTime?.toEpochSecond() ?: 0L

    override fun nextMonth() = apply { dateTime = dateTime?.plusMonths(1L) }

    override fun daysBetween(other: Time) =
        ChronoUnit.DAYS.between(dateTime, zonedFromTime(other.getTime()))

    override fun monthsBetween(other: Time) =
        ChronoUnit.MONTHS.between(dateTime, zonedFromTime(other.getTime()))

    private fun zonedFromTime(time: Long) =
        ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault())

}