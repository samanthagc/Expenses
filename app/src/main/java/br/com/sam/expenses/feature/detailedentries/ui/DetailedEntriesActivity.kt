package br.com.sam.expenses.feature.detailedentries.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R
import br.com.sam.expenses.commons.ExpensesApi
import br.com.sam.expenses.feature.detailedentries.model.Category
import br.com.sam.expenses.feature.entries.model.Entry
import br.com.sam.expenses.feature.entries.ui.EntriesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailedEntriesActivity : AppCompatActivity() {

    private lateinit var entriesData: List<Entry>

    private val categoriesList: RecyclerView by lazy {
        findViewById(R.id.rv_categories)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_entries)
        getBundle()
        callApi()
    }

    private fun getBundle() {
        val args = intent.getBundleExtra(DETAILS_BUNDLE)
        entriesData = args!!.getSerializable(ENTRIES_EXTRA) as List<Entry>? ?: listOf()
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
                    setupAdapter(it)
                }
            }
        })
    }

    private fun setupAdapter(data: List<Category>) {
        categoriesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        categoriesList.adapter = CategoryAdapter(data)
    }

    companion object {
        const val ENTRIES_EXTRA = "entries_extra"
        const val DETAILS_BUNDLE = "details_bundle"
    }
}