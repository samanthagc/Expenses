package br.com.sam.expenses.extensions

internal fun Double.toBrazilianCurrencyFormat(): String {
    return "R$ " + this.toString().replace(".", ",")
}