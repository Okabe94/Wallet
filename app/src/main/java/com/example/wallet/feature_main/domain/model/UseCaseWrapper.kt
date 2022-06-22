package com.example.wallet.feature_main.domain.model

import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_main.data.time.Time

data class UseCaseWrapper(val time: Time, val pending: List<Expense>)