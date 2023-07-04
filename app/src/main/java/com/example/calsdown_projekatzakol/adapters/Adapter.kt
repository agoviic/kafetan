package com.example.calsdown_projekatzakol.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.calsdown_projekatzakol.model.Drink
import com.example.mvvmappclass.databinding.ItemBinding

class Adapter: RecyclerView.Adapter<Adapter.DrinkViewHolder>() {
    inner class DrinkViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Drink>() {
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        return DrinkViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = differ.currentList[position]
        holder.binding.apply {
            Glide.with(ivSlika.context).load(drink.strDrinkThumb).override(300,300).fitCenter().into(ivSlika)
            tvDrinkId.text = drink.idDrink
            tvInstruction.text = drink.strInstructions
            holder.itemView.setOnClickListener{
                onItemClickListener?.let { drinkItem->
                    drinkItem(drink)
                }
            }
        }
    }

    private var onItemClickListener: ((Drink) -> Unit)? = null

    fun setOnItemClickListener(listener: (Drink) -> Unit) {
        onItemClickListener = listener
    }
}


