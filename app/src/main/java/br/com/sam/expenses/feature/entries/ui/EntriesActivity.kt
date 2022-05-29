package br.com.sam.expenses.feature.entries.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R
import br.com.sam.expenses.feature.detailedentries.ui.DetailedEntriesActivity
import br.com.sam.expenses.feature.detailedentries.ui.DetailedEntriesActivity.Companion.DETAILS_BUNDLE
import br.com.sam.expenses.feature.detailedentries.ui.DetailedEntriesActivity.Companion.ENTRIES_EXTRA
import br.com.sam.expenses.feature.entries.model.Entry
import br.com.sam.expenses.commons.providers.provideEntryRepository
import br.com.sam.expenses.feature.entries.viewmodel.EntriesViewModel
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
        fetchEntries()
    }

    private fun fetchEntries() {

        val viewModel: EntriesViewModel =
            EntriesViewModel.EntriesViewModelFactory(provideEntryRepository())
                .create(EntriesViewModel::class.java)

        viewModel.entriesLiveData.observe(this, { entryList ->
            entryList?.let { entries ->
                setupAdapter(entries)
                setupViews(entries)
            }
        })

        viewModel.errorLiveData.observe(this, { error ->
            error?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
        viewModel.getEntries()
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