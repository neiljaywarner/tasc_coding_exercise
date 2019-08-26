package com.neiljaywarner.tasc_exercise.cart.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.neiljaywarner.tasc_exercise.R

const val INVALID_BASKET_NUMBER = Integer.MIN_VALUE
class CartFragment : Fragment() {

    private lateinit var cartViewModel: CartViewModel

    private val basketNumber by lazy { arguments?.let { CartFragmentArgs.fromBundle(it).basketNumber } ?: INVALID_BASKET_NUMBER }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartViewModel =
            ViewModelProviders.of(this).get(CartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        cartViewModel.text.observe(this, Observer {
            textView.text = it
        })

       // val detailArgs = CartFragmentArgs.fromBundle(arguments)
        //val detailArgs =
        //val itemId = detailArgs.itemId
        cartViewModel.loadBasketNumber(basketNumber)
        return root
    }

}