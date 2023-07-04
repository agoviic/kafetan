package com.example.calsdown_projekatzakol.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calsdown_projekatzakol.model.Drink
import com.example.calsdown_projekatzakol.model.Drinks
import com.example.calsdown_projekatzakol.repository.CocktailRepository
import com.example.calsdown_projekatzakol.utils.NetworkResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (val cocktailRepository: CocktailRepository) : ViewModel() {

        val drinkList: MutableLiveData<NetworkResponse<Drinks>> = MutableLiveData()
        val searchedDrinks: MutableLiveData<NetworkResponse<Drinks>> = MutableLiveData()

        init {
            getDrinks()
        }

        fun getDrinks() = viewModelScope.launch {
            drinkList.postValue(NetworkResponse.Loading())
            val drinks = cocktailRepository.getDrinks()
            drinkList.postValue(handleResponse(drinks))
        }
        fun searchDrinks(query: String) = viewModelScope.launch {
            searchedDrinks.postValue(NetworkResponse.Loading())
            val drinks = cocktailRepository.searchDrinks(query)
            searchedDrinks.postValue(handleResponse(drinks!!))
        }
        private fun handleResponse(response: Response<Drinks>) : NetworkResponse<Drinks>{
            if(response.isSuccessful){
                response.body()?.let { drinks ->
                    return NetworkResponse.Success(drinks)
                }
            }
            return NetworkResponse.Error(response.message())
        }
    fun addDrinkToDatabase(drink: Drink){
        viewModelScope.launch {
            cocktailRepository.insertDrink(drink)
        }
    }
    fun getFavDrinks() : LiveData<List<Drink>> {
        return cocktailRepository.getFavCocktails()
    }
}
