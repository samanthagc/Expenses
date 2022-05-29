package br.com.sam.expenses.feature.entries.repository

import br.com.sam.expenses.commons.api.EntriesResponseState
import br.com.sam.expenses.feature.entries.model.Entry
import br.com.sam.expenses.feature.entries.service.EntryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntryRepository(private val service: EntryService) {

    fun fetchEntries(callback: (result: EntriesResponseState) -> Unit) {
        service.fetchEntries().enqueue(object : Callback<List<Entry>> {
            override fun onFailure(call: Call<List<Entry>>, t: Throwable) {
                callback(EntriesResponseState.Error(e = Exception("Fetch entries failed")))
            }

            override fun onResponse(call: Call<List<Entry>>, response: Response<List<Entry>>) {
                callback(EntriesResponseState.Success(response.body()!!))
            }
        })
    }
}
