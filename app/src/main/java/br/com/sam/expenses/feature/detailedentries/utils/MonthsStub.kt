package br.com.sam.expenses.feature.detailedentries.utils

import android.content.Context
import br.com.sam.expenses.R
import br.com.sam.expenses.commons.Constants.JAN
import br.com.sam.expenses.commons.Constants.FEB
import br.com.sam.expenses.commons.Constants.MAR
import br.com.sam.expenses.commons.Constants.APR
import br.com.sam.expenses.commons.Constants.MAY
import br.com.sam.expenses.commons.Constants.JUN
import br.com.sam.expenses.commons.Constants.JUL
import br.com.sam.expenses.commons.Constants.AUG
import br.com.sam.expenses.commons.Constants.DEC
import br.com.sam.expenses.commons.Constants.NOV
import br.com.sam.expenses.commons.Constants.OCT
import br.com.sam.expenses.commons.Constants.SEP
import br.com.sam.expenses.feature.detailedentries.model.Month
import br.com.sam.expenses.feature.entries.model.Entry

object MonthsStub {

    fun getMonths(entries: List<Entry>, context: Context) : List<Month> {
        return listOf(
            Month(JAN, getMonth(context, JAN), getMonthAmout(entries, JAN)),
            Month(FEB, getMonth(context, FEB), getMonthAmout(entries, FEB)),
            Month(MAR, getMonth(context, MAR), getMonthAmout(entries, MAR)),
            Month(APR, getMonth(context, APR), getMonthAmout(entries, APR)),
            Month(MAY, getMonth(context, MAY), getMonthAmout(entries, MAY)),
            Month(JUN, getMonth(context, JUN), getMonthAmout(entries, JUN)),
            Month(JUL, getMonth(context, JUL), getMonthAmout(entries, JUL)),
            Month(AUG, getMonth(context, AUG), getMonthAmout(entries, AUG)),
            Month(SEP, getMonth(context, SEP), getMonthAmout(entries, SEP)),
            Month(OCT, getMonth(context, OCT), getMonthAmout(entries, OCT)),
            Month(NOV, getMonth(context, NOV), getMonthAmout(entries, NOV)),
            Month(DEC, getMonth(context, DEC), getMonthAmout(entries, DEC))
        )
    }

    private fun getMonthAmout(entries: List<Entry>, month: Int) : Double {
        var amout = 0.0
        entries.forEach { entry ->
            if (month == entry.month){
                amout += entry.value ?: 0.0
            }
        }
        return amout
    }

    fun getMonth(context: Context, month: Int) = when (month) {
        JAN -> context.getString(R.string.month_jan)
        FEB -> context.getString(R.string.month_feb)
        MAR -> context.getString(R.string.month_mar)
        APR -> context.getString(R.string.month_apr)
        MAY -> context.getString(R.string.month_may)
        JUN -> context.getString(R.string.month_jun)
        JUL -> context.getString(R.string.month_jul)
        AUG -> context.getString(R.string.month_aug)
        SEP -> context.getString(R.string.month_sep)
        OCT -> context.getString(R.string.month_oct)
        NOV -> context.getString(R.string.month_nov)
        DEC -> context.getString(R.string.month_dec)
        else -> context.getString(R.string.empty_value)
    }
}