package com.example.calsdown_projekatzakol.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.calsdown_projekatzakol.database.CocktailDatabase
import com.example.calsdown_projekatzakol.repository.CocktailRepository
import com.example.mvvmappclass.R
import com.example.mvvmappclass.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        val cocktailRepository = CocktailRepository(CocktailDatabase(this))
        val viewModelProviderFactory = MainViewModelProvider(drinksRepository = cocktailRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[MainViewModel::class.java]
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.drinksFragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView = binding.bottomNavigation
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.isInvisible = destination.id == R.id.singleItemFragment
        }
        bottomNavigationView.setupWithNavController(navController)
    }
}