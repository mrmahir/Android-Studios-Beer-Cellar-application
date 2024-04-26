package com.example.mobiledevelopment_aflevering_mahir

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopment_aflevering_mahir.databinding.ActivitySearchBeersBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager


class SearchBeersActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBeersBinding
    private val viewModel: BeerViewModel by viewModels {
        BeerViewModelFactory((application as BeerApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBeersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = BeerAdapter(emptyList())
        binding.beersRecyclerView.adapter = adapter
        binding.beersRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.searchButton.setOnClickListener {
            val user = binding.userEditText.text.toString()
            val orderBy = binding.orderByEditText.text.toString()
            if (user.isNotBlank()) {
                viewModel.getUserBeers(user, orderBy)
            }
        }

        binding.backButton.setOnClickListener {
            finish()  // This will close the current activity and return to the previous Activity in the stack, typically MainPage
        }
        viewModel.beers.observe(this) { beers ->
            if (beers.isNotEmpty()) {
                adapter.updateBeers(beers)
            } else {
                Toast.makeText(this, "No beers found", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}