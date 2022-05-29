package br.com.sam.expenses.commons.providers

import br.com.sam.expenses.commons.api.ExpensesApi
import br.com.sam.expenses.feature.detailedentries.repository.CategoryRepository
import br.com.sam.expenses.feature.entries.repository.EntryRepository

internal fun provideEntriesService() = ExpensesApi().entryService()

internal fun provideCategoriesService() = ExpensesApi().categoryService()

internal fun provideEntryRepository() = EntryRepository(provideEntriesService())

internal fun provideCategoryRepository() = CategoryRepository(provideCategoriesService())
