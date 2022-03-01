package com.example.pizza.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.pizza.R
import com.example.pizza.ui.main.model.MainViewModel

class PizzaTypeItemAdapter (private val dataset: List<String>,
                            private val sharedViewModel: MainViewModel
) : RecyclerView.Adapter<PizzaTypeItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ItemViewHolder( view: View) : RecyclerView.ViewHolder(view) {
        val radioButton: RadioButton = view.findViewById(R.id.radioButton)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_category_pizza_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.radioButton.text = item
        holder.radioButton.isChecked = sharedViewModel.currentPizza.value?.category.equals(item)

        holder.radioButton.setOnClickListener {
            sharedViewModel.setPizzaCategory(item)
            for (i in 0..dataset.size)
                notifyItemChanged(i)
        }

    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size
}