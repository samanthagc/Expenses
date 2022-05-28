package br.com.sam.expenses.commons

internal fun Double.toBrazilianCurrencyFormat(): String {
    return "R$ " + this.toString().replace(".", ",")
}