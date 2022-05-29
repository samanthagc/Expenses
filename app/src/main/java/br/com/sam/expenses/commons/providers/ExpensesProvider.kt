package br.com.sam.expenses.commons.providers

import br.com.sam.expenses.commons.api.ExpensesApi
import br.com.sam.expenses.feature.detailedentries.repository.CategoryDataSource
import br.com.sam.expenses.feature.entries.repository.EntryDataSource

internal fun provideEntriesService() = ExpensesApi().entryService()

internal fun provideCategoriesService() = ExpensesApi().categoryService()

internal fun provideEntryRepository() = EntryDataSource(provideEntriesService())

internal fun provideCategoryRepository() = CategoryDataSource(provideCategoriesService())
