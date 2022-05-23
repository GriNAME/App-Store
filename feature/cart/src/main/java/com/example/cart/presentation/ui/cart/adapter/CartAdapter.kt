package com.example.cart.presentation.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cart.R
import com.example.cart.databinding.RowCartBinding
import com.example.cart.presentation.viewmodel.CartViewModel
import com.example.cart.util.CartDiffUtil
import com.example.details_api.domain.model.CartItem
import com.example.details_api.domain.model.Product
import com.example.navigation.NavigationFlow
import com.example.navigation.ToFlowNavigatable

class CartAdapter(
    private val requireActivity: FragmentActivity,
    private val cartViewModel: CartViewModel
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var productList = emptyList<CartItem>()

    fun setData(newProductList: List<CartItem>) {
        val cartDiffUtil = CartDiffUtil(productList, newProductList)
        val resultDiffUtil = DiffUtil.calculateDiff(cartDiffUtil)
        productList = newProductList
        resultDiffUtil.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: RowCartBinding) : RecyclerView.ViewHolder(binding.root) {

        private var counter: Int = 1

        fun bind(cartItem: CartItem) {
            binding.apply {

                cartItem.product.images.first().let {
                    productImage.load(it)
                }
                productImage.setOnClickListener {
                    (requireActivity as ToFlowNavigatable).navigateToFlow(NavigationFlow.DetailsFlow)
                }

                productTitleText.text = cartItem.product.title
                productTitleText.setOnClickListener {
                    (requireActivity as ToFlowNavigatable).navigateToFlow(NavigationFlow.DetailsFlow)
                }

                productPrice.text = cartItem.product.price.toString()
                productCounterGoodsText.text = cartItem.quantity.toString()

                productPlusButton.setOnClickListener {

                    val count = cartItem.quantity + 1
                    productCounterGoodsText.text = count.toString()

                    val newCartItem = CartItem(
                        cartItem.id,
                        cartItem.product,
                        count
                    )

                    cartViewModel.updateItemToCart(newCartItem)
                    cartViewModel.refreshTotalPrice()

                    if (counter > 0)
                        productMinusButton.setImageDrawable(
                            AppCompatResources.getDrawable(
                                root.context,
                                R.drawable.ic_minus
                            )
                        )
                }

                productMinusButton.setOnClickListener {

                    if (cartItem.quantity > 0) {
                        val count = cartItem.quantity - 1
                        productCounterGoodsText.text = count.toString()

                        val newCartItem = CartItem(
                            cartItem.id,
                            cartItem.product,
                            count
                        )
                        cartViewModel.updateItemToCart(newCartItem)
                        cartViewModel.refreshTotalPrice()
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
                    cartViewModel.deleteItemFromCart(cartItem)
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
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}