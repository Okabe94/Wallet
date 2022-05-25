package com.example.wallet.feature_recurrent.domain.model.time

interface Time {
    fun getTime(): Long
    fun nextMonth(): Time
    fun format() : String
    fun daysBetween(other: Time): Long
    fun monthsBetween(other: Time): Long
}