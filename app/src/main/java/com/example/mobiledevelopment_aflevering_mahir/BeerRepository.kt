package com.example.mobiledevelopment_aflevering_mahir

import android.os.Build
import androidx.annotation.RequiresApi

class BeerRepository(private val beerApiService: BeerApiService) {

    suspend fun getAllBeers(): List<Beer> {
        return beerApiService.getAllBeers()
    }

    suspend fun getUserBeers(user: String, orderBy: String): List<Beer> {
        return beerApiService.getUserBeers(user, orderBy)
    }

    suspend fun addBeer(beer: Beer): Beer {
        return beerApiService.addBeer(beer)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun sortBeers(beers: List<Beer>, sortBy: String, isAscending: Boolean = true): List<Beer> {
        return beers.sortedWith(compareBy<Beer> {
            when (sortBy) {
                "brewery" -> it.brewery
                "name" -> it.name
                "abv" -> it.abv.toString()
                else -> it.id.toString()
            }
        }.let { if (!isAscending) it.reversed() else it })
    }

    fun filterBeers(beers: List<Beer>, filterBy: Map<String, String>): List<Beer> {
        return beers.filter { beer ->
            filterBy.entries.all { (key, value) ->
                when (key) {
                    "brewery" -> beer.brewery.contains(value, ignoreCase = true)
                    "name" -> beer.name.contains(value, ignoreCase = true)
                    "abv" -> beer.abv.toString() == value
                    else -> false
                }
            }
        }
    }


    // Implement PUT and DELETE methods here when you have them in your API.
}