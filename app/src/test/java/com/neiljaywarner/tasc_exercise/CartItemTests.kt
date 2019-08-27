package com.neiljaywarner.tasc_exercise

import com.neiljaywarner.tasc_exercise.cart.model.*
import org.junit.Test

import org.junit.Assert.*
import java.math.BigDecimal

class CartItemTests {

    // Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, ***with no exemptions****
    // Sales tax is rounded up to the *nearest* multiple of $0.05. This rounding is done by item, by type of tax (basic sales and import duty)

    @Test
    fun importTax_is_correct_roundup_1() {
        val cartItem = CartItem("Misc Imported item with easy calc", BigDecimal("0.95"), isImported = true)
        assertEquals(BigDecimal("0.05"), cartItem.importTax)
    }

    @Test
    fun importTax_is_correct_roundup_dime() {
        val cartItem = CartItem("Misc Imported item with easy calc", BigDecimal("1.98"), isImported = true)
        assertEquals(BigDecimal("0.10"), cartItem.importTax)
    }

    // TODO: Test this part with cart tests. - roundup is an excel function and common sense says that round up means $0.01 = $0.05
    @Test
    fun importTax_is_correct_roundup_to_5cents_not_zerocents() {
        val cartItem = CartItem("Misc Imported item with easy calc", BigDecimal("0.01"), isImported = true)
        assertEquals(BigDecimal("0.05"), cartItem.importTax)
    }

    @Test
    fun imported_candy_is_correct() {
        val importedCandy = CartItem("Imported dummyvalue", BigDecimal("1.00"), isExempt = true, isImported = true)
        assertEquals(BigDecimal("0.05"), importedCandy.importTax)
    }

    @Test
    fun domestic_candy_is_correct() {
        assertEquals(BigDecimal.ZERO, skittles.importTax)
        assertEquals(BigDecimal.ZERO, skittles.salesTax)
    }

    @Test
    fun walkman_is_correct() {
        val totalPrice = walkman.price + walkman.salesTax + walkman.importTax
        assertEquals(BigDecimal("109.99"), totalPrice)
    }

    @Test
    fun vespa_is_correct() {
        val totalPrice = vespa.price + vespa.salesTax + vespa.importTax
        assertEquals(BigDecimal("17251.5"), totalPrice.stripTrailingZeros())
    }

    @Test
    fun importedCoffee_tax_is_correct() {
        val tax = coffee.importTax + coffee.salesTax
        assertEquals(BigDecimal("0.55"), tax)
    }

    @Test
    fun roundUpNickel() {
        assertEquals(BigDecimal("0.05"), BigDecimal("0.04").roundUpNickel())
        assertEquals(BigDecimal("2.0"), BigDecimal("1.98").roundUpNickel())
        assertEquals(BigDecimal("0.10"), BigDecimal("0.08").roundUpNickel())
    }



    @Test
    fun penniesToNickels() {
        assertEquals(BigDecimal("1"), BigDecimal("5").penniesToNickelsRoundedUp())
    }

    // TODO: Consider revamping all of these to JodaMoney for cleanness

}

