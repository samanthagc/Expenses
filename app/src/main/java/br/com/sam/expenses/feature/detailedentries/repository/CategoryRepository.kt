package br.com.sam.expenses.feature.detailedentries.repository

import br.com.sam.expenses.commons.api.CategoriesResponseState

interface CategoryRepository {
    fun fetchCategories(callback: (result: CategoriesResponseState) -> Unit)
}