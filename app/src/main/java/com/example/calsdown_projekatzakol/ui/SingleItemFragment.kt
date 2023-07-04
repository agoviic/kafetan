package com.example.calsdown_projekatzakol.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mvvmappclass.R
import com.example.mvvmappclass.databinding.FragmentSingleItemBinding

class SingleItemFragment : Fragment(R.layout.fragment_single_item) {
    private lateinit var binding: FragmentSingleItemBinding
    private lateinit var viewModel: MainViewModel
    private val args: SingleItemFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingleItemBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        var drink = args.drink
        binding.apply {
            Glide.with(ivSlika.context).load(drink!!.strDrinkThumb).override(300,300).fitCenter().into(ivSlika)
            binding.tvDrinkId.text = drink!!.idDrink
            binding.tvInstruction.text = drink!!.strInstructions
            star.setOnClickListener {
                Toast.makeText(context, "Drink added to favourite drinks!", Toast.LENGTH_SHORT).show()
                viewModel.addDrinkToDatabase(drink)
            }
            back.setOnClickListener {
                findNavController().navigate(R.id.action_singleItemFragment_to_coffeeFragment)
            }
        }
    }
}