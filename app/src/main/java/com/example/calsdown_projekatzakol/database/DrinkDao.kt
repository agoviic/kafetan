package com.example.calsdown_projekatzakol.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.calsdown_projekatzakol.model.Drink
import com.example.calsdown_projekatzakol.model.Drinks

@Dao
interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDrink(drink: Drink) : Long

    @Query("SELECT * FROM cocktails")
    fun getDrinks() : LiveData<List<Drink>>
}