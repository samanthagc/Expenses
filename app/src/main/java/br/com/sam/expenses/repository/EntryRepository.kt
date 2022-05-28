package br.com.sam.expenses.repository

import android.util.Log
import br.com.sam.expenses.api.EntryApi
import br.com.sam.expenses.api.EntryService
import br.com.sam.expenses.model.Entry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class EntryRepository() {
    lateinit var res : List<Entry>

    fun fetchEntries() : List<Entry> {

        val call = EntryApi().entryService().fetchEntries()
        call.enqueue(object: Callback<List<Entry>?> {
            override fun onFailure(call: Call<List<Entry>?>, t: Throwable) {
                Log.e("Entries API Error", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<Entry>?>,
                response: Response<List<Entry>?>
            ) {
                response.body()?.let {
                    val responses: List<Entry> = it
                    res = responses
                }
            }
        })

        return res
    }
}