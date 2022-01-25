package com.example.ecommerce.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ecommerce.R
import com.example.ecommerce.databinding.RowBestSellerBinding
import com.example.ecommerce.domain.model.BestSeller
import com.example.ecommerce.presentation.ui.util.ListDiffUtil

class BestSellerAdapter : RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {

    private var bestSellers = emptyList<BestSeller>()

    class ViewHolder(private val binding: RowBestSellerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bestSeller: BestSeller) {
            binding.apply {

                val context = binding.root.context

                root.setOnClickListener {
                    Toast.makeText(context, "Navigate to ${bestSeller.title}", Toast.LENGTH_SHORT).show()
                }

                bestSellerName.text = bestSeller.title

                val price = "$${bestSeller.discountPrice}"
                bestSellerPrice.text = price

                val text = "<strike><font color=\'#757575\'>$${bestSeller.priceWithoutDiscount}</font></strike>"
                bestSellerOldPrice.text = HtmlCompat.fromHtml(text, FROM_HTML_MODE_COMPACT)

                bestSeller.isFavorites.let {
                    if (it)
                        bestSellerFavoriteButton.load(R.drawable.ic_heart)
                    else
                        bestSellerFavoriteButton.load(R.drawable.ic_heart_outline)
                }

                bestSeller.picture.let { pictureUrl ->
                    bestSellerImage.load(pictureUrl)
                }
            }
        }
    }

    fun setData(bestSellers: List<BestSeller>) {
        val listDiffUtil = ListDiffUtil(bestSellers, this.bestSellers)
        val resultDiffUtil = DiffUtil.calculateDiff(listDiffUtil)
        this.bestSellers = bestSellers
        resultDiffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowBestSellerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(bestSellers[position])

    override fun getItemCount(): Int = bestSellers.size
}