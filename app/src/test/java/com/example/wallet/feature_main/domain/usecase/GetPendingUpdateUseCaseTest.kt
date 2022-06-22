package com.example.wallet.feature_main.domain.usecase

import android.util.Log
import com.example.wallet.core.data.preferences.FakeApplicationPreferences
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_main.data.repository.FakeRecurrentRepository
import com.example.wallet.feature_main.data.time.Time
import com.example.wallet.feature_main.data.time.WalletTime
import com.example.wallet.feature_main.domain.repository.RecurrentRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class GetPendingUpdateUseCaseTest {

    private lateinit var fakeTime: Time
    private lateinit var fakeClock: Clock
    private lateinit var fakeRepository: RecurrentRepository
    private lateinit var fakePreferences: ApplicationPreferences
    private lateinit var getPendingUpdateUseCase: GetPendingUpdateUseCase

    @Before
    fun setUp() {
        fakeClock = Clock.fixed(Instant.parse("2020-01-20T10:05:00Z"), ZoneOffset.UTC)
        fakeTime = WalletTime(fakeClock).now()

        val today = fakeTime.getTime()
        val nextMonth = fakeTime.nextMonth().getTime()

        val dataToInsert = mutableListOf<Expense>().apply {
            add(Expense("montly, current", "100", true, today, today))
            add(Expense("no monthly, current", "100", false, today, today))
            add(Expense("montly, next month", "100", true, today, nextMonth))
            add(Expense("no monthyl, next month", "100", false, today, nextMonth))
        }

        fakeRepository = FakeRecurrentRepository(dataToInsert)
        fakePreferences = FakeApplicationPreferences()

        fakeTime = WalletTime(fakeClock).now().nextMonth().nextMonth()
        getPendingUpdateUseCase = GetPendingUpdateUseCase(fakeRepository, fakePreferences, fakeTime)

        println("BEFORE: $dataToInsert")
    }

    @Test
    fun `Getting all that need update, successful`() = runBlocking {
        println(fakeTime.format())
        val wrapper = getPendingUpdateUseCase()
        assertThat(wrapper).isNotNull()
    }
}