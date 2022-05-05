package com.example.cart.presentation.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cart.data.api.model.BasketDto
import com.example.cart.databinding.RowCartBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val basket: List<BasketDto> = listOf()

    inner class ViewHolder(private val binding: RowCartBinding) : RecyclerView.ViewHolder(binding.root) {

        private var counter: Int = 0

        fun bind(basket: BasketDto) {
            binding.apply {
                basket.images.let {
                    productImage.load(it)
                }
                productTitleText.text = basket.title
                productPrice.text = basket.price.toString()

                productPlusButton.setOnClickListener {
                    productCounterGoodsText.text = counter++.toString()
                }

                productMinusButton.setOnClickListener {
                    productCounterGoodsText.text = counter--.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(basket[position])
    }

    override fun getItemCount(): Int = basket.size
}