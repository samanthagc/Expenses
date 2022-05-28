package br.com.sam.expenses.feature.entries.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R
import br.com.sam.expenses.constants.Constants.APR
import br.com.sam.expenses.constants.Constants.AUG
import br.com.sam.expenses.constants.Constants.CAR
import br.com.sam.expenses.constants.Constants.CARD
import br.com.sam.expenses.constants.Constants.DEC
import br.com.sam.expenses.constants.Constants.FEB
import br.com.sam.expenses.constants.Constants.HEATH_AND_CARE
import br.com.sam.expenses.constants.Constants.INTERNET
import br.com.sam.expenses.constants.Constants.JAN
import br.com.sam.expenses.constants.Constants.JUL
import br.com.sam.expenses.constants.Constants.JUN
import br.com.sam.expenses.constants.Constants.MAR
import br.com.sam.expenses.constants.Constants.MARKET
import br.com.sam.expenses.constants.Constants.MAY
import br.com.sam.expenses.constants.Constants.NOV
import br.com.sam.expenses.constants.Constants.NULL_MONTH
import br.com.sam.expenses.constants.Constants.OCT
import br.com.sam.expenses.constants.Constants.RESTAURANT
import br.com.sam.expenses.constants.Constants.SEP
import br.com.sam.expenses.constants.Constants.TOOLS
import br.com.sam.expenses.extensions.toBrazilianCurrencyFormat
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
            month.text = getMonth(entry.month ?: NULL_MONTH)
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

        private fun getMonth(month: Int) = when (month) {
            JAN -> view.context.getString(R.string.month_jan)
            FEB -> view.context.getString(R.string.month_feb)
            MAR -> view.context.getString(R.string.month_mar)
            APR -> view.context.getString(R.string.month_apr)
            MAY -> view.context.getString(R.string.month_may)
            JUN -> view.context.getString(R.string.month_jun)
            JUL -> view.context.getString(R.string.month_jul)
            AUG -> view.context.getString(R.string.month_aug)
            SEP -> view.context.getString(R.string.month_sep)
            OCT -> view.context.getString(R.string.month_oct)
            NOV -> view.context.getString(R.string.month_nov)
            DEC -> view.context.getString(R.string.month_dec)
            else -> view.context.getString(R.string.empty_value)
        }
    }
}