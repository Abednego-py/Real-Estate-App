package com.example.realestateapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val use: String,
    val title: String,
    val time: String
)