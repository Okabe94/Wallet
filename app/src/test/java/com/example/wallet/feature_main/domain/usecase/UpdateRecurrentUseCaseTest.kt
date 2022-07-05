package com.example.wallet.feature_main.domain.usecase

import com.example.wallet.core.data.preferences.FakeApplicationPreferences
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.core.util.TestingConstants.MONTH1
import com.example.wallet.core.util.createFakeTimeProvider
import com.example.wallet.feature_main.data.repository.FakeRecurrentRepository
import com.example.wallet.feature_main.data.time.DefaultTimeProvider
import com.example.wallet.feature_main.data.time.FakeTimeComparator
import com.example.wallet.feature_main.data.time.FaultyTimeComparator
import com.example.wallet.feature_main.domain.repository.RecurrentRepository
import com.example.wallet.feature_main.domain.time.TimeComparator
import com.example.wallet.feature_main.domain.time.TimeProvider
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class UpdateRecurrentUseCaseTest {

    private lateinit var dummyData: MutableList<Expense>
    private lateinit var fakeTimeComparator: TimeComparator
    private lateinit var fakeRepository: FakeRecurrentRepository
    private lateinit var fakePreferences: ApplicationPreferences

    @Before
    fun setUp() {
        dummyData = createDummyData()
        fakeTimeComparator = FakeTimeComparator()
        fakeRepository = FakeRecurrentRepository()
        fakePreferences = FakeApplicationPreferences(1L)
    }

    @Test
    fun `Months elapsed - Months were updated`(): Unit = runBlocking {
        val monthsElapsed = 2L
        val provider = createFakeTimeProvider(MONTH1)
        val useCase = createFakeUpdateRecurrentUseCase(
            provider, timeComparator = FakeTimeComparator(months = monthsElapsed)
        )

        useCase(this, dummyData)
        val newList = fakeRepository.updatedList

        assertThat(dummyData.size).isEqualTo(newList.size)
        repeat(dummyData.size) {
            val months = dummyData[it].months + monthsElapsed
            assertThat(newList[it].months).isEqualTo(months)
        }
    }

    @Test
    fun `Months elapsed - UpdatedUntil is updated`(): Unit = runBlocking {
        val monthsElapsed = 2L
        val provider = createFakeTimeProvider(MONTH1)
        val useCase = createFakeUpdateRecurrentUseCase(
            provider, timeComparator = FakeTimeComparator(months = monthsElapsed)
        )

        useCase(this, dummyData)
        val newList = fakeRepository.updatedList

        assertThat(dummyData.size).isEqualTo(newList.size)
        repeat(dummyData.size) {
            assertThat(dummyData[it].updatedUntil).isLessThan(newList[it].updatedUntil)
        }
    }

    @Test
    fun `Months elapsed - Preferences updated`(): Unit = runBlocking {
        val provider = createFakeTimeProvider(MONTH1)
        val beforeUpdateMillis = provider.now().millis()
        val beforeUpdatePreferencesLastUpdate = fakePreferences.getLastUpdated()

        val useCase = createFakeUpdateRecurrentUseCase(provider)
        useCase(this, dummyData)

        assertThat(fakePreferences.getLastUpdated()).isGreaterThan(beforeUpdatePreferencesLastUpdate)
        assertThat(fakePreferences.getLastUpdated()).isEqualTo(beforeUpdateMillis)
    }

    @Test
    fun `No month elapsed - Data is the same`(): Unit = runBlocking {
        val provider = createFakeTimeProvider(MONTH1)
        val useCase = createFakeUpdateRecurrentUseCase(
            provider, timeComparator = FakeTimeComparator(0, 0)
        )

        useCase(this, dummyData)
        val newList = fakeRepository.updatedList

        assertThat(dummyData.size).isEqualTo(newList.size)
        repeat(dummyData.size) {
            val currentDummy = dummyData[it]
            val currentUpdated = newList[it]
            assertThat(currentDummy.name).isEqualTo(currentUpdated.name)
            assertThat(currentDummy.id).isEqualTo(currentUpdated.id)
            assertThat(currentDummy.months).isEqualTo(currentUpdated.months)
            assertThat(currentDummy.amount).isEqualTo(currentUpdated.amount)
            assertThat(currentDummy.createdAt).isEqualTo(currentUpdated.createdAt)
            assertThat(currentDummy.isMonthly).isEqualTo(currentUpdated.isMonthly)
        }
    }

    @Test(expected = Exception::class)
    fun `Months elapsed - Catch exception`(): Unit = runBlocking {
        val provider = createFakeTimeProvider(MONTH1)
        val useCase = createFakeUpdateRecurrentUseCase(
            provider, timeComparator = FaultyTimeComparator()
        )
        useCase(this, dummyData)
    }

    @Test
    fun `Months elapsed - Data not altered`(): Unit = runBlocking {
        val monthsElapsed = 2L
        val provider = createFakeTimeProvider(MONTH1)
        val useCase = createFakeUpdateRecurrentUseCase(
            provider, timeComparator = FakeTimeComparator(months = monthsElapsed)
        )

        useCase(this, dummyData)
        val newList = fakeRepository.updatedList

        assertThat(dummyData.size).isEqualTo(newList.size)
        repeat(dummyData.size) {
            val currentDummy = dummyData[it]
            val currentUpdated = newList[it]
            assertThat(currentDummy.name).isEqualTo(currentUpdated.name)
            assertThat(currentDummy.id).isEqualTo(currentUpdated.id)
            assertThat(currentDummy.amount).isEqualTo(currentUpdated.amount)
            assertThat(currentDummy.createdAt).isEqualTo(currentUpdated.createdAt)
            assertThat(currentDummy.isMonthly).isEqualTo(currentUpdated.isMonthly)

            assertThat(currentDummy.updatedUntil).isNotEqualTo(currentUpdated.updatedUntil)
        }
    }

    private fun createFakeUpdateRecurrentUseCase(
        timeProvider: TimeProvider,
        repo: RecurrentRepository = fakeRepository,
        preferences: ApplicationPreferences = fakePreferences,
        timeComparator: TimeComparator = fakeTimeComparator
    ) = UpdateRecurrentUseCase(repo, preferences, timeProvider, timeComparator)

    private fun createDummyData(month: String = MONTH1): MutableList<Expense> {
        val month1 = createFakeTimeProvider(month).now().millis()
        return mutableListOf<Expense>().apply {
            add(Expense("monthly, current", "100", true, month1, month1, id = 1))
            add(Expense("monthly, next month", "100", true, month1, month1, id = 2))
            add(Expense("monthly, next month", "100", true, month1, month1, 1, 3))
        }
    }
}