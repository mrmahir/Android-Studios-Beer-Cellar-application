package com.example.mobiledevelopment_aflevering_mahir

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobiledevelopment_aflevering_mahir.databinding.ActivityMainPageBinding
import com.google.firebase.auth.FirebaseAuth

class MainPage : AppCompatActivity() {
    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if user is logged in
        if (FirebaseAuth.getInstance().currentUser == null) {
            redirectToSignIn()
            return // Prevent further execution
        }

        // Button to view beers
        binding.viewBeersButton.setOnClickListener {
            startActivity(Intent(this, BeerListActivity::class.java))
        }

        binding.addBeerButton.setOnClickListener {
            startActivity(Intent(this, BeerAddActivity::class.java))
        }

        binding.logoutButton.setOnClickListener{
            startActivity(Intent(this, BeerAddActivity::class.java))
        }

        binding.viewUserBeersButton.setOnClickListener{
            startActivity(Intent(this, SearchBeersActivity::class.java))
        }

        // Set up the logout button

    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut() // Sign out from Firebase
        redirectToSignIn()
    }

    private fun redirectToSignIn() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
}
