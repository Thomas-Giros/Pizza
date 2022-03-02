package com.example.pizza.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizza.R
import com.example.pizza.databinding.FragmentIngredientsBinding
import com.example.pizza.ui.main.adapter.ExtrasItemAdapter
import com.example.pizza.ui.main.adapter.IngredientsItemAdapter
import com.example.pizza.ui.main.adapter.PizzaTypeItemAdapter
import com.example.pizza.ui.main.model.MainViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pizza.Pizza


class IngredientsFragment: Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentIngredientsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ingredientsBinding = FragmentIngredientsBinding.inflate(inflater, container, false)
        binding = ingredientsBinding
        return ingredientsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize data.
        val ingredients = Pizza.ingredients
        // setting the pizza category to custom (the first element of the array of pizza categories)

        binding?.apply {
            ingredientsRecyclerView.adapter = IngredientsItemAdapter( ingredients, sharedViewModel)
            ingredientsRecyclerView.setHasFixedSize(true)
            ingredientsRecyclerView.layoutManager = GridLayoutManager(context, 2)


            extrasRecyclerView.adapter = ExtrasItemAdapter( ingredients, sharedViewModel)
            extrasRecyclerView.setHasFixedSize(true)
            extrasRecyclerView.layoutManager = GridLayoutManager(context, 2)

            ingredientsFragment = this@IngredientsFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }


    /**
     * Navigate to the next screen to choose pickup date.
     */
    fun goToNextScreen() {
        sharedViewModel.addPizza()
        findNavController().navigate(R.id.action_ingredientsFragment_to_summaryFragment)
    }

    fun cancelOrder() {
        findNavController().navigate(R.id.action_ingredientsFragment_to_pizzaTypeFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}