package com.example.wallet.feature_main.domain.model.time

interface Time {
    fun now(): Time
    fun getTime(): Long
    fun format(): String
    fun nextMonth(): Time
    fun now(time: Long): Time
    fun daysBetween(other: Time): Long
    fun monthsBetween(other: Time): Long
}