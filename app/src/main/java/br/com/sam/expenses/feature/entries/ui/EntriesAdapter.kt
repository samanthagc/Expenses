package br.com.sam.expenses.feature.entries.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R
import br.com.sam.expenses.commons.Constants.CAR
import br.com.sam.expenses.commons.Constants.CARD
import br.com.sam.expenses.commons.Constants.HEATH_AND_CARE
import br.com.sam.expenses.commons.Constants.INTERNET
import br.com.sam.expenses.commons.Constants.MARKET
import br.com.sam.expenses.commons.Constants.NULL_MONTH
import br.com.sam.expenses.commons.Constants.RESTAURANT
import br.com.sam.expenses.commons.Constants.TOOLS
import br.com.sam.expenses.commons.toBrazilianCurrencyFormat
import br.com.sam.expenses.feature.detailedentries.utils.MonthsStub.getMonth
import br.com.sam.expenses.feature.entries.model.Entry

class EntriesAdapter(private val entries: List<Entry>) :
    RecyclerView.Adapter<EntriesAdapter.EntriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntriesViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.entry_item, parent, false)
        return EntriesViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: EntriesViewHolder, position: Int) {
        holder.bindView(entries[position])
    }

    override fun getItemCount(): Int = entries.size

    inner class EntriesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val icon = view.findViewById<TextView>(R.id.tv_icon)
        private val value = view.findViewById<TextView>(R.id.tv_value)
        private val origin = view.findViewById<TextView>(R.id.tv_origin)
        private val month = view.findViewById<TextView>(R.id.tv_month)

        fun bindView(entry: Entry) {
            icon.text = getIcon(entry.category ?: CARD)
            value.text = entry.value?.toBrazilianCurrencyFormat() ?: view.context.getString(R.string.empty_value)
            origin.text = entry.origin ?: view.context.getString(R.string.empty_value)
            month.text = getMonth( view.context, entry.month ?: NULL_MONTH)
        }

        private fun getIcon(category: Int) = when (category) {
            CAR -> view.context.getString(R.string.icon_car)
            INTERNET -> view.context.getString(R.string.icon_internet)
            HEATH_AND_CARE -> view.context.getString(R.string.icon_care)
            TOOLS -> view.context.getString(R.string.icon_tools)
            RESTAURANT -> view.context.getString(R.string.icon_restaurant)
            MARKET -> view.context.getString(R.string.icon_market)
            else -> view.context.getString(R.string.icon_card)
        }
    }
}