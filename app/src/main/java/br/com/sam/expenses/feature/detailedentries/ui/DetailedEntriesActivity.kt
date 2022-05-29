package br.com.sam.expenses.feature.detailedentries.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R
import br.com.sam.expenses.commons.providers.provideCategoryRepository
import br.com.sam.expenses.feature.detailedentries.model.Category
import br.com.sam.expenses.feature.detailedentries.model.CategoryDTO
import br.com.sam.expenses.feature.detailedentries.utils.MonthsStub.getMonths
import br.com.sam.expenses.feature.detailedentries.viewmodel.DetailedEntriesViewModel
import br.com.sam.expenses.feature.entries.model.Entry

class DetailedEntriesActivity : AppCompatActivity() {

    private lateinit var entriesData: List<Entry>

    private val categoriesList: RecyclerView by lazy {
        findViewById(R.id.rv_categories)
    }

    private val monthsList: RecyclerView by lazy {
        findViewById(R.id.rv_months)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_entries)
        configToolbar()
        getBundle()
        fetchCategories()
        setupMonthsAdapter()
    }

    private fun configToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getBundle() {
        val args = intent.getBundleExtra(DETAILS_BUNDLE)
        entriesData = args!!.getSerializable(ENTRIES_EXTRA) as List<Entry>? ?: listOf()
    }

    private fun fetchCategories() {

        val viewModel: DetailedEntriesViewModel =
            DetailedEntriesViewModel.DetailViewModelFactory(provideCategoryRepository())
                .create(DetailedEntriesViewModel::class.java)

        viewModel.categoriesLiveData.observe(this, { entryList ->
            entryList?.let {
                setupCategoriesAdapter(it)
            }
        })

        viewModel.errorLiveData.observe(this, { error ->
            error?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT)
                    .show()
            }
        })

        viewModel.getCategories()
    }

    private fun setupCategoriesAdapter(data: List<CategoryDTO>) {
        categoriesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        categoriesList.adapter = CategoryAdapter(mapCategoriesDTOtoCategoriesModeled(data))
    }

    private fun setupMonthsAdapter() {
        monthsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        monthsList.adapter = MonthAdapter(getMonths(entriesData, this))
    }

    private fun mapCategoriesDTOtoCategoriesModeled(listDTO: List<CategoryDTO>) : ArrayList<Category> {
        val mappedList = ArrayList<Category>()
        listDTO.forEach {
            mappedList.add(mapCategoriesModeled(it))
        }
        return mappedList
    }

    private fun mapCategoriesModeled(response: CategoryDTO) =
        Category().apply {
            id = response.id ?: 0
            name = response.name ?: ""
            amout = getCategoryAmout(response.id)
        }

    private fun getCategoryAmout(category: Int?) : Double {
        var amout = 0.0
        entriesData.forEach { entry ->
            if (category == entry.category){
                amout += entry.value ?: 0.0
            }
        }
        return amout
    }


    companion object {
        const val ENTRIES_EXTRA = "entries_extra"
        const val DETAILS_BUNDLE = "details_bundle"
    }
}