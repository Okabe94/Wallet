package com.example.wallet.feature_main.domain.time

interface TimeComparator {
    fun daysBetween(a: Time, b: Time) : Long
    fun monthsBetween(a: Time, b: Time) : Long
}