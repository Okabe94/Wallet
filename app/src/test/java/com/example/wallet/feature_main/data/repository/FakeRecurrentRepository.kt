package com.example.wallet.feature_main.data.repository

import android.util.Log
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_main.domain.repository.RecurrentRepository

private const val TAG = "FakeRecurrent"

class FakeRecurrentRepository(private val list: List<Expense>) : RecurrentRepository {

    override suspend fun updateRecurrent(list: List<Expense>) {
        list.forEach { Log.d(TAG, it.toString()) }
    }

    override suspend fun getPendingRecurrent(today: Long) = list.filter {
        println(today)
        it.updatedUntil < today
    }
}