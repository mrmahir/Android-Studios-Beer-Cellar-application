package com.example.mobiledevelopment_aflevering_mahir

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobiledevelopment_aflevering_mahir.databinding.ActivityBeerListBinding

class BeerListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeerListBinding
    private lateinit var viewModel: BeerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val beerApiService = RetrofitInstance.api // Obtain the API service from Retrofit instance
        val repository = BeerRepository(beerApiService) // Initialize repository with the API service
        val viewModelFactory = BeerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(BeerViewModel::class.java)

        val beerAdapter = BeerAdapter(emptyList())
        binding.beerRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.beerRecyclerView.adapter = beerAdapter

        viewModel.beers.observe(this, Observer { beers ->
            beerAdapter.updateBeers(beers)
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            errorMessage?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        })

        viewModel.getAllBeers()
    }
}