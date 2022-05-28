package br.com.sam.expenses.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R

class EntriesActivity : AppCompatActivity() {

    lateinit var entriesList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entries)
        initViews()
        setAdapter()
    }

    private fun initViews() {
        entriesList = findViewById(R.id.rv_entries)
    }

    private fun setAdapter() {

    }

}