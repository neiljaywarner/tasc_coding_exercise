package com.neiljaywarner.tasc_exercise

import com.neiljaywarner.tasc_exercise.cart.model.basket1
import com.neiljaywarner.tasc_exercise.cart.model.basket2
import com.neiljaywarner.tasc_exercise.cart.model.basket3

import org.junit.Test

import org.junit.Assert.*

class CartTests {

    // Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, ***with no exemptions****
    // Sales tax is rounded up to the *nearest* multiple of $0.05. This rounding is done by item, by type of tax (basic sales and import duty)
    @Test
    fun basket1SalesTax() {
        assertEquals(10.00, basket1.calculateTotalSalesTax(), 0.001)
    }

    @Test
    fun basket1Total() {
        assertEquals(126.98, basket1.calculateTotal(), 0.001)
    }

    @Test
    fun basket2SalesTax() {
        assertEquals(2250.8, basket2.calculateTotalSalesTax(), 0.001)
    }

    @Test
    fun basket2Total() {
        assertEquals(17263.05, basket2.calculateTotal(), 0.001)
    }

    @Test
    fun basket3SalesTax() {
        assertEquals(10.8, basket3.calculateTotalSalesTax(), 0.001)
    }

    @Test
    fun basket3Total() {
        assertEquals(1149.7, basket3.calculateTotal(), 0.001)
    }

}

