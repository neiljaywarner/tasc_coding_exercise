package com.neiljaywarner.tasc_exercise

import com.neiljaywarner.tasc_exercise.cart.model.CartItem
import com.neiljaywarner.tasc_exercise.cart.model.roundUpNickel
import org.junit.Test

import org.junit.Assert.*

const val DELTA = 0.00001
class CartItemTests {

    // Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, ***with no exemptions****
    // Sales tax is rounded up to the *nearest* multiple of $0.05. This rounding is done by item, by type of tax (basic sales and import duty)
    @Test
    fun importTax_is_correct_rounding_not_needed() {
        val cartItem = CartItem("Misc Imported item with easy calc", 100.00, isImported = true)
        assertEquals(5.0, cartItem.importTax, DELTA)
    }

    @Test
    fun importTax_is_correct_roundup_1() {
        val cartItem = CartItem("Misc Imported item with easy calc", 0.95, isImported = true)
        assertEquals(0.05, cartItem.importTax, DELTA)
    }

    @Test
    fun importTax_is_correct_roundup_dime() {
        val cartItem = CartItem("Misc Imported item with easy calc", 1.98, isImported = true)
        assertEquals(0.10, cartItem.importTax, DELTA)
    }

    // TODO: Test this part with cart tests. - roundup is an excel function and common sense says that round up means $0.01 = $0.05
    @Test
    fun importTax_is_correct_roundup_to_5cents_not_zerocents() {
        val cartItem = CartItem("Misc Imported item with easy calc", 0.01, isImported = true)
        assertEquals(0.05, cartItem.importTax, DELTA)
    }

    @Test
    fun imported_candy_is_correct() {
        val candy = CartItem("Imported snickers", 1.00, isExempt = true, isImported = true)
        assertEquals(0.05, candy.importTax, DELTA)
    }

    @Test
    fun domestic_candy_is_correct() {
        val candy = CartItem("non-imported skittles", 1.00, isExempt = true)
        assertEquals(0.00, candy.importTax, DELTA)
        assertEquals(0.00, candy.salesTax, DELTA)
    }

    @Test
    fun roundUpNickel() {
        assertEquals(0.05, 0.04.roundUpNickel(), DELTA)
        assertEquals(2.00, 1.98.roundUpNickel(), DELTA)
        assertEquals(0.10, 0.08.roundUpNickel(), DELTA)
    }

    // TODO: Consider revamping all of these to BigDecimal or JodaMoney

}

