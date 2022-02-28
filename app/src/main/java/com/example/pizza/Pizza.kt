package com.example.pizza

import android.R.string


class Pizza {
    var name = ""
    var ingredients = listOf<String>()
    var extras = listOf<String>()
    var size = ""
    var price = 0.0

    fun recipetoString(): String{
        var recipe = ingredients.joinToString(", ","--  ","  --\n")
        recipe = recipe.plus(extras.joinToString(", ", "Extras : ","\n"))
        recipe = recipe.plus("Price  : $price")
        return recipe
    }
}