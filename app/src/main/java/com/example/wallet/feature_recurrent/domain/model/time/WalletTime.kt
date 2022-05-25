package com.example.wallet.feature_recurrent.domain.model.time

import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class WalletTime private constructor(
    private var time: ZonedDateTime
) : Time {

    companion object {
        fun create() = WalletTime(ZonedDateTime.now())

        fun create(clock: Clock) = WalletTime(ZonedDateTime.now(clock))

        fun create(time: Long) = WalletTime(zonedFromTime(time))

        private fun zonedFromTime(time: Long) =
            ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault())
    }

    override fun format() = time.toString()

    override fun getTime() = time.toEpochSecond()

    override fun nextMonth() = apply { time = time.plusMonths(1L) }

    override fun daysBetween(other: Time) =
        ChronoUnit.DAYS.between(time, zonedFromTime(other.getTime()))

    override fun monthsBetween(other: Time) =
        ChronoUnit.MONTHS.between(time, zonedFromTime(other.getTime()))
}