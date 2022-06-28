package com.example.wallet.feature_main.domain.time

interface Time {
    fun millis(): Long
    fun format(): String
    fun rollMonths(months: Long = 1L): Time
}