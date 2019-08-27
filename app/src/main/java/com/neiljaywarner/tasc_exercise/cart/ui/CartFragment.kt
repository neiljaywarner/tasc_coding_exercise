package com.neiljaywarner.tasc_exercise.cart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neiljaywarner.tasc_exercise.R
import com.neiljaywarner.tasc_exercise.cart.model.Cart
import kotlinx.android.synthetic.main.fragment_cart.*

const val INVALID_BASKET_NUMBER = Integer.MIN_VALUE
class CartFragment : Fragment() {

    private lateinit var cartViewModel: CartViewModel

    private val basketNumber by lazy { arguments?.let { CartFragmentArgs.fromBundle(it).basketNumber } ?: INVALID_BASKET_NUMBER }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // TODO: Change to Koin
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)

        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cartViewModel.cart.observe(this, Observer(::updateUi))
        recycler_cartitems.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        cartViewModel.loadBasketNumber(basketNumber)
    }

    private fun updateUi(cart: Cart?) {
        cart?.run {
            recycler_cartitems.adapter = CartRecyclerAdapter(cart)
            text_sales_tax.text = salesTaxDisplayString
            text_total.text = totalDisplayString
        }

    }

}