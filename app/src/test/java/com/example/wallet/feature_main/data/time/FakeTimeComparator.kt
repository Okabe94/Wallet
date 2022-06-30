package com.example.wallet.feature_main.data.time

import com.example.wallet.feature_main.domain.time.Time
import com.example.wallet.feature_main.domain.time.TimeComparator

class FakeTimeComparator(
    private val days: Long = 1L,
    private val months: Long = 1L
) : TimeComparator {

    override fun daysBetween(a: Time, b: Time) = days
    override fun monthsBetween(a: Time, b: Time) = months

}