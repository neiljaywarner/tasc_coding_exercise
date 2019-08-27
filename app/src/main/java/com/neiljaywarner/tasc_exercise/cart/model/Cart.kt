package com.neiljaywarner.tasc_exercise.cart.model

import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import kotlin.math.ceil

data class Cart(val cartItems: List<CartItem>) {
    fun calculateTotalSalesTax() : Double {
        //TODO: Calculate sales tax
        return cartItems.sumByDouble {
            it.importTax + it.salesTax
        }
    }

    //TODO: Caculate
    fun calculateTotal() : Double = Double.MAX_VALUE
}

fun getBasket(basketNum: Int) : Cart = when (basketNum) {
    1 -> Cart(listOf(CartItem("item1", 1.2)))
    2 -> Cart(listOf(CartItem("item2", 3.2)))
    else -> Cart(listOf(CartItem("item3.1", 3.2), CartItem("item3.2", 1.2)))
}

// Note: We could consider JodaMoney
data class CartItem(val itemName: String, val price: Double, val isImported: Boolean = false, val isExempt: Boolean = false) {
    val importTax : Double
        get() {
            return if (isImported) {
                (price * 0.05).roundUpNickel()
            } else {
                0.0
            }
        }
    val salesTax : Double
        get() {
            return if (isExempt) {
                0.0
            } else {
                (price * 0.10).roundUpNickel()
            }
        }

}

// convert to integer pennies
// then round up 1,2,3,4,5 to 5 to 5, 6,7,8,9,0 to 0 (+ in prev column)
// TODO: Refactor this with confidence becauase of unit test.
fun Double.roundUpNickel() : Double {
    val pennies = this.toPennies()
    val nickels = ceil(pennies / 5.0).toBigDecimal()
    val roundedCents = nickels * BigDecimal("5")
    return roundedCents.toDouble() / 100.0

}

fun Double.toPennies() : Int = ceil(this*100).toInt()

// ***** Dummy Values
// ** Important note: Quantity is ignored for time's sake, everything considered qty: 1
val skittles = CartItem("1 16lb bag of Skittles: 16.00", 16.00, isExempt = true)
val walkman = CartItem("1 Walkman", 99.99) // tax $10
val popcorn = CartItem("1 bag of microwave popcorn", 0.99)

val basket1 = Cart(listOf(skittles, walkman, popcorn))

val coffee = CartItem("1 imported bag of Vanilla-Hazelnut Coffee", 11.00, isImported = true)
val vespa = CartItem("1 Imported Vespa", 15001.25, isImported = true)

val basket2 = Cart(listOf(coffee, vespa))

/*
Shopping Basket 3:
1 imported crate of Almond Snickers at 75.99 1 Discman at 55.00
1 Imported Bottle of Wine at 10.00
1 300# bag of Fair-Trade Coffee at 997.99

 */
val importedSnickers = CartItem("1 imported crate of Almond Snickers", 75.99, isImported = true)
val importedWine = CartItem("1 Imported Bottle of Wine", 10.00, isImported = true)
val fairTradeCoffee = CartItem("1 300# bag of Fair-Trade Coffee", 997.99, isExempt = true)

val basket3 = Cart(listOf(importedSnickers, importedWine, fairTradeCoffee))

