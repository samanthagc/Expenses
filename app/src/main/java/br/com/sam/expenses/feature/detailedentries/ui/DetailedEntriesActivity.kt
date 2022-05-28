package br.com.sam.expenses.feature.detailedentries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.sam.expenses.R
import br.com.sam.expenses.commons.ExpensesApi
import br.com.sam.expenses.feature.detailedentries.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailedEntriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_entries)
        callApi()
    }

    private fun callApi() {

        val call = ExpensesApi().categoryService().fetchCategories()

        call.enqueue(object: Callback<List<Category>?> {
            override fun onFailure(call: Call<List<Category>?>, t: Throwable) {
                Log.e("Entries API Error", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<Category>?>,
                response: Response<List<Category>?>
            ) {
                response.body()?.let {
                    Log.e("Entries API 200", it[0].name ?: "null")
                }
            }
        })
    }
}