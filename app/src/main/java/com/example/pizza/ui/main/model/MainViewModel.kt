package com.example.pizza.ui.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pizza.R


class MainViewModel : ViewModel() {
    private var _currentPizzaCategory = MutableLiveData<String>()
    val currentPizzaCategory: LiveData<String> = _currentPizzaCategory

    private var _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> = _totalPrice

    init {
        resetOrder()
    }

    fun setPizzaCategory(category :String){
        _currentPizzaCategory.value = category
    }

    /*
    * Re-initializes the Pizza app data.
    */
    fun resetOrder() {
        _currentPizzaCategory.value = ""
    }
}