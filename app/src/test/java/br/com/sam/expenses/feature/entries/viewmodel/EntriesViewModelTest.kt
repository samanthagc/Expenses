package br.com.sam.expenses.feature.entries.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.sam.expenses.commons.api.EntriesResponseState
import br.com.sam.expenses.feature.entries.model.Entry
import br.com.sam.expenses.feature.entries.repository.MockEntriesRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import java.lang.Exception

@RunWith(JUnit4::class)
class EntriesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var entriesLiveDataObserver: Observer<List<Entry>>

    @Mock
    private lateinit var errorLiveDataObserver: Observer<String>

    private lateinit var viewModel: EntriesViewModel

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun whenSuccessGetEntriesThenSetsEntriesLiveData() {
        val entries = listOf(Entry(
            0,
            22.5,
            "Uber",
            1,
            2
        ))

        val resultSucess = MockEntriesRepository(EntriesResponseState.Success(entries))
        viewModel = EntriesViewModel(resultSucess)
        viewModel.entriesLiveData.observeForever(entriesLiveDataObserver)

        viewModel.getEntries()

        verify(entriesLiveDataObserver).onChanged(entries)
    }

    @Test
    fun whenErrorGetEntriesThenSetsEntriesLiveData() {
        val exception = Exception("failed")

        val resultError = MockEntriesRepository(EntriesResponseState.Error(exception))
        viewModel = EntriesViewModel(resultError)
        viewModel.errorLiveData.observeForever(errorLiveDataObserver)

        viewModel.getEntries()

        verify(errorLiveDataObserver).onChanged(exception.message)
    }
}