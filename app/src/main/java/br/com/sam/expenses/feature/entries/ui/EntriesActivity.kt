package br.com.sam.expenses.feature.entries.ui

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R
import br.com.sam.expenses.commons.ExpensesApi
import br.com.sam.expenses.feature.detailedentries.ui.DetailedEntriesActivity
import br.com.sam.expenses.feature.detailedentries.ui.DetailedEntriesActivity.Companion.DETAILS_BUNDLE
import br.com.sam.expenses.feature.detailedentries.ui.DetailedEntriesActivity.Companion.ENTRIES_EXTRA
import br.com.sam.expenses.feature.entries.model.Entry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class EntriesActivity : AppCompatActivity() {

    private val entriesList: RecyclerView by lazy {
        findViewById(R.id.rv_entries)
    }

    private val detailButton: Button by lazy {
        findViewById(R.id.bt_detailed)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entries)
        callApi()
    }

    private fun callApi() {

        val call = ExpensesApi().entryService().fetchEntries()

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
                    setupViews(it)
                }
            }
        })
    }

    private fun setupAdapter(data: List<Entry>) {
        entriesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        entriesList.adapter = EntriesAdapter(data)
    }

    private fun setupViews(data: List<Entry>) {
        detailButton.setOnClickListener {
            val intent = Intent(this, DetailedEntriesActivity::class.java)
            val args = Bundle()
            args.putSerializable(ENTRIES_EXTRA, data as Serializable)
            intent.putExtra(DETAILS_BUNDLE, args)
            startActivity(intent)
        }
    }

}