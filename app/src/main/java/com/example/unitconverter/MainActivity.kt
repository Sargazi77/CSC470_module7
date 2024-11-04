package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ConversionScreen()
                }
            }
        }
    }
}

@Composable
fun ConversionScreen() {
    var input by remember { mutableStateOf("") }
    var fromUnit by remember { mutableStateOf("Celsius") }
    var toUnit by remember { mutableStateOf("Fahrenheit") }
    var result by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }  // Track error messages

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Enter Value") },
            modifier = Modifier.fillMaxWidth()
        )

        UnitDropdown(selectedUnit = fromUnit, onUnitSelected = { fromUnit = it }, label = "From Unit")
        UnitDropdown(selectedUnit = toUnit, onUnitSelected = { toUnit = it }, label = "To Unit")

        Button(
            onClick = {
                // Reset error message
                errorMessage = ""
                result = ""

                val inputValue = input.toDoubleOrNull()
                if (inputValue != null) {
                    try {
                        // Attempt the conversion
                        result = Converter.convert(inputValue, fromUnit, toUnit).toString()
                    } catch (e: IllegalArgumentException) {
                        // Display error message for unsupported conversions
                        errorMessage = e.message ?: "Conversion error"
                    }
                } else {
                    errorMessage = "Invalid input"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convert")
        }

        // Display result or error message based on the outcome
        if (errorMessage.isNotEmpty()) {
            Text(
                text = "Error: $errorMessage",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        } else {
            Text(
                text = "Result: $result",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun UnitDropdown(selectedUnit: String, onUnitSelected: (String) -> Unit, label: String) {
    val units = listOf("Celsius", "Fahrenheit", "Meters", "Feet", "Kilograms", "Pounds")
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        Button(onClick = { expanded = true }) {
            Text(selectedUnit)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            units.forEach { unit ->
                DropdownMenuItem(
                    text = { Text(unit) },
                    onClick = {
                        onUnitSelected(unit)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConversionScreenPreview() {
    UnitConverterTheme {
        ConversionScreen()
    }
}