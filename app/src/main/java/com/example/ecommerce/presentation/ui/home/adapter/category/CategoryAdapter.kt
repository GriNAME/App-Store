package com.example.ecommerce.presentation.ui.home.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ecommerce.R
import com.example.ecommerce.databinding.RowCategoryBinding


class CategoryAdapter(private val categories: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var selectedItemPos = 0
    var lastItemSelectedPos = 0

    inner class ViewHolder(private val binding: RowCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(category: Category) {

            binding.apply {
                categoryButton.load(category.icon)
                categoryText.text = category.name

                categoryButton.setOnClickListener {
                    if (!category.isChecked) {
                        selectedItem()
                        category.isChecked = true
                    } else {
                        unselectedItem()
                        category.isChecked = false
                    }
                }
            }
        }

        init {
            itemView.setOnClickListener {
                selectedItemPos = adapterPosition

                if (lastItemSelectedPos == 0)
                    lastItemSelectedPos = selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    lastItemSelectedPos = selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
            }
        }

        fun selectedItem() {
            binding.apply {
                categoryButton.background = context.getDrawable(R.drawable.circle_background_accent)
                categoryButton.setColorFilter(context.getColor(R.color.white))
                categoryText.setTextColor(context.getColor(R.color.accent))
            }
        }

        fun unselectedItem() {
            binding.apply {
                categoryButton.background = context.getDrawable(R.drawable.circle_background_white)
                categoryButton.setColorFilter(context.getColor(R.color.silver))
                categoryText.setTextColor(context.getColor(R.color.silver))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == selectedItemPos)
            holder.selectedItem()
        else
            holder.unselectedItem()

        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}