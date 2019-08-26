package com.neiljaywarner.tasc_exercise.cart.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    fun loadBasketNumber(basketNumber: Int) {
        _text.value = "Load basket number $basketNumber"
    }

    private val _text = MutableLiveData<String>().apply {
        value = "Empty Cart"
    }
    val text: LiveData<String> = _text
}