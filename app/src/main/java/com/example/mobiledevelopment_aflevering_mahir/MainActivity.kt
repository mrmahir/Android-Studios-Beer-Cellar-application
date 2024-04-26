package com.example.mobiledevelopment_aflevering_mahir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobiledevelopment_aflevering_mahir.ui.theme.MobileDevelopmentAfleveringMahirTheme
import android.content.Intent
import android.widget.Button
import com.example.mobiledevelopment_aflevering_mahir.databinding.ActivityMainPageBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Check if the user is logged in
        if (FirebaseAuth.getInstance().currentUser != null) {
            // User is logged in, navigate to MainPage
            startActivity(Intent(this, MainPage::class.java))
        } else {
            // User is not logged in, navigate to SignInActivity
            startActivity(Intent(this, SignInActivity::class.java))
        }

        finish() // Finish MainActivity so it's not in the back stack


    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileDevelopmentAfleveringMahirTheme {
        Greeting("Android")
    }
}

