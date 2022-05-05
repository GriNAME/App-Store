package com.example.details.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.details.databinding.PagerPictureBinding
import com.example.details.domain.model.Details

class PicturesPagerAdapter(private val urls: List<String>) : RecyclerView.Adapter<PicturesPagerAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: PagerPictureBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(url: String) {
            binding.imageView.load(url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = PagerPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(urls[position])
    }

    override fun getItemCount() = urls.size
}