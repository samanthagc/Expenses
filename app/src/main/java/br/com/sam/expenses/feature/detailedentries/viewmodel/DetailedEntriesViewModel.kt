package br.com.sam.expenses.feature.detailedentries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.sam.expenses.commons.api.CategoriesResponseState
import br.com.sam.expenses.feature.detailedentries.model.CategoryDTO
import br.com.sam.expenses.feature.detailedentries.repository.CategoryRepository

class DetailedEntriesViewModel(private val repository: CategoryRepository) : ViewModel() {

    private val categoryList: MutableLiveData<List<CategoryDTO>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()

    val categoriesLiveData: LiveData<List<CategoryDTO>> = categoryList
    val errorLiveData: LiveData<String> = errorMessage

    fun getCategories() {
        repository.fetchCategories { result ->
            when (result) {
                is CategoriesResponseState.Success -> {
                    categoryList.value = result.categories
                }
                is CategoriesResponseState.Error -> {
                    errorMessage.value = result.e.message
                }
            }
        }
    }


    class DetailViewModelFactory(private val repository: CategoryRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailedEntriesViewModel(repository) as T
        }
    }
}