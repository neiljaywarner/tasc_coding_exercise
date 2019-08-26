package com.neiljaywarner.tasc_exercise

import com.neiljaywarner.tasc_exercise.cart.model.CartItem
import org.junit.Test

import org.junit.Assert.*

class CartItemTests {

    // Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, ***with no exemptions****
    // Sales tax is rounded up to the *nearest* multiple of $0.05. This rounding is done by item, by type of tax (basic sales and import duty)
    @Test
    fun importTax_is_correct_rounding_not_needed() {
        val cartItem = CartItem("Misc Imported item with easy calc", 100.00, isImported = true)
        assertEquals(5, cartItem.importTax)
    }

    @Test
    fun importTax_is_correct_roundup_1() {
        val cartItem = CartItem("Misc Imported item with easy calc", 0.95, isImported = true)
        assertEquals(0.05, cartItem.importTax, 0.0001)
    }

    @Test
    fun importTax_is_correct_roundup_dime() {
        val cartItem = CartItem("Misc Imported item with easy calc", 1.98, isImported = true)
        assertEquals(0.10, cartItem.importTax, 0.0001)
    }

    // TODO: Test this part with cart tests. - roundup is an excel function and common sense says that round up means $0.01 = $0.05
    @Test
    fun importTax_is_correct_roundup_to_5cents_not_zerocents() {
        val cartItem = CartItem("Misc Imported item with easy calc", 0.01, isImported = true)
        assertEquals(0.05, cartItem.importTax, 0.0001)
    }

    @Test
    fun imported_candy_is_correct() {
        val candy = CartItem("Imported snickers", 1.00, isExempt = true)
        assertEquals(0.05, candy.importTax, 0.001)
    }

    @Test
    fun domestic_candy_is_correct() {
        val candy = CartItem("non-imported skittles", 1.00, isExempt = true)
        assertEquals(0.05, candy.importTax, 0.001)
        assertEquals(0.33, candy.salesTax, 0.001)
    }

   /*
   1 16lb bag of Skittles: 16.00
1 Walkman: 109.99
1 bag of microwave Popcorn: 0.99 Sales Taxes: 10.00
Total: 126.98

    */



}

