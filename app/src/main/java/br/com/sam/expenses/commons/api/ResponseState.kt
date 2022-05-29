package br.com.sam.expenses.commons.api

import br.com.sam.expenses.feature.detailedentries.model.CategoryDTO
import br.com.sam.expenses.feature.entries.model.Entry

sealed class EntriesResponseState {
    data class Success(val entries: List<Entry>) : EntriesResponseState()
    data class Error(val e: Exception) : EntriesResponseState()
}

sealed class CategoriesResponseState {
    data class Success(val categories: List<CategoryDTO>) : CategoriesResponseState()
    data class Error(val e: Exception) : CategoriesResponseState()
}