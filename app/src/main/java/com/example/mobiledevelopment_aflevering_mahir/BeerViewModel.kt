package com.example.mobiledevelopment_aflevering_mahir

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BeerViewModel(private val repository: BeerRepository) : ViewModel() {
    val beers = MutableLiveData<List<Beer>>()
    val error = MutableLiveData<String>()

    fun getAllBeers() {
        viewModelScope.launch {
            try {
                beers.value = repository.getAllBeers()
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    fun getUserBeers(userId: String, orderBy: String) {
        viewModelScope.launch {
            try {
                beers.value = repository.getUserBeers(userId, orderBy)
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    fun addBeer(beer: Beer) {
        viewModelScope.launch {
            try {
                val newBeer = repository.addBeer(beer)
                // Assuming you want to update the live data with the new list including the added beer
                beers.value = beers.value?.plus(newBeer)
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    // Define methods for PUT and DELETE operations here when you have them in your API.
}