package ru.otus.compose.examples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CounterWithoutRemember() {
    var count = 0 // This variable resets on every recomposition
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Count: $count")
        Button(onClick = { count++ }) { // This won't work as expected
            Text(text = "Increment")
        }
    }
}

@Composable
fun CounterWithRemember() {
    var count by remember { mutableStateOf(0) } // Value is remembered
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Count: $count")
        Button(onClick = { count++ }) { // This now works as expected
            Text(text = "Increment")
        }
    }
}