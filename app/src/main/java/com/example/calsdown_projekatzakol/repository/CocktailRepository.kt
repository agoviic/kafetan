package com.example.calsdown_projekatzakol.repository

import com.example.calsdown_projekatzakol.api.RetrofitInstance
import com.example.calsdown_projekatzakol.database.CocktailDatabase
import com.example.calsdown_projekatzakol.model.Drink

class CocktailRepository(val cocktailDatabase: CocktailDatabase) {
    suspend fun getDrinks() = RetrofitInstance.api!!.getDrinks()
    suspend fun searchDrinks(query: String) = RetrofitInstance.api!!.searchDrink(query = query)
    suspend fun insertDrink(drink: Drink) = cocktailDatabase.getDrinkDao().insertDrink(drink)
    fun getFavCocktails() = cocktailDatabase.getDrinkDao().getDrinks()
}