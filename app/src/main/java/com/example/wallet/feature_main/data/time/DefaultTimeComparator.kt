package com.example.wallet.feature_main.data.time

import com.example.wallet.feature_main.domain.time.Time
import com.example.wallet.feature_main.domain.time.TimeComparator
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class DefaultTimeComparator @Inject constructor(): TimeComparator {
    override fun daysBetween(a: Time, b: Time) = ChronoUnit.DAYS.between(
        Instant.ofEpochMilli(a.millis()), Instant.ofEpochMilli(b.millis())
    )

    override fun monthsBetween(a: Time, b: Time) = ChronoUnit.MONTHS.between(
        Instant.ofEpochMilli(a.millis()), Instant.ofEpochMilli(b.millis())
    )
}