package com.example.realestateapp.model.search.data

import android.app.Application

class SearchApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database: SearchDatabase by lazy {
        SearchDatabase.getDatabase(this)
    }
}