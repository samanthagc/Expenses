package br.com.sam.expenses.feature.detailedentries.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.sam.expenses.commons.api.CategoriesResponseState
import br.com.sam.expenses.feature.detailedentries.model.CategoryDTO
import br.com.sam.expenses.feature.detailedentries.repository.MockCategoryRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.MockitoAnnotations.initMocks
import java.lang.Exception

@RunWith(JUnit4::class)
class DetailedEntriesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var categoriesLiveDataObserver: Observer<List<CategoryDTO>>

    @Mock
    private lateinit var errorLiveDataObserver: Observer<String>

    private lateinit var viewModel: DetailedEntriesViewModel

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun whenSuccessGetCategoriesThenSetsCategoriesLiveData() {
        val categories = listOf(
            CategoryDTO(
            0,
            "Transporte"
            )
        )

        val resultSucess = MockCategoryRepository(CategoriesResponseState.Success(categories ))
        viewModel = DetailedEntriesViewModel(resultSucess)
        viewModel.categoriesLiveData.observeForever(categoriesLiveDataObserver)

        viewModel.getCategories()

        verify(categoriesLiveDataObserver).onChanged(categories)
    }

    @Test
    fun whenErrorGetCategoriesThenSetsCategoriesLiveData() {
        val exception = Exception("failed")

        val resultError = MockCategoryRepository(CategoriesResponseState.Error(exception))
        viewModel = DetailedEntriesViewModel(resultError)
        viewModel.errorLiveData.observeForever(errorLiveDataObserver)

        viewModel.getCategories()

        verify(errorLiveDataObserver).onChanged(exception.message)
    }
}