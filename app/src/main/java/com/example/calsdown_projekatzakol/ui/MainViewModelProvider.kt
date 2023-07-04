package com.example.calsdown_projekatzakol.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calsdown_projekatzakol.repository.CocktailRepository

class MainViewModelProvider(val drinksRepository: CocktailRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(drinksRepository) as T
    }
}