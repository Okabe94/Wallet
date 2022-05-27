package com.example.wallet.feature_main.domain.model.time

import java.time.Clock

interface Time {
    fun now(): Time
    fun getTime(): Long
    fun format(): String
    fun nextMonth(): Time
    fun now(time: Long): Time
    fun now(clock: Clock): Time
    fun daysBetween(other: Time): Long
    fun monthsBetween(other: Time): Long
}