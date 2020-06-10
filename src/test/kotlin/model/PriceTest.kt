package model

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.RoundingMode

internal class PriceTest {

    @Test
    fun calculate() {
        var price = Price(1021)
        assertEquals(1050.toBigDecimal(), price.calculate())

        price = Price(1040)
        assertEquals(1050.toBigDecimal(), price.calculate())

        price = Price(1080)
        assertEquals(1100.toBigDecimal(), price.calculate())
    }
}