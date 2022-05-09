package com.example.cart.presentation.ui.cart.adapter

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cart.R
import com.example.cart.databinding.RowCartBinding
import com.example.cart.domain.model.Basket
import com.example.navigation.NavigationFlow
import com.example.navigation.ToFlowNavigatable

class CartAdapter(
    private val requireActivity: FragmentActivity,
    private val basket: List<Basket>
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RowCartBinding) : RecyclerView.ViewHolder(binding.root) {

        private var counter: Int = 1

        fun bind(basket: Basket) {
            binding.apply {
                basket.images.let {
                    productImage.load(it)
                }
                productTitleText.text = basket.title
                productTitleText.setOnClickListener {
                    (requireActivity as ToFlowNavigatable).navigateToFlow(NavigationFlow.DetailsFlow)
                }

                productPrice.text = basket.price.toString()

                productPlusButton.setOnClickListener {
                    counter = ++counter
                    productCounterGoodsText.text = counter.toString()
                    if (counter > 0)
                        productMinusButton.setImageDrawable(
                            AppCompatResources.getDrawable(
                                root.context,
                                R.drawable.ic_minus
                            )
                        )
                }

                productMinusButton.setOnClickListener {
                    if (counter > 0) {
                        counter = --counter
                        productCounterGoodsText.text = counter.toString()
                    }
                    if (counter == 0)
                        productMinusButton.setImageDrawable(
                            AppCompatResources.getDrawable(
                                root.context,
                                R.drawable.ic_minus_disabled
                            )
                        )
                }

                deleteButton.setOnClickListener {
                    Toast.makeText(root.context, "${productTitleText.text} deleted", Toast.LENGTH_SHORT).show()
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