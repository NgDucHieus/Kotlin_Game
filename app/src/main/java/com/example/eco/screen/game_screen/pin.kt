import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CreateDPinScreen() {
    val pinLength = 6
    val pinState = remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        // Request focus on the TextField to open the keyboard
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create a PIN",
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                },
                backgroundColor = Color.White,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        backgroundColor = Color(0xFFF9F9F9)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Instruction Text
                Text(
                    text = "Finally, Your final step....\nEnter 6 numbers to keep your account safe",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(32.dp))

                // PIN Circles Row
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    for (i in 0 until pinLength) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    if (i < pinState.value.length) Color.Black else Color.LightGray,
                                    CircleShape
                                )
                        )
                    }
                }

                // Invisible TextField to capture keyboard input
                TextField(
                    value = pinState.value,
                    onValueChange = {
                        // Limit input to 6 digits
                        if (it.length <= pinLength && it.all { char -> char.isDigit() }) {
                            pinState.value = it
                        }
                    },
                    modifier = Modifier
                        .size(1.dp) // Make it invisible
                        .focusRequester(focusRequester) // Attach focus requester
                        .background(Color.Transparent), // Invisible background
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    visualTransformation = VisualTransformation.None,
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
            }

            // Confirm Button
            Button(
                onClick = {
                    // Handle Confirm
                },
                enabled = pinState.value.length == pinLength,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (pinState.value.length == pinLength) Color(0xFF4CAF50) else Color.LightGray
                )
            ) {
                Text(
                    text = "Confirm",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}
@Preview
@Composable
fun  rte()
{
    CreateDPinScreen()
}
