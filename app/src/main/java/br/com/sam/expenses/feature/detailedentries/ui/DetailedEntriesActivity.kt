package br.com.sam.expenses.feature.detailedentries.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.sam.expenses.R
import br.com.sam.expenses.commons.ExpensesApi
import br.com.sam.expenses.feature.detailedentries.model.Category
import br.com.sam.expenses.feature.entries.model.Entry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailedEntriesActivity : AppCompatActivity() {

    private lateinit var entriesData: ArrayList<Entry>
    private lateinit var eentriesData: List<Entry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_entries)
        getBundle()
        callApi()
    }

    private fun getBundle() {
        val args = intent.getBundleExtra(DETAILS_BUNDLE)
        eentriesData = args!!.getSerializable(ENTRIES_EXTRA) as List<Entry>? ?: listOf()
    }

    private fun callApi() {

        val call = ExpensesApi().categoryService().fetchCategories()

        call.enqueue(object: Callback<List<Category>?> {
            override fun onFailure(call: Call<List<Category>?>, t: Throwable) {
                Log.e("Category API Error", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<Category>?>,
                response: Response<List<Category>?>
            ) {
                response.body()?.let {
                    Log.e("Category API 200", it[0].name ?: "null")
                }
            }
        })
    }

    companion object {
        const val ENTRIES_EXTRA = "entries_extra"
        const val DETAILS_BUNDLE = "details_bundle"
    }
}