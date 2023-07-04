package com.example.calsdown_projekatzakol.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calsdown_projekatzakol.adapters.Adapter
import com.example.calsdown_projekatzakol.model.Drink
import com.example.calsdown_projekatzakol.utils.NetworkResponse
import com.example.mvvmappclass.R
import com.example.mvvmappclass.databinding.FragmentCoffeeBinding

class CoffeeFragment : Fragment(R.layout.fragment_coffee) {

    private lateinit var binding: FragmentCoffeeBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var drinksAdapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoffeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initRV()
        viewModel.drinkList.observe(viewLifecycleOwner){response->
            when(response){
                is NetworkResponse.Success -> {
                    response.data?.let { drinkResponse ->
                        drinksAdapter.differ.submitList(drinkResponse.drinks)
                    }
                }
                is NetworkResponse.Error -> {
                    response.message?.let {
                    }
                }
                is NetworkResponse.Loading -> {
                    response.message?.let {
                    }
                }
            }
        }
        drinksAdapter.setOnItemClickListener {drink->
            val bundle = Bundle().apply {
                putSerializable("drink", drink)
            }
            findNavController().navigate(R.id.action_coffeeFragment_to_singleItemFragment, bundle)
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
