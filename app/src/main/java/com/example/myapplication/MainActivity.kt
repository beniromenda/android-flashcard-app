package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This tells the activity what to display on the screen
        setContent {
            FlashcardApp()
        }
    }
}

@Composable
fun FlashcardApp() {
    // This is our 'State'. When it changes, the UI redraws.
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. The Interactive Card
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }, // Toggles state on tap
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Finite State Machine",
                    style = MaterialTheme.typography.titleLarge
                )

                // The UI reacts to the State
                if (isExpanded) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "A mathematical model of computation used to design both computer programs and sequential logic circuits.")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 2. Button with an Icon
        Button(onClick = { /* Add logic to load next term */ }) {
            Text(text = "Next Term")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next"
            )
        }
    }
}
