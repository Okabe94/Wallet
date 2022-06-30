package com.example.wallet.feature_main.domain.usecase

import com.example.wallet.core.data.preferences.FakeApplicationPreferences
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_main.data.repository.FakeRecurrentRepository
import com.example.wallet.feature_main.data.time.DefaultTimeProvider
import com.example.wallet.feature_main.data.time.FakeTimeComparator
import com.example.wallet.feature_main.domain.repository.RecurrentRepository
import com.example.wallet.feature_main.domain.time.TimeComparator
import com.example.wallet.feature_main.domain.time.TimeProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class GetPendingUpdateUseCaseTest {

    private lateinit var fakeTimeProvider: TimeProvider
    private lateinit var fakeTimeComparator: TimeComparator
    private lateinit var fakeRepository: RecurrentRepository
    private lateinit var fakePreferences: ApplicationPreferences
    private lateinit var getPendingUpdateUseCase: GetPendingUpdateUseCase

    @Before
    fun setUp() {
        fakePreferences = FakeApplicationPreferences()
        fakeTimeComparator = FakeTimeComparator(2, 2)

        val auxTimeProvider = DefaultTimeProvider(
            Clock.fixed(Instant.parse("2020-01-20T10:05:00Z"), ZoneOffset.UTC)
        )

        val currentDate = auxTimeProvider.now()
        val todayMillis = currentDate.millis()
        val nextMonthMillis = currentDate.rollMonths().millis()

        val dataToInsert = mutableListOf<Expense>().apply {
            add(Expense("monthly, current", "100", true, todayMillis, todayMillis))
            add(Expense("no monthly, current", "100", false, todayMillis, todayMillis))
            add(Expense("monthly, next month", "100", true, todayMillis, nextMonthMillis))
            add(Expense("no monthly, next month", "100", false, todayMillis, nextMonthMillis))
        }

        fakeRepository = FakeRecurrentRepository(dataToInsert)
    }

    @Test
    fun `Getting all that need update two months later, successful`(): Unit = runBlocking {
        setUpProviderAndUseCase("2020-03-20T10:05:00Z")

        val today = fakeTimeProvider.now()
        val wrapper = getPendingUpdateUseCase()
        assertThat(wrapper).isNotNull()
        assertThat(wrapper?.pending).isNotEmpty()
        println("========== ========== ========== ==========")
        println("Today: ${today.format()}")
        println("========== ========== ========== ==========")
        wrapper?.pending?.forEach {
            assertThat(it.isMonthly).isTrue()
            assertThat(it.updatedUntil).isLessThan(today.millis())
            println("Created: ${fakeTimeProvider.now(it.createdAt).format()}")
            println("Update: ${fakeTimeProvider.now(it.updatedUntil).format()}")
            println("========== ========== ========== ==========")
        }
    }

    @Test
    fun `Getting all that need update one month later, successful`(): Unit = runBlocking {
        setUpProviderAndUseCase("2020-02-20T10:05:00Z")

        val today = fakeTimeProvider.now()
        val wrapper = getPendingUpdateUseCase()
        assertThat(wrapper).isNotNull()
        assertThat(wrapper?.pending).isNotEmpty()
        assertThat(wrapper?.pending).hasSize(1)
        println("========== ========== ========== ==========")
        println("Today: ${today.format()}")
        println("========== ========== ========== ==========")
        wrapper?.pending?.forEach {
            assertThat(it.isMonthly).isTrue()
            assertThat(it.updatedUntil).isLessThan(today.millis())
            println("Created: ${fakeTimeProvider.now(it.createdAt).format()}")
            println("Update: ${fakeTimeProvider.now(it.updatedUntil).format()}")
            println("========== ========== ========== ==========")
        }
    }

    private fun setUpProviderAndUseCase(date: String) {
        fakeTimeProvider = DefaultTimeProvider(Clock.fixed(Instant.parse(date), ZoneOffset.UTC))

        getPendingUpdateUseCase = GetPendingUpdateUseCase(
            fakeRepository, fakePreferences, fakeTimeProvider, fakeTimeComparator
        )
    }
}