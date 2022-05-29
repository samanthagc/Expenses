package br.com.sam.expenses.feature.detailedentries.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.sam.expenses.R
import br.com.sam.expenses.commons.toBrazilianCurrencyFormat
import br.com.sam.expenses.feature.detailedentries.model.Month

class MonthAdapter (private val months: List<Month>) :
    RecyclerView.Adapter<MonthAdapter.MonthViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.month_item, parent, false)
        return MonthViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.bindView(months[position])
    }

    override fun getItemCount(): Int = months.size

    inner class MonthViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.tv_month)
        private val value = view.findViewById<TextView>(R.id.tv_value)

        fun bindView(month: Month) {
            name.text = month.name
            value.text = month.amout.toBrazilianCurrencyFormat()
        }

    }
}