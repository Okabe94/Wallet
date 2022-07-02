package com.example.wallet.feature_main.data.time

import com.example.wallet.feature_main.domain.time.Time
import com.example.wallet.feature_main.domain.time.TimeComparator

class FaultyTimeComparator : TimeComparator {

    override fun daysBetween(a: Time, b: Time): Long = throw Exception()

    override fun monthsBetween(a: Time, b: Time): Long = throw Exception()
}