package com.neiljaywarner.tasc_exercise.cart.model

data class Cart(val cartItems: List<CartItem>) {
    fun calculateSalesTax() : Double {
        //TODO: Calculate sales tax
        return 22.22
    }
}

fun getBasket(basketNum: Int) : Cart = when (basketNum) {
    1 -> Cart(listOf(CartItem("item1")))
    2 -> Cart(listOf(CartItem("item2")))
    else -> Cart(listOf(CartItem("item3.1"), CartItem("item3.2")))
}

data class CartItem(val itemName: String, val isImported: Boolean = false, val isExempt: Boolean = false) {
    val importTax : Double
        get() {
            return if (isImported) {
                33.33
            } else {
                0.0
            }
        }
    val salesTax : Double
        get() {
            return if (isExempt) {
                0.0
            } else {
                77.77
            }
        }

}