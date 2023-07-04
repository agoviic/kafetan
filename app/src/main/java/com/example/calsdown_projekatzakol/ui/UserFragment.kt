package com.example.calsdown_projekatzakol.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calsdown_projekatzakol.adapters.Adapter
import com.example.calsdown_projekatzakol.adapters.ViewPagerAdapter
import com.example.mvvmappclass.R
import com.example.mvvmappclass.databinding.FragmentUserBinding
import com.google.android.material.tabs.TabLayoutMediator


class UserFragment : Fragment(R.layout.fragment_user) {

    private lateinit var drinksAdapter: Adapter
    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: MainViewModel

    private val images = listOf(
        R.drawable.coffee_brand2,
        R.drawable.coffee_brand5
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserBinding.bind(view)
        initRV()
        viewModel = (activity as MainActivity).viewModel
        binding.btnLogout.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        viewModel.getFavDrinks().observe(viewLifecycleOwner) { drink ->
            drinksAdapter.differ.submitList(drink)
        }
    }

    private fun initRV(){
        drinksAdapter = Adapter()
        binding.rvKafa.apply {
            adapter = drinksAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}