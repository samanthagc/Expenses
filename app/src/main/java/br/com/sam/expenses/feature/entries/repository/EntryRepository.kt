package br.com.sam.expenses.feature.entries.repository

import br.com.sam.expenses.commons.api.EntriesResponseState

interface EntryRepository {
    fun fetchEntries(callback: (result: EntriesResponseState) -> Unit)
}