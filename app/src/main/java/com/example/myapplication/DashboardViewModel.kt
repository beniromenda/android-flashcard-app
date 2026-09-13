package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
sealed class DashboardUiState {
    object Loading: DashboardUiState()
    data class Success(val profile: Profile, val stats: DailyStats) : DashboardUiState()
    data class Error (val message: String): DashboardUiState()
}
//Inheriting from build viewModel class
class DashboardViewModel : ViewModel(){
    //instatntiating the Fake API class we created
    private val api= FakeApi()
    private var loadJob: Job ?=null

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState

    fun loadDashboard(){
        loadJob?.cancel()// cancel any in-flight load before starting
        loadJob=viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            try{
                //This is sequential as fetchProfile finishes before
                //Network calls now run concurrently
                val profileDeferred = async {api.fetchProfile()}
                val statsDeferred=  async {api.fetchDailyStats()}

                val profile = profileDeferred.await()
                val stats = statsDeferred.await()
                _uiState.value= DashboardUiState.Success(profile, stats)
            }catch(e: Exception){
                _uiState.value = DashboardUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
    fun cancelLoad(){
        loadJob?.cancel()
        _uiState.value=DashboardUiState.Error("Cancelled by user")
    }
}