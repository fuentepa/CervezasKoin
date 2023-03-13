package com.paf.cervezaskoin.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paf.cervezaskoin.CervezasKoinApplication
import com.paf.cervezaskoin.data.entities.Beer
import com.paf.cervezaskoin.databinding.ItemBeerBinding
import com.paf.cervezaskoin.ui.common.basicDiffUtil
import com.paf.cervezaskoin.ui.common.loadUrl

class BeersAdapter(private val listener: (Int) -> Unit) : RecyclerView.Adapter<BeersAdapter.ViewHolder>() {

    var beers: List<Beer> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = beers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beers[position]
        holder.bind(beer)
        holder.itemView.setOnClickListener { listener(beer.id) }
    }

    class ViewHolder(var binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer) {
            binding.beerName.text = beer.name
            binding.imageView.loadUrl(beer.imageUrl)
            binding.beerTagline.text = beer.tagline
            binding.card.setCardBackgroundColor(
                CervezasKoinApplication.appContext.getColor(
                    if (beer.available) android.R.color.white
                    else android.R.color.darker_gray
                )
            )
        }
    }
}