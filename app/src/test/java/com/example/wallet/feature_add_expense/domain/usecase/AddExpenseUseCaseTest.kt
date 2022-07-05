package com.example.wallet.feature_add_expense.domain.usecase

import com.example.wallet.core.util.TestingConstants.MONTH1
import com.example.wallet.core.util.createFakeTimeProvider
import com.example.wallet.feature_add_expense.data.repository.FakeCreateExpenseRepository
import com.example.wallet.feature_add_expense.data.repository.FaultyCreateExpenseRepository
import com.example.wallet.feature_add_expense.presentation.state.AddExpenseState
import com.example.wallet.feature_main.domain.time.TimeProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

private const val name = "NAME"
private const val amount = "1000"
private const val monthly = true

class AddExpenseUseCaseTest {

    private lateinit var useCase: AddExpenseUseCase
    private lateinit var fakeTimeProvider: TimeProvider
    private lateinit var fakeAddExpenseState: AddExpenseState
    private lateinit var fakeRepository: FakeCreateExpenseRepository

    @Before
    fun setUp() {
        fakeRepository = FakeCreateExpenseRepository()
        fakeTimeProvider = createFakeTimeProvider(MONTH1)
        fakeAddExpenseState = AddExpenseState(name, amount, monthly)
        useCase = AddExpenseUseCase(fakeRepository, fakeTimeProvider)
    }

    @Test
    fun `Data is being created correctly`(): Unit = runBlocking {
        val time = fakeTimeProvider.now()
        val created = time.millis()
        val updatedUntil = time.rollMonths().millis()

        useCase(fakeAddExpenseState)

        val result = fakeRepository.list
        assertThat(result).isNotEmpty()
        assertThat(result).hasSize(1)

        val item = result.first()
        assertThat(item.createdAt).isEqualTo(created)
        assertThat(item.updatedUntil).isEqualTo(updatedUntil)
        assertThat(item.name).isEqualTo(fakeAddExpenseState.name)
        assertThat(item.amount).isEqualTo(fakeAddExpenseState.amount)
        assertThat(item.isMonthly).isEqualTo(fakeAddExpenseState.isMonthly)
    }

    @Test
    fun `Data is being added - Returns true`(): Unit = runBlocking {
        val result = useCase(fakeAddExpenseState)
        assertThat(result).isTrue()
    }

    @Test
    fun `Problem with data - Returns false`(): Unit = runBlocking {
        useCase = AddExpenseUseCase(FaultyCreateExpenseRepository(), fakeTimeProvider)
        val result = useCase(fakeAddExpenseState)
        assertThat(result).isFalse()
    }
}