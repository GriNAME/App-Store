package com.example.ecommerce.presentation.ui.util

import androidx.recyclerview.widget.DiffUtil

class ListDiffUtil<T>(
    private val newData: List<T>,
    private val oldData: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] === newData[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}