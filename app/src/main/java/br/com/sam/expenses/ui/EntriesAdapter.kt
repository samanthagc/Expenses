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

    inner class EntriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val icon = view.findViewById<TextView>(R.id.tv_icon)
        private val value = view.findViewById<TextView>(R.id.tv_value)
        private val origin = view.findViewById<TextView>(R.id.tv_origin)
        private val month = view.findViewById<TextView>(R.id.tv_month)

        fun bindView(entry: Entry) {
            icon.text = getIcon(entry.category ?: 7)
            value.text = entry.value?.toBrazilianCurrencyFormat() ?: "-"
            origin.text = entry.origin ?: "-"
            month.text = getMonth(entry.month ?: 13)
        }

        private fun getIcon(category: Int) = when (category) {
            1 -> "🚗"
            2 -> "🎮"
            3 -> "💳"
            4 -> "🛠"
            5 -> "🍴"
            6 -> "🛒"
            else -> "💳"
        }

        private fun getMonth(month: Int) = when (month) {
            1 -> "janeiro"
            2 -> "fevereiro"
            3 -> "março"
            4 -> "abril"
            5 -> "maio"
            6 -> "junho"
            7 -> "julho"
            8 -> "agosto"
            9 -> "setembro"
            10 -> "outubro"
            11 -> "novembro"
            12 -> "dezembro"
            else -> "-"
        }
    }
}