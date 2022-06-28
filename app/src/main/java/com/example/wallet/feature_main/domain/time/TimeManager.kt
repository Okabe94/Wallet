package com.example.wallet.feature_main.domain.time

import java.time.Clock

interface TimeManager {
    fun now(): Time
    fun now(millis: Long): Time
    fun now(clock: Clock): Time
    fun daysBetween(a: Time, b: Time): Long
    fun monthsBetween(a: Time, b: Time): Long
}