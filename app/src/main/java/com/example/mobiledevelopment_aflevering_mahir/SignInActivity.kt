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
        setContent {
            SignInScreen()
        }
    }

    @Composable
    fun SignInScreen() {
        val auth = FirebaseAuth.getInstance()
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }
        var showError by remember { mutableStateOf(false) }  // State to control the visibility of the error message

        MobileDevelopmentAfleveringMahirTheme {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                            showError = false  // Hide error when user starts typing
                        },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = showError  // Show error if showError is true
                    )
                    if (showError && email.isBlank()) {
                        Text("Email cannot be empty", color = MaterialTheme.colorScheme.error)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
                            showError = false  // Hide error when user starts typing
                        },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        isError = showError  // Show error if showError is true
                    )
                    if (showError && password.isBlank()) {
                        Text("Password cannot be empty", color = MaterialTheme.colorScheme.error)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        if (email.isBlank() || password.isBlank()) {
                            showError = true  // Show error if either field is blank
                        } else {
                            loginUser(email, password, auth) { message = it }
                        }
                    }) {
                        Text("Login")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        if (email.isBlank() || password.isBlank()) {
                            showError = true  // Show error if either field is blank
                        } else {
                            registerUser(email, password, auth) { message = it }
                        }
                    }) {
                        Text("Register")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = message)  // Show the login or registration message
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
        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        finish()
    }
}
