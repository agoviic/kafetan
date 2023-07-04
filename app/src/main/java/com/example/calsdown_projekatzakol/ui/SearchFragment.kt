package com.example.calsdown_projekatzakol.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calsdown_projekatzakol.adapters.Adapter
import com.example.calsdown_projekatzakol.utils.NetworkResponse
import com.example.mvvmappclass.R
import com.example.mvvmappclass.databinding.FragmentSearchBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var drinksAdapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initRV()
        drinksAdapter.setOnItemClickListener {

        }
        var job: Job? = null
        binding.etSearch.addTextChangedListener {query->
            job?.cancel()
            job = MainScope().launch {
                delay(300)
                if(query.toString().isNotEmpty()){
                    viewModel.searchDrinks(query = query.toString())
                }
            }
        }

        viewModel.searchedDrinks.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Success -> {
                    response.data?.let { drinks ->
                        drinksAdapter.differ.submitList(drinks.drinks)
                    }
                }
                is NetworkResponse.Loading -> {
                }
                is NetworkResponse.Error -> {
                }
            }
        }

        drinksAdapter.setOnItemClickListener {drink->
            val bundle = Bundle().apply {
                putSerializable("drink", drink)
            }
            findNavController().navigate(R.id.action_searchFragment_to_singleItemFragment, bundle)
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