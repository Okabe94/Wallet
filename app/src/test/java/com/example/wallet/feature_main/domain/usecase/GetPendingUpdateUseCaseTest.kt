package com.example.wallet.feature_main.domain.usecase

import com.example.wallet.core.data.preferences.FakeApplicationPreferences
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.core.util.TestingConstants.MONTH1
import com.example.wallet.core.util.TestingConstants.MONTH2
import com.example.wallet.core.util.TestingConstants.MONTH3
import com.example.wallet.core.util.createFakeTimeProvider
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
import java.time.ZoneId

class GetPendingUpdateUseCaseTest {

    private lateinit var fakeTimeComparator: TimeComparator
    private lateinit var fakeRepository: RecurrentRepository
    private lateinit var fakePreferences: ApplicationPreferences

    @Before
    fun setUp() {
        fakeTimeComparator = FakeTimeComparator()
        fakePreferences = FakeApplicationPreferences()
        fakeRepository = FakeRecurrentRepository(createDummyData())
    }

    @Test
    fun `Months elapsed, No preferences - Get expenses`(): Unit = runBlocking {
        val timeProvider = createFakeTimeProvider(MONTH3)
        val useCase = createGetPendingUpdateUseCase(timeProvider)

        val response = useCase()
        assertThat(response).isNotNull()
        assertThat(response).isNotEmpty()
    }

    @Test
    fun `Months elapsed, With preferences - Get expenses`(): Unit = runBlocking {
        val timeProvider = createFakeTimeProvider(MONTH3)
        val useCase = createGetPendingUpdateUseCase(
            timeProvider,
            preferences = FakeApplicationPreferences(timeProvider.now().millis()),
            comparator = FakeTimeComparator(2)
        )
        val response = useCase()
        assertThat(response).isNotNull()
        assertThat(response).isNotEmpty()
    }

    @Test
    fun `No month elapsed, No preferences - Get null`(): Unit = runBlocking {
        val timeProvider = createFakeTimeProvider(MONTH1)
        val useCase = createGetPendingUpdateUseCase(timeProvider)

        val response = useCase()
        assertThat(response).isNull()
    }

    @Test
    fun `No month elapsed, With preferences - Get null`(): Unit = runBlocking {
        val timeProvider = createFakeTimeProvider(MONTH1)
        val useCase = createGetPendingUpdateUseCase(
            timeProvider,
            preferences = FakeApplicationPreferences(timeProvider.now().millis())
        )
        val response = useCase()
        assertThat(response).isNull()
    }

    private fun createGetPendingUpdateUseCase(
        timeProvider: TimeProvider,
        repo: RecurrentRepository = fakeRepository,
        preferences: ApplicationPreferences = fakePreferences,
        comparator: TimeComparator = fakeTimeComparator,
    ) = GetPendingUpdateUseCase(repo, preferences, timeProvider, comparator)

    private fun createDummyData(): MutableList<Expense> {
        val month1 = DefaultTimeProvider(
            Clock.fixed(Instant.parse(MONTH1), ZoneId.systemDefault())
        ).now().millis()
        val month2 = DefaultTimeProvider(
            Clock.fixed(Instant.parse(MONTH2), ZoneId.systemDefault())
        ).now().millis()
        val month3 = DefaultTimeProvider(
            Clock.fixed(Instant.parse(MONTH3), ZoneId.systemDefault())
        ).now().millis()
        val dataToInsert = mutableListOf<Expense>().apply {
            add(Expense("monthly, current", "100", true, month1, month1))
            add(Expense("no monthly, current", "100", false, month1, month1))
            add(Expense("monthly, next month", "100", true, month1, month2))
            add(Expense("no monthly, next month", "100", false, month1, month2))
            add(Expense("monthly, next month", "100", true, month1, month3))
            add(Expense("no monthly, next month", "100", false, month1, month3))
        }
        return dataToInsert
    }
}