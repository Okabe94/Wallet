package com.example.wallet.feature_main.domain.time

interface TimeProvider {
    fun now(): Time
    fun now(millis: Long): Time
}