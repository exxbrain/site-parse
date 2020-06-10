package model

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class Price(private val price: BigDecimal) {
    constructor(price: String) : this(price.toBigDecimal())
    constructor(price: Double) : this(price.toBigDecimal())
    constructor(price: Int) : this(price.toBigDecimal())

    fun calculate(multiplier: Double = 1.0): BigDecimal {
        var res = price.multiply(multiplier.toBigDecimal())
            .divide(100.toBigDecimal(), 1, RoundingMode.HALF_EVEN)
        val integer = res.toBigInteger()
        val fraction = res.minus(integer.toBigDecimal())
        if (fraction >= 0.5.toBigDecimal()) {
            res = integer.plus(1.toBigInteger()).toBigDecimal()
        } else if (fraction > 0.0.toBigDecimal()) {
            res = integer.toBigDecimal().plus(0.5.toBigDecimal())
        }
        return res.multiply(100.toBigDecimal()).setScale(0)
    }

    companion object {
        val ZERO
            get() = Price(0.0)
    }
}