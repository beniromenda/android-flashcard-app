package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel= viewModel()){
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        when (val state= uiState){
            is DashboardUiState.Loading -> {
                CircularProgressIndicator()
                Spacer(Modifier.height(16.dp))
                Text("Loading dashboard ...")
            }
            is DashboardUiState.Success ->{
                Text("Name: ${state.profile.name}")
                Text("Bio: ${state.profile.bio}")
                Spacer(Modifier.height(8.dp))
                Text("Steps: ${state.stats.steps}")
                Text("Calories: ${state.stats.caloriesBurned}")
            }
            is DashboardUiState.Error ->{
                Text("Error: ${state.message}")
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = {viewModel.loadDashboard()}){
            Text("Reload")
        }
        Button(onClick= {viewModel.cancelLoad()}){
            Text("Cancel")

        }
    }
}