package com.example.wallet.feature_recurrent.domain.util

import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

private const val DAYS_IN_A_MONTH = 30L

fun Instant.requiredTime() = epochSecond
fun Instant.nextMonth(): Instant = plus(Duration.ofDays(DAYS_IN_A_MONTH))
fun daysBetween(a: Instant, b: Instant) = ChronoUnit.DAYS.between(a, b)
fun monthsBetween(a: Instant, b: Instant) = ChronoUnit.MONTHS.between(a, b)

fun standardTime(): Instant = Instant.now()
fun standardTime(clock: Clock): Instant = Instant.now(clock)
fun standardTime(time: Long): Instant = Instant.ofEpochSecond(time)
