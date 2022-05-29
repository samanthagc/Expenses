package br.com.sam.expenses.feature.entries.repository

import br.com.sam.expenses.commons.api.EntriesResponseState

class MockEntriesRepository(private val responseState: EntriesResponseState) : EntryRepository {
    override fun fetchEntries(callback: (result: EntriesResponseState) -> Unit) {
        callback(responseState)
    }
}