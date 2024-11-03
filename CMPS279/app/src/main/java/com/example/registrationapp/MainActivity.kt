package com.example.registrationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.EditText
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.registrationapp.ui.theme.RegistrationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistrationAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var inputText by remember { mutableStateOf("") }
                    var displayText by remember { mutableStateOf("") }

                    // Layout for user input and display
                    Column(modifier = Modifier.padding(innerPadding)) {
                        EditText(
                            value = inputText,
                            onValueChange = { inputText = it },
                            placeholder = { Text("Enter text") }
                        )
                        Button(onClick = { displayText = inputText }) {
                            Text("Display")
                        }
                        Text(text = displayText)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RegistrationAppTheme {
        Greeting("Android")
    }
}
