package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    UnitConverter()
                }
            }
        }
    }


    @Composable
    fun UnitConverter() {
        var inputValue by remember { mutableStateOf(" ") }
        var outputValue by remember { mutableStateOf(" ") }
        var inputUnit by remember { mutableStateOf("Meters") }
        var outputUnit by remember { mutableStateOf("Meters") }
        var iExpanded by remember { mutableStateOf(false) }
        var oExpanded by remember { mutableStateOf(false) }
        var conversionFactor = remember { mutableStateOf(1.00) }
        var oconversionFactor = remember { mutableStateOf(1.00) }

        fun convertUnits() {
            // ?:- elvis operator
            val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
            val result = (inputValueDouble * conversionFactor.value * 100 / oconversionFactor.value
                    ).roundToInt() / 100.0
            outputValue = result.toString()
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = "Unit Converter",
                style = MaterialTheme.typography.headlineMedium
                , modifier = Modifier.padding(26.dp))


            OutlinedTextField(value = inputValue,
                onValueChange = { inputValue =it},
                label = { Text(text = "Enter Value")
                convertUnits()})

            //here all the ui elements will be stacked on top of eachother
            Spacer(modifier = Modifier.height(26.dp))
            Row {

                //all ui elements will be stacked next to each other
                Box {
                    //input button
                    Button(onClick = { iExpanded = true }) {
                        Text(text = inputUnit )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = " Arrow Down ")
                    }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(text = { Text(text = "Centimeter") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Centimeters"
                                conversionFactor.value = 0.01
                                convertUnits()
                            })

                        DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.00
                            convertUnits()
                        })

                        DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        })

                        DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.03048
                            convertUnits()
                        })
                    }

                }
                Spacer(modifier = Modifier.width(16.dp))
                Box {
                    //output button
                    Button(onClick = { oExpanded = true }) {
                        Text(text = outputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = " Arrow Down ")
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(text = { Text(text = "Centimeter") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Centimeter"
                                oconversionFactor.value = 0.01
                                convertUnits()
                            })

                        DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oconversionFactor.value = 1.00
                            convertUnits()
                        })

                        DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oconversionFactor.value = 0.001
                            convertUnits()
                        })

                        DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oconversionFactor.value = 0.3048
                            convertUnits()
                        })
                    }
                }

            }
            Spacer(modifier = Modifier.height(26.dp))
            Text(text = "Result : $outputValue $outputUnit" ,
                style = MaterialTheme.typography.headlineMedium)
        }
    }

}
@Preview(showBackground = true)
@Composable
fun UnitConverter(){}




