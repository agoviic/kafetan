package com.example.calsdown_projekatzakol.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmappclass.databinding.ItemViewPagerBinding


class ViewPagerAdapter(
    private val content: List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(content[position])
    }

    override fun getItemCount(): Int = content.size

    inner class ViewHolder(private val binding: ItemViewPagerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(imageId: Int){
            binding.ivImageId.setImageResource(imageId)
        }
    }
}