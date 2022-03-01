package com.example.pizza.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza.Pizza
import com.example.pizza.R
import com.example.pizza.ui.main.model.MainViewModel

class SummaryItemAdapter (private val sharedViewModel: MainViewModel
) : RecyclerView.Adapter<SummaryItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ItemViewHolder( view: View) : RecyclerView.ViewHolder(view) {
        val pizzaTitle: TextView = view.findViewById(R.id.pizza_title)
        val pizzaIngredients: TextView = view.findViewById(R.id.pizza_ingredients)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_summary_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = sharedViewModel.allPizzas.value!!.toList()[position]
        holder.pizzaTitle.text = item.category
        holder.pizzaIngredients.text = item.recipetoString()
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = sharedViewModel.allPizzas.value!!.size
}