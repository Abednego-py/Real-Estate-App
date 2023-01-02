package com.example.realestateapp.model

import com.example.realestateapp.R


class Datasource {
    fun loadImages(): List<Home> {
        return listOf<Home>(
            Home(R.drawable.image1),
            Home(R.drawable.image2),
            Home(R.drawable.image3),
            Home(R.drawable.image4),
            Home(R.drawable.image5),
            Home(R.drawable.image6),
            Home(R.drawable.image7),
            Home(R.drawable.image8),
            Home(R.drawable.image9),
            Home(R.drawable.image10),
        )
    }
}