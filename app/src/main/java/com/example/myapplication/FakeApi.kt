package com.example.myapplication

import kotlinx.coroutines.delay
import kotlin.time.Duration.*

data class Profile (val name: String, val bio: String)
data class DailyStats(val steps: Int, val caloriesBurned:Int)
//Fetching Profile and Daily stats from two fake data sources
class FakeApi {
    // simulates a slow network call

    suspend fun fetchProfile(): Profile {
        delay(2000)
        return Profile("Benir Odeny", "Andriod Developer")
    }

    suspend fun fetchDailyStats(): DailyStats {
        delay(2000)
        return DailyStats(5000,512)
    }
}