package br.com.sam.expenses.feature.entries.service

import br.com.sam.expenses.feature.entries.model.Entry
import retrofit2.Call
import retrofit2.http.GET

interface EntryService {

    @GET("lancamentos")
    fun fetchEntries() : Call<List<Entry>>

}