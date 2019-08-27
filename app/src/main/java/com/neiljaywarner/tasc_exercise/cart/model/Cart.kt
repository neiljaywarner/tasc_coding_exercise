package com.neiljaywarner.tasc_exercise.cart.model

import java.math.BigDecimal
import java.text.NumberFormat
import kotlin.math.ceil

data class Cart(val cartItems: List<CartItem>) {

    private val format : NumberFormat = NumberFormat.getCurrencyInstance()

    val salesTaxDisplayString: String
        get() = format.format(calculateTotalSalesTax().toDouble())

    val totalDisplayString: String
        get() = format.format(calculateTotal().toDouble())

    fun calculateTotalSalesTax() : BigDecimal {
        var totalSalesTax = BigDecimal.ZERO
        cartItems.forEach {
            totalSalesTax += it.salesTax + it.importTax
        }
        return totalSalesTax
    }

    fun calculateTotal() : BigDecimal {
        var total = BigDecimal.ZERO
        cartItems.forEach {
            total += it.price + it.salesTax + it.importTax

        }
        return total
    }

}

fun getCart(basketNum: Int) : Cart = when (basketNum) {
    1 -> basket1
    2 -> basket2
    else -> basket3
}

// Note: We could consider JodaMoney
data class CartItem(val itemName: String, val price: BigDecimal, val isImported: Boolean = false, val isExempt: Boolean = false) {
    val importTax : BigDecimal
        get() = if (isImported) {
            (price * BigDecimal("0.05")).roundUpNickel()
        } else {
            BigDecimal.ZERO
        }
    val salesTax : BigDecimal
        get() = if (isExempt) {
            BigDecimal.ZERO
        } else {
            (price * BigDecimal("0.10")).roundUpNickel()
        }

    val displayDescription
        get() = "$itemName:"

}

// convert to integer pennies
// then round up 1,2,3,4,5 to 5 to 5, 6,7,8,9,0 to 0 (+ in prev column)
// TODO: Refactor this with confidence becauase of unit test.
fun BigDecimal.roundUpNickel() : BigDecimal {
    val pennies = this.dollarsToPenniesRoundedUp()
    println("pennies = ${pennies.toPlainString()}")
    val nickels = pennies.penniesToNickelsRoundedUp()
    println("nickels = ${nickels.toPlainString()}")
    return nickels * BigDecimal("5.0").movePointLeft(2).stripTrailingZeros()
}

fun BigDecimal.dollarsToPenniesRoundedUp() : BigDecimal = this*BigDecimal("100").stripTrailingZeros()
fun BigDecimal.penniesToNickelsRoundedUp() : BigDecimal = ceil(this.toDouble() / 5.0).toBigDecimal().stripTrailingZeros()

// ***** Dummy Values
// ** Important note: Quantity is ignored for time's sake, everything considered qty: 1
val skittles = CartItem("1 16lb bag of Skittles", BigDecimal(16.00), isExempt = true)
val walkman = CartItem("1 Walkman", BigDecimal("99.99")) // tax $10
val popcorn = CartItem("1 bag of microwave popcorn", BigDecimal("0.99"), isExempt = true)

val basket1 = Cart(listOf(skittles, walkman, popcorn))

val coffee = CartItem("1 imported bag of Vanilla-Hazelnut Coffee", BigDecimal("11.00"), isImported = true, isExempt = true)
val vespa = CartItem("1 Imported Vespa", BigDecimal("15001.25"), isImported = true)

val basket2 = Cart(listOf(coffee, vespa))

val importedSnickers = CartItem("1 imported crate of Almond Snickers", BigDecimal("75.99"), isImported = true, isExempt = true)
val importedWine = CartItem("1 Imported Bottle of Wine", BigDecimal("10.00"), isImported = true)
val fairTradeCoffee = CartItem("1 300# bag of Fair-Trade Coffee", BigDecimal("997.99"), isExempt = true)
val discMan = CartItem("1 Discman", BigDecimal("55.00"))

val basket3 = Cart(listOf(importedSnickers, discMan, importedWine, fairTradeCoffee))

