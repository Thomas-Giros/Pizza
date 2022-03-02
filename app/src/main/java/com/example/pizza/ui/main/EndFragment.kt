package com.example.pizza.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pizza.R
import com.example.pizza.databinding.FragmentEndBinding
import com.example.pizza.databinding.FragmentSummaryBinding
import com.example.pizza.ui.main.model.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EndFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_summary.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentEndBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentEndBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            endFragment = this@EndFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    /**
     * Submit the order by sharing out the order details to another app via an implicit intent.
     */
    fun sendOrder() {
        sharedViewModel.setClientName(binding?.endFragment?.binding?.TextInputEditTextName?.text.toString())
        sharedViewModel.setClientAdress(binding?.endFragment?.binding?.TextInputEditTextAddress?.text.toString())

        if (sharedViewModel.clientAdress.value.isNullOrEmpty() || sharedViewModel.clientName.value.isNullOrEmpty() )
            Toast.makeText(context,"Information missing", Toast.LENGTH_SHORT).show()
        else
        {
            val orderSummary = sharedViewModel.allPizzasToString()
            val intent = Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, "pizza order")
                .putExtra(Intent.EXTRA_TEXT, orderSummary)

            if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
                startActivity(intent)
            }
        }

    }


    private fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_endFragment_to_summaryFragment)
    }

    /*
* Creates and shows an AlertDialog with the final score.
*/
    fun showCancelConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Cancel order")
            .setMessage("Press Cancel to confirm the cancel order")
            .setCancelable(true)
            .setNegativeButton("Cancel") { _, _ ->
                cancelOrder()
            }
            .show()
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