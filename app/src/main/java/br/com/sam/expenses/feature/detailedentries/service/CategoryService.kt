package br.com.sam.expenses.feature.detailedentries.service

import br.com.sam.expenses.feature.detailedentries.model.CategoryDTO
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {

    @GET("categorias")
    fun fetchCategories() : Call<List<CategoryDTO>>
}