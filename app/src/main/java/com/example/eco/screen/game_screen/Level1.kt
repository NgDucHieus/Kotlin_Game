import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.eco.R

@Composable
fun SignInScreen() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo), // Replace with your logo drawable
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "Đăng Nhập",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Email TextField
            BasicTextField(
                value = email.value,
                onValueChange = { email.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFFF2F2F2), shape = CircleShape)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                decorationBox = { innerTextField ->
                    if (email.value.isEmpty()) {
                        Text(text = "Email", color = Color.Gray)
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            BasicTextField(
                value = password.value,
                onValueChange = { password.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFFF2F2F2), shape = CircleShape)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(Modifier.weight(1f)) {
                            if (password.value.isEmpty()) {
                                Text(text = "Password", color = Color.Gray)
                            }
                            innerTextField()
                        }
                        IconButton(
                            onClick = { passwordVisible.value = !passwordVisible.value }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.high_volume), // Replace with eye icon drawable
                                contentDescription = if (passwordVisible.value) "Hide Password" else "Show Password"
                            )
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot Password
            Text(
                text = "Forgot Password?",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { /* Handle click */ }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Sign-in Button
            Button(
                onClick = { /* Handle sign-in */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF6A11CB),
                                Color(0xFF2575FC)
                            )
                        ), shape = CircleShape
                    ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent), // Set transparent to use custom gradient
                elevation = ButtonDefaults.elevation(0.dp) // Remove default elevation to match design
            ) {
                Text(text = "Sign In", color = Color.White)
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Sign-up Text
            Row {
                Text(text = "Didn't have an account? ", color = Color.Gray)
                Text(
                    text = "Sign Up",
                    color = Color.Blue,
                    modifier = Modifier.clickable { /* Handle sign-up */ }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Social Media Icons
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { /* Handle Twitter */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.facebook_36), // Replace with Twitter icon drawable
                        contentDescription = "Twitter"
                    )
                }
                IconButton(onClick = { /* Handle Gmail */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.high_volume), // Replace with Gmail icon drawable
                        contentDescription = "Gmail"
                    )
                }
                IconButton(onClick = { /* Handle Facebook */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.facebook_36), // Replace with Facebook icon drawable
                        contentDescription = "Facebook"
                    )
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun trs()
{
    SignInScreen()
}


