package br.com.sam.expenses.commons

import br.com.sam.expenses.feature.detailedentries.service.CategoryService
import br.com.sam.expenses.feature.entries.service.EntryService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExpensesApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://desafio-it-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun entryService(): EntryService = retrofit.create(EntryService::class.java)
    fun categoryService(): CategoryService = retrofit.create(CategoryService::class.java)
}