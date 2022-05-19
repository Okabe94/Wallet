package com.example.wallet.data.repository

import com.example.wallet.data.dao.RecurrentDao
import javax.inject.Inject

class RecurrentRepository @Inject constructor(
    private val dao: RecurrentDao
) {
    suspend fun getLatestRecurrent() = dao.getLatestRecurrent()
    suspend fun getLatestRecurrent(lastRequestDate: Long) = dao.getLatestRecurrent(lastRequestDate)
}