package com.example.realestateapp.model.search.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    suspend fun create(searchItems: SearchItems)

    @Query("select * from SearchItems")
    fun getSearchItems() : Flow<List<SearchItems>>

    @Query("SELECT * from SearchItems WHERE id = :id")
    fun getSearchItem(id : Int) : Flow<SearchItems>
}