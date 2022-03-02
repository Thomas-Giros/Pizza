package com.example.pizza

import android.R.string


class Pizza {
    var category = ""
    var price = 0.0
    var ingredients = mutableListOf<String>()
    var extras = mutableListOf<String>()
    var size = ""
    var numberOrdered = 1

    fun titleToString(): String{
        return category.plus(" -- ").plus(size).plus(" - ").plus(numberOrdered).plus("x")
    }

    fun recipetoString(): String{
        var recipe = ingredients.joinToString(", ","--  ","  --\n")
        recipe = recipe.plus(extras.joinToString(", ", "Extras : ","\n"))
        recipe = recipe.plus("Price  : $price")
        return recipe
    }

    fun calculatePrice(){
        price = 0.0
        when (size)
        {
            "S" -> price = 6.0
            "M" -> price = 8.0
            "L" -> price = 11.0
            "XL" -> price = 15.0
            "XXL" -> price = 20.0
        }
        if (ingredients.size > 2)
            price += (ingredients.size - 2) * 2.0
        price += extras.size * 1.5
        price *= numberOrdered
    }


    companion object PizzaData {
        val ingredients = ingredientsList
        val sizes = pizzaSizeList
        val categories = pizzaCategoryList

        fun recipe(category: String): MutableList<String> {
            var recipe = listOf<String>()

            when(category)
            {
                "custom" -> recipe = listOf("provolone")
                "neapolitan" -> recipe = listOf("mozzarella","basil")
                "pepperoni" -> recipe = listOf("pepperoni","mozzarella")
                "vegetarian" -> recipe = listOf("basil","onion","mushrooms","pepper")
                "four cheeses" -> recipe = listOf("mozzarella", "provolone", "gorgonzola", "cheddar",)
                "pineapple" -> recipe = listOf("mozzarella","ham","pineapple")
            }

            return recipe.toMutableList()
        }
    }
}



// List with all the ingredients for a pizza
val ingredientsList: List<String> =
    listOf(
        "mozzarella",
        "provolone",
        "gorgonzola",
        "cheddar",
        "pepperoni",
        "bacon",
        "chicken",
        "beef",
        "ham",
        "basil",
        "cream",
        "potato",
        "pepper",
        "onion",
        "mushrooms",
        "pineapple"
    )

val pizzaCategoryList: List<String> =
    listOf(
        "custom",
        "neapolitan",
        "pepperoni",
        "vegetarian",
        "four cheeses",
        "pineapple"
    )

val pizzaSizeList: List<String> =
    listOf(
        "S",
        "M",
        "L",
        "XL",
        "XXL"
    )
