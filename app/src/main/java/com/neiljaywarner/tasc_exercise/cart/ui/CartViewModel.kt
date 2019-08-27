package com.neiljaywarner.tasc_exercise.cart.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neiljaywarner.tasc_exercise.cart.model.Cart
import com.neiljaywarner.tasc_exercise.cart.model.getCart

class CartViewModel : ViewModel() {
    fun loadBasketNumber(basketNumber: Int) {
        _cart.value = getCart(basketNumber)
    }

    private val _cart = MutableLiveData<Cart>()
    val cart: LiveData<Cart> = _cart
}