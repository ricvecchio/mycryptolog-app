package com.blimas.mycryptolog.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blimas.mycryptolog.ui.screens.PasswordResetDialog
import com.blimas.mycryptolog.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showPasswordResetDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LoginContent(
        email = email,
        onEmailChange = { email = it },
        password = password,
        onPasswordChange = { password = it },
        isLoading = isLoading,
        onLoginClick = {
            val cleanEmail = email.trim()
            val cleanPassword = password.trim()
            if (cleanEmail.isNotBlank() && cleanPassword.isNotBlank()) {
                isLoading = true
                authViewModel.login(cleanEmail, cleanPassword) { success ->
                    isLoading = false
                    if (success) {
                        onLoginSuccess()
                    } else {
                        Toast.makeText(context, "Login failed. Check credentials.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            }
        },
        onSignUpClick = onSignUpClick,
        onForgotPasswordClick = { showPasswordResetDialog = true }
    )

    if (showPasswordResetDialog) {
        PasswordResetDialog(
            onDismiss = { showPasswordResetDialog = false },
            onConfirm = {
                authViewModel.sendPasswordResetEmail(it.trim()) { success ->
                    val message = if (success) "Password reset email sent!" else "Failed to send email."
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
                showPasswordResetDialog = false
            }
        )
    }
}

@Composable
private fun LoginContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isLoading: Boolean,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        TextButton(onClick = onForgotPasswordClick, enabled = !isLoading) {
            Text("Forgot password?")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onLoginClick,
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Login")
            }
        }

        TextButton(onClick = onSignUpClick, enabled = !isLoading) {
            Text("Don't have an account? Sign up")
        }
    }
}
