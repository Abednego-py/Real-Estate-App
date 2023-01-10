package com.example.realestateapp.model.search.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchItems (
        @PrimaryKey(autoGenerate = true)
        val id : Int = 0,
        @ColumnInfo(name = "search_text")  val searchText : String?
        )