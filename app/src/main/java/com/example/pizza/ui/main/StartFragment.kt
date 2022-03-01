package com.example.pizza.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizza.Pizza
import com.example.pizza.R
import com.example.pizza.databinding.FragmentStartBinding
import com.example.pizza.ui.main.model.MainViewModel

class StartFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    // Binding object instance corresponding to the fragment_start.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentStartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            startFragment = this@StartFragment
        }
    }

    /**
     * Start an order with the desired quantity of cupcakes and navigate to the next screen.
     */
    fun goToNextFragment() {
        // setting the pizza category to custom (the first element of the array of pizza categories)
        sharedViewModel.setPizzaCategory(Pizza.categories[0])

        // setting the pizza size to medium
        sharedViewModel.setPizzaSize(Pizza.sizes[1])
        findNavController().navigate(R.id.action_startFragment_to_pizzaTypeFragment)
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