package com.neiljaywarner.tasc_exercise.cart.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neiljaywarner.tasc_exercise.R
import com.neiljaywarner.tasc_exercise.cart.model.Cart
import com.neiljaywarner.tasc_exercise.cart.model.CartItem
import com.neiljaywarner.tasc_exercise.extensions.inflate
import kotlinx.android.synthetic.main.row_cart_item.view.*
import java.text.NumberFormat

class CartRecyclerAdapter(private val cart: Cart) : RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.row_cart_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(cart.cartItems[position])

    override fun getItemCount() = cart.cartItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CartItem) = with(itemView) {
            item.run {
                text_description.text = displayDescription
                text_price.text = NumberFormat.getCurrencyInstance().format(price.toDouble())
            }
        }
    }
}