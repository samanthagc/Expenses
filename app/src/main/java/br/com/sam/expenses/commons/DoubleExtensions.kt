package br.com.sam.expenses.commons

import br.com.sam.expenses.commons.Constants.BR
import br.com.sam.expenses.commons.Constants.PT
import java.text.NumberFormat
import java.util.Locale

internal fun Double.toBrazilianCurrencyFormat(): String {
    return NumberFormat.getCurrencyInstance(Locale(PT, BR)).format(this)
}