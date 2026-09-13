package com.example.myapplication

data class Task (
    val id: Int,
    val title: String,
    val isDone: Boolean = false
)