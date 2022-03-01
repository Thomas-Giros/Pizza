package com.example.pizza.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizza.Pizza
import com.example.pizza.R
import com.example.pizza.databinding.FragmentSummaryBinding
import com.example.pizza.ui.main.adapter.SummaryItemAdapter
import com.example.pizza.ui.main.model.MainViewModel

class SummaryFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentSummaryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentSummaryBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentSummaryBinding
        return fragmentSummaryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize data.

        binding?.apply {
            pizzaTypeRecyclerView.adapter = SummaryItemAdapter( sharedViewModel)
            pizzaTypeRecyclerView.setHasFixedSize(true)
            pizzaTypeRecyclerView.layoutManager = LinearLayoutManager(context)
            summaryFragment = this@SummaryFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    /**
     * Navigate to the next screen to choose pickup date.
     */
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_summaryFragment_to_endFragment)
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