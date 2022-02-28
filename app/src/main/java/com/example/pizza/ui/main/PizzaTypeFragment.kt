package com.example.pizza.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizza.R
import com.example.pizza.databinding.FragmentPizzaTypeBinding
import com.example.pizza.ui.main.adapter.PizzaTypeItemAdapter
import com.example.pizza.ui.main.model.MainViewModel
import androidx.navigation.fragment.findNavController


class PizzaTypeFragment: Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentPizzaTypeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val pizzaTypeBinding = FragmentPizzaTypeBinding.inflate(inflater, container, false)
        binding = pizzaTypeBinding
        return pizzaTypeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize data.
        val myDataset = resources.getStringArray(R.array.pizza_categories).toList()
        // setting the pizza category to custom (the first element of the array of pizza categories)
        sharedViewModel.setPizzaCategory(resources.getStringArray(R.array.pizza_categories)[0])

        binding?.apply {
            pizzaTypeRecyclerView.adapter = PizzaTypeItemAdapter( myDataset, sharedViewModel)
            pizzaTypeRecyclerView.setHasFixedSize(true)
            pizzaTypeRecyclerView.layoutManager = LinearLayoutManager(context)
            pizzaTypeFragment = this@PizzaTypeFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }


    /**
     * Navigate to the next screen to choose pickup date.
     */
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_pizzaTypeFragment_to_ingredientsFragment)
    }

    fun cancelOrder() {
//        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
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