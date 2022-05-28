package br.com.sam.expenses.api

import br.com.sam.expenses.model.Entry
import retrofit2.Call
import retrofit2.http.GET

interface EntryService {

    @GET("lancamentos")
    fun fetchEntries() : Call<List<Entry>>

}