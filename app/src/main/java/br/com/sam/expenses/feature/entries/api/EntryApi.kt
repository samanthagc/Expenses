package br.com.sam.expenses.feature.entries.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EntryApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://desafio-it-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun entryService(): EntryService = retrofit.create(EntryService::class.java)
}