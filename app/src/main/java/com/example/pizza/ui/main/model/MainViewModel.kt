package com.example.pizza.ui.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pizza.Pizza
import kotlin.random.Random


class MainViewModel : ViewModel() {

    private var _allPizzas = MutableLiveData<MutableSet<Pizza>>()
    val allPizzas: LiveData<MutableSet<Pizza>> = _allPizzas

    private var _currentPizza = MutableLiveData<Pizza>()
    val currentPizza: LiveData<Pizza> = _currentPizza

    private var _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> = _totalPrice

    private var _subTotalPrice = MutableLiveData<Double>()
    val subTotalPrice: LiveData<Double> = _subTotalPrice


    init {
        resetOrder()
    }

    fun setPizzaCategory(category :String){
        _currentPizza.value?.category  = category
        setPizzaIngredientsWithRecipe(currentPizza.value?.category.toString())
        calculateCurrentPrice()
    }

    fun setPizzaSize(size :String){
        _currentPizza.value?.size = size
        calculateCurrentPrice()
    }

    fun setPizzaIngredientsWithRecipe(category: String){
        _currentPizza.value?.ingredients = Pizza.recipe(category)
        calculateCurrentPrice()
    }

    fun addIngredient(ingredient: String){
        _currentPizza.value?.ingredients?.add(ingredient)
        calculateCurrentPrice()
    }

    fun removeIngredient(ingredient: String){
        _currentPizza.value?.ingredients?.remove(ingredient)
        calculateCurrentPrice()
    }

    fun addExtraIngredient(ingredient: String){
        _currentPizza.value?.extras?.add(ingredient)
        calculateCurrentPrice()
    }

    fun removeExtraIngredient(ingredient: String){
        _currentPizza.value?.extras?.remove(ingredient)
        calculateCurrentPrice()
    }

    fun addPizza(){
        _allPizzas.value?.remove(_currentPizza.value!!)
        _currentPizza.value!!.calculatePrice()
        _allPizzas.value?.add(_currentPizza.value!!)
        calculateTotalPrice()
    }

    fun calculateTotalPrice(){
        _totalPrice.value = 0.0
        for (pizza in allPizzas.value!!){
            _totalPrice.value = _totalPrice.value!! + pizza.price
        }
    }

    fun calculateCurrentPrice(){
        _currentPizza.value?.calculatePrice()
        _subTotalPrice.value = currentPizza.value!!.price
    }

    /*
    * Re-initializes the Pizza app data.
    */
    fun resetOrder() {
        _currentPizza.value = Pizza()
        _allPizzas.value = mutableSetOf()
        _totalPrice.value = 0.0
        _subTotalPrice.value = 0.0
    }
}