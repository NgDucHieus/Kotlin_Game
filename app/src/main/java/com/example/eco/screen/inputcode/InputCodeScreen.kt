import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreatePinScreen() {
    var pin by remember { mutableStateOf("") }
    var isPinVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create New Pin") },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF6F6F6)),
                contentAlignment = Alignment.Center // Ensure everything inside is centered
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp) // Spacing between elements
                ) {
                    Text(
                        text = "Add a Pin Number to Make Your Account\nmore Secure",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        repeat(4) { index ->
                            OutlinedTextField(
                                value = if (index < pin.length) pin[index].toString() else "",
                                onValueChange = {},
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(50.dp)
                                ,
                                textStyle = LocalTextStyle.current.copy(
                                    textAlign = TextAlign.Center,
                                    fontSize = 14.sp
                                ),
                                enabled = false,
                                visualTransformation = if (isPinVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                shape = RoundedCornerShape(10.dp)
                            )
                        }
                    }

                    Button(
                        onClick = { /* Handle Continue Action */ },
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .padding(horizontal = 50.dp)
                            .fillMaxWidth(0.7f) // Adjust width
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3366FF))
                    ) {
                        Text(
                            text = "Continue",
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(30.dp)) // Apply the rounded corners
                                .background(Color.White), // Set the background color
                            contentAlignment = Alignment.Center // Center the Icon inside the Box
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Continue",
                                tint = Color(9,97,245) // Leave the tint as it is, or apply a color if desired
                            )
                        }

                    }

                    PinPad(
                        isPinVisible = isPinVisible,
                        onKeyPress = { key ->
                            if (key == "DEL" && pin.isNotEmpty()) {
                                pin = pin.dropLast(1)
                            } else if (key != "DEL" && pin.length < 4) {
                                pin += key
                            }
                        },
                        onVisibilityToggle = { isPinVisible = !isPinVisible }
                    )
                }
            }
        }
    )
}

@Composable
fun PinPad(
    isPinVisible: Boolean,
    onKeyPress: (String) -> Unit,
    onVisibilityToggle: () -> Unit
) {
    val keys = listOf(
        "1", "2", "3",
        "4", "5", "6",
        "7", "8", "9",
        "*", "0", "DEL"
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Center the keypad horizontally
        modifier = Modifier.fillMaxWidth() // Ensure it takes the full width of the screen
    ) {
        keys.chunked(3).forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally) // Center each row horizontally
            ) {
                row.forEach { key ->
                    Button(
                        onClick = {
                            if (key == "DEL") onKeyPress(key) else if (key != "*") onKeyPress(key)
                        },
                        modifier = Modifier
                            .size(70.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text(text = key, fontSize = 18.sp)
                    }
                }
            }
        }

        Button(
            onClick = { onVisibilityToggle() },
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.CenterHorizontally), // Center the visibility toggle button
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(text = if (isPinVisible) "👁" else "👁‍🗨", fontSize = 18.sp)
        }
    }
}


@Preview
@Composable
fun Preview()
{
    CreatePinScreen()
}
