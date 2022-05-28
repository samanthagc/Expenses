package br.com.sam.expenses.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R
import br.com.sam.expenses.extensions.toBrazilianCurrencyFormat
import br.com.sam.expenses.model.Entry

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
            icon.text = getIcon(entry.category ?: 7)
            value.text = entry.value?.toBrazilianCurrencyFormat() ?: view.context.getString(R.string.empty_value)
            origin.text = entry.origin ?: view.context.getString(R.string.empty_value)
            month.text = getMonth(entry.month ?: 13)
        }

        private fun getIcon(category: Int) = when (category) {
            1 -> view.context.getString(R.string.icon_car)
            2 -> view.context.getString(R.string.icon_game)
            3 -> view.context.getString(R.string.icon_card)
            4 -> view.context.getString(R.string.icon_tools)
            5 -> view.context.getString(R.string.icon_restaurant)
            6 -> view.context.getString(R.string.icon_market)
            else -> view.context.getString(R.string.icon_card)
        }

        private fun getMonth(month: Int) = when (month) {
            1 -> view.context.getString(R.string.month_jan)
            2 -> view.context.getString(R.string.month_feb)
            3 -> view.context.getString(R.string.month_mar)
            4 -> view.context.getString(R.string.month_apr)
            5 -> view.context.getString(R.string.month_may)
            6 -> view.context.getString(R.string.month_jun)
            7 -> view.context.getString(R.string.month_jul)
            8 -> view.context.getString(R.string.month_aug)
            9 -> view.context.getString(R.string.month_sep)
            10 -> view.context.getString(R.string.month_oct)
            11 -> view.context.getString(R.string.month_nov)
            12 -> view.context.getString(R.string.month_dec)
            else -> view.context.getString(R.string.empty_value)
        }
    }
}