package com.example.mobiledevelopment_aflevering_mahir

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.mobiledevelopment_aflevering_mahir.Beer

class BeerAddActivity : AppCompatActivity() {

    private lateinit var breweryEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var styleEditText: EditText
    private lateinit var abvEditText: EditText
    private lateinit var ibuEditText: EditText
    private lateinit var pictureUrlEditText: EditText
    private lateinit var howManyEditText: EditText
    // Initialize other EditTexts if needed

    private lateinit var addButton: Button
    private lateinit var beerRepository: BeerRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beer_add_activity)

        breweryEditText = findViewById(R.id.editTextBrewery)
        nameEditText = findViewById(R.id.editTextName)
        styleEditText = findViewById(R.id.editTextStyle)
        abvEditText = findViewById(R.id.editTextAbv)
        ibuEditText = findViewById(R.id.editTextIbu)
        pictureUrlEditText = findViewById(R.id.editTextPictureUrl)
        howManyEditText = findViewById(R.id.editTextHowMany)
        // Initialize other EditTexts

        // Initialize BeerRepository with the BeerApiService instance
        beerRepository = BeerRepository(RetrofitInstance.api)

        addButton = findViewById(R.id.buttonAddBeer)
        addButton.setOnClickListener {
            addBeer()
        }
    }

    private fun addBeer() {
        val id = breweryEditText.id
        val user = "user_placeholder" // Replace with actual user identification
        val brewery = breweryEditText.text.toString()
        val name = nameEditText.text.toString()
        val style = styleEditText.text.toString()
        val abv = abvEditText.text.toString().toDoubleOrNull()
        val volume = ibuEditText.text.toString().toDoubleOrNull()
        val pictureUrl = pictureUrlEditText.text.toString().ifEmpty { null }
        val howMany = howManyEditText.text.toString().toIntOrNull()

        if (abv == null || howMany == null) {
            Toast.makeText(this, "Please enter valid numbers for ABV and quantity", Toast.LENGTH_SHORT).show()
            return
        }

        // Construct the Beer object
        val beer = Beer(
            id = id,
            user = user,
            brewery = brewery,
            name = name,
            style = style,
            abv = abv,
            volume = volume,
            pictureUrl = pictureUrl,
            howMany = howMany)

        // Call repository to add beer within coroutine scope
        lifecycleScope.launch {
            try {
                val result = beerRepository.addBeer(beer)
                // Post success to UI thread
                Toast.makeText(this@BeerAddActivity, "Beer added successfully! ID: ${result.id}", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                // Post error to UI thread
                Toast.makeText(this@BeerAddActivity, "Error adding beer: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
