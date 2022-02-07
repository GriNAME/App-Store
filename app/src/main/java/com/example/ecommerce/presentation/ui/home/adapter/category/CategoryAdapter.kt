package com.example.ecommerce.presentation.ui.home.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ecommerce.R
import com.example.ecommerce.databinding.RowCategoryBinding


class CategoryAdapter(private val categories: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var selectedItemPos = 0
    var lastItemSelectedPos = -1
    var initPosition = true

    inner class ViewHolder(private val binding: RowCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context
        private lateinit var category: Category

        init {
            binding.categoryButton.setOnClickListener {
                selectedItemPos = adapterPosition

                lastItemSelectedPos = if (lastItemSelectedPos == -1)
                    selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    selectedItemPos
                }
                notifyItemChanged(selectedItemPos)

                if (initPosition) {
                    notifyItemRangeChanged(0, 1)
                    initPosition = false
                }

                Toast.makeText(context, "Navigate to ${category.name} category", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(category: Category) {
            this.category = category

            binding.apply {
                categoryButton.load(category.icon)
                categoryText.text = category.name
            }

        }

        fun selectedItem() {
            binding.apply {
                categoryButton.background = AppCompatResources.getDrawable(context, R.drawable.circle_background_accent)
                categoryButton.setColorFilter(context.getColor(R.color.white))
                categoryText.setTextColor(context.getColor(R.color.accent))
            }
        }

        fun unselectedItem() {
            binding.apply {
                categoryButton.background = AppCompatResources.getDrawable(context, R.drawable.circle_background_white)
                categoryButton.setColorFilter(context.getColor(R.color.silver))
                categoryText.setTextColor(context.getColor(R.color.silver))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position == 0)
            holder.selectedItem()

        if (position == selectedItemPos)
            holder.selectedItem()
        else
            holder.unselectedItem()

        holder.bind(categories[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size
}