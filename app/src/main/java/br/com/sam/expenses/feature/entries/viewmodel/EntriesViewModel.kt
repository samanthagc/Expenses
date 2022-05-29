package br.com.sam.expenses.feature.entries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.sam.expenses.commons.api.EntriesResponseState
import br.com.sam.expenses.feature.entries.model.Entry
import br.com.sam.expenses.feature.entries.repository.EntryRepository

class EntriesViewModel(private val repository: EntryRepository) : ViewModel() {

    private val entryList: MutableLiveData<List<Entry>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()

    val entriesLiveData: LiveData<List<Entry>> = entryList
    val errorLiveData: LiveData<String> = errorMessage

    fun getEntries() {
        repository.fetchEntries { result ->
            when (result) {
                is EntriesResponseState.Success -> {
                    entryList.value = result.entries
                }
                is EntriesResponseState.Error -> {
                    errorMessage.value = result.e.message
                }
            }
        }
    }


    class EntriesViewModelFactory(private val repository: EntryRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EntriesViewModel(repository) as T
        }
    }
}