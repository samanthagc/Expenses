package br.com.sam.expenses.commons

import org.junit.Assert
import org.junit.Test

class DoubleExtensionsTest {

    @Test
    fun givenADoubleValueWithMoreThanTwoDecimalPlacesMustReturnAStringInBrazilianCurrencyFormat() {
        Assert.assertEquals(
            "R$\u00A022,19",
            22.1901.toBrazilianCurrencyFormat()
        )
    }

    @Test
    fun givenADoubleValueWithNoDecimalPlacesMustReturnAStringInBrazilianCurrencyFormat() {
        Assert.assertEquals(
            "R$\u00A022,00",
            22.00.toBrazilianCurrencyFormat()
        )
    }

    @Test
    fun givenADoubleValueWithTwoDecimalPlacesMustReturnAStringInBrazilianCurrencyFormat() {
        Assert.assertEquals(
            "R$\u00A022,11",
            22.11.toBrazilianCurrencyFormat()
        )
    }

    @Test
    fun givenADoubleValueWithOneDecimalPlacesMustReturnAStringInBrazilianCurrencyFormat() {
        Assert.assertEquals(
            "R$\u00A022,10",
            22.1.toBrazilianCurrencyFormat()
        )
    }
}