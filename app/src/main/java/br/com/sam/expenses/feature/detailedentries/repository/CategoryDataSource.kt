package br.com.sam.expenses.feature.detailedentries.repository

import br.com.sam.expenses.commons.api.CategoriesResponseState
import br.com.sam.expenses.feature.detailedentries.model.CategoryDTO
import br.com.sam.expenses.feature.detailedentries.service.CategoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDataSource(private val service: CategoryService) : CategoryRepository {

    override fun fetchCategories(callback: (result: CategoriesResponseState) -> Unit) {
        service.fetchCategories().enqueue(object : Callback<List<CategoryDTO>> {
            override fun onFailure(call: Call<List<CategoryDTO>>, t: Throwable) {
                callback(CategoriesResponseState.Error(e = Exception("Fetch categories failed")))
            }

            override fun onResponse(call: Call<List<CategoryDTO>>, response: Response<List<CategoryDTO>>) {
                callback(CategoriesResponseState.Success(response.body()!!))
            }
        })
    }
}