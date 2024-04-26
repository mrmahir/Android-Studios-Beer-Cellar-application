package com.example.mobiledevelopment_aflevering_mahir

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopment_aflevering_mahir.R

class BeerAdapter(private var beers: List<Beer>) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById((R.id.idTextView))
        val userTextView: TextView = itemView.findViewById((R.id.userTextView))
        val breweryTextView: TextView = itemView.findViewById((R.id.breweryTextView))
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val styleTextView: TextView = itemView.findViewById(R.id.styleTextView)
        val abvTextView: TextView = itemView.findViewById(R.id.abvTextView)
        val volumeTextView: TextView = itemView.findViewById(R.id.volumeTextView)
        val pictureUrlTextView: TextView = itemView.findViewById(R.id.pictureUrlTextView)
        val howManyTextView: TextView = itemView.findViewById(R.id.howManyTextView)
        // Add other properties if needed

        fun bind(beer: Beer) {
            idTextView.text = "ID: ${beer.id}"
            userTextView.text = "User: ${beer.user}"
            breweryTextView.text = beer.brewery
            nameTextView.text = beer.name
            styleTextView.text = beer.style
            abvTextView.text = "ABV: ${beer.abv}"
            volumeTextView.text = "Volume: ${beer.volume}"
            pictureUrlTextView.text = beer.pictureUrl
            howManyTextView.text = "Quantity: ${beer.howMany}"
            // Bind other properties of Beer to views
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_beer, parent, false)
        return BeerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(beers[position])
    }

    override fun getItemCount() = beers.size

    fun updateBeers(newBeers: List<Beer>) {
        this.beers = newBeers.toList()
        notifyDataSetChanged()
    }
}