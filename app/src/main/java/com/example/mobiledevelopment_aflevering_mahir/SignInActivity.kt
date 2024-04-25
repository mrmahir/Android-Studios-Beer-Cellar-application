package com.example.mobiledevelopment_aflevering_mahir

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mobiledevelopment_aflevering_mahir.ui.theme.MobileDevelopmentAfleveringMahirTheme
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the user is already logged in
        if (FirebaseAuth.getInstance().currentUser != null) {
            navigateToMain()  // User is already logged in, navigate to the main activity
            return  // Stop further execution of this function
        }

        setContent {
            SignInScreen()
        }
    }

    @Composable
    fun SignInScreen() {
        val auth = FirebaseAuth.getInstance()
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }  // MutableState for message

        MobileDevelopmentAfleveringMahirTheme {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { loginUser(email, password, auth) { message = it } }) {
                        Text("Login")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { registerUser(email, password, auth) { message = it } }) {
                        Text("Register")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = message)
                }
            }
        }
    }

    private fun loginUser(email: String, password: String, auth: FirebaseAuth, updateMessage: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                updateMessage("Login successful")
                navigateToMain()
            } else {
                updateMessage("Login failed: ${task.exception?.message}")
            }
        }
    }

    private fun registerUser(email: String, password: String, auth: FirebaseAuth, updateMessage: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                updateMessage("Registration successful")
                navigateToMain()
            } else {
                updateMessage("Registration failed: ${task.exception?.message}")
            }
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
