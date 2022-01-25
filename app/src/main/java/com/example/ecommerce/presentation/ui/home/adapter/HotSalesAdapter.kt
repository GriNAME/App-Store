package com.example.ecommerce.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ecommerce.databinding.RowHotSaleBinding
import com.example.ecommerce.domain.model.HomeStore
import com.example.ecommerce.presentation.ui.util.ListDiffUtil

class HotSalesAdapter : RecyclerView.Adapter<HotSalesAdapter.ViewHolder>() {

    private var hotSales = emptyList<HomeStore>()

    class ViewHolder(private val binding: RowHotSaleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hotSale: HomeStore) {

            binding.apply {

                val context = binding.root.context

                hotSaleTitle.text = hotSale.title
                hotSaleSubtitle.text = hotSale.subtitle

                hotSaleButton.setOnClickListener {
                    Toast.makeText(context, "Start buying ${hotSale.title}", Toast.LENGTH_SHORT).show()
                }

                root.setOnClickListener {
                    Toast.makeText(context, "Navigate to ${hotSale.title}", Toast.LENGTH_SHORT).show()
                }

                hotSale.isNew.let {
                    hotSaleNewMark.visibility = if (it)
                        View.VISIBLE
                    else
                        View.INVISIBLE
                }

                hotSale.picture.let {
                    hotSaleImage.load(it)
                }
            }
        }
    }

    fun setData(hotSales: List<HomeStore>) {
        val listDiffUtil = ListDiffUtil(hotSales, this.hotSales)
        val resultDiffUtil = DiffUtil.calculateDiff(listDiffUtil)
        this.hotSales = hotSales
        resultDiffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowHotSaleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(hotSales[position])

    override fun getItemCount(): Int = hotSales.size
}