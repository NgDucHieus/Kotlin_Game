package com.example.eco

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginUIScreen(navController: NavHostController) {
    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() }) // Dismiss keyboard on tap
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Continue with Facebook Button
        Button(
            onClick = { /* Handle Facebook Login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF386FFF)), // Facebook Blue
            shape = RoundedCornerShape(40.dp)
        ) {
            Text("Continue with Facebook", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Continue as a specific user (outlined)
        OutlinedButton(
            onClick = { /* Handle Login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(40.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Continue as Hiếu", fontSize = 14.sp,color = Color.Gray)
                Text("hieu57075@gmail.com", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Divider with "OR"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
            Text("OR", modifier = Modifier.padding(horizontal = 8.dp), fontSize = 14.sp)
            Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Email TextField
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(50)) // White background with rounded corners
                .border(1.dp, Color.Black, shape = RoundedCornerShape(50)) // Black border
                .padding(horizontal = 16.dp) // Inner padding for the text field
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", color = Color.Gray) },
                modifier = Modifier.fillMaxSize(),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, // Remove underline when focused
                    unfocusedIndicatorColor = Color.Transparent, // Remove underline when unfocused
                    placeholderColor = Color.Gray,
                    backgroundColor = Color.Transparent // Transparent container
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Password TextField
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(50)) // White background with rounded corners
                .border(1.dp, Color.Black, shape = RoundedCornerShape(50)) // Black border
                .padding(horizontal = 16.dp) // Inner padding for the text field
        ) {
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", color = Color.Gray) },
                modifier = Modifier.fillMaxSize(),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, // Remove underline when focused
                    unfocusedIndicatorColor = Color.Transparent, // Remove underline when unfocused
                    placeholderColor = Color.Gray,
                    backgroundColor = Color.Transparent // Transparent container
                ),
                visualTransformation = PasswordVisualTransformation(), // Hide input
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() } // Dismiss keyboard on Done action
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password Text
        Text(
            text = "Forgot your password?",
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button (Initially Disabled)
        Button(
            onClick = {
                    if (password == "1234" && email == "hieu57075@gmail.com") {
                        navController.navigate("main")
                    }
                      },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = email.isNotEmpty() && password.isNotEmpty(), // Enable when inputs are filled
            colors = ButtonDefaults.buttonColors(backgroundColor = if (email.isNotEmpty() && password.isNotEmpty()) Color(0xFF386FFF) else Color.LightGray),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Log In", color = if (email.isNotEmpty() && password.isNotEmpty()) Color.White else Color.Gray, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Option
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "No Account?", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sign up",
                fontSize = 14.sp,
                color = Color.Blue,
                modifier = Modifier.clickable {  navController.navigate("signup") },
                textAlign = TextAlign.Center
            )
        }
    }
}
