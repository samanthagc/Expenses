package br.com.sam.expenses.feature.detailedentries.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R
import br.com.sam.expenses.commons.Constants
import br.com.sam.expenses.commons.toBrazilianCurrencyFormat
import br.com.sam.expenses.feature.detailedentries.model.Category

class CategoryAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindView(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val icon = view.findViewById<TextView>(R.id.tv_icon)
        private val name = view.findViewById<TextView>(R.id.tv_category)
        private val value = view.findViewById<TextView>(R.id.tv_value)

        fun bindView(category: Category) {
            icon.text = getIcon(category.id )
            name.text = category.name
            value.text = category.amout.toBrazilianCurrencyFormat()
        }

        private fun getIcon(category: Int) = when (category) {
            Constants.CAR -> view.context.getString(R.string.icon_car)
            Constants.INTERNET -> view.context.getString(R.string.icon_internet)
            Constants.HEATH_AND_CARE -> view.context.getString(R.string.icon_care)
            Constants.TOOLS -> view.context.getString(R.string.icon_tools)
            Constants.RESTAURANT -> view.context.getString(R.string.icon_restaurant)
            Constants.MARKET -> view.context.getString(R.string.icon_market)
            else -> view.context.getString(R.string.icon_card)
        }
    }
}