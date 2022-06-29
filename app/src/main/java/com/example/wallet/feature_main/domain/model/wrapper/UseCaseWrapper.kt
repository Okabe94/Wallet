package com.example.wallet.feature_main.domain.model.wrapper

import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_main.domain.time.Time

data class UseCaseWrapper(val time: Time, val pending: List<Expense>)