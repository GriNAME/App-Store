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
import com.example.details_api.domain.model.Product
import com.example.navigation.NavigationFlow
import com.example.navigation.ToFlowNavigatable

class CartAdapter(
    private val requireActivity: FragmentActivity,
    private val cartViewModel: CartViewModel
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var productList = emptyList<Product>()

    fun setData(newProductList: List<Product>) {
        val cartDiffUtil = CartDiffUtil(productList, newProductList)
        val resultDiffUtil = DiffUtil.calculateDiff(cartDiffUtil)
        productList = newProductList
        resultDiffUtil.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: RowCartBinding) : RecyclerView.ViewHolder(binding.root) {

        private var counter: Int = 1

        fun bind(product: Product) {
            binding.apply {
                product.images.first().let {
                    productImage.load(it)
                }
                productTitleText.text = product.title
                productTitleText.setOnClickListener {
                    (requireActivity as ToFlowNavigatable).navigateToFlow(NavigationFlow.DetailsFlow)
                }

                productPrice.text = product.price.toString()

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
                    cartViewModel.deleteItemFromCart(product)
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