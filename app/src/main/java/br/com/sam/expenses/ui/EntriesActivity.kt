package br.com.sam.expenses.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R
import br.com.sam.expenses.api.EntryApi
import br.com.sam.expenses.model.Entry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntriesActivity : AppCompatActivity() {

    private val entriesList: RecyclerView by lazy {
        findViewById(R.id.rv_entries)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entries)
        callApi()
    }

    private fun callApi() {

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
                    setupAdapter(it)
                }
            }
        })
    }

    private fun setupAdapter(res: List<Entry>) {
        entriesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        entriesList.adapter = EntriesAdapter(res)
    }

}