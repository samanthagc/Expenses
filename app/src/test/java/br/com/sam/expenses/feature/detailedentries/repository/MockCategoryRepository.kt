package br.com.sam.expenses.feature.detailedentries.repository

import br.com.sam.expenses.commons.api.CategoriesResponseState

class MockCategoryRepository(private val responseState: CategoriesResponseState) : CategoryRepository {
    override fun fetchCategories(callback: (result: CategoriesResponseState) -> Unit) {
        callback(responseState)
    }
}