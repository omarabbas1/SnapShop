package com.myproject.project_279

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddToCartHelper {

    companion object {
        private const val PREFS_NAME = "cartPrefs"
        private const val KEY_CART_ITEMS = "cartItems"

        private val gson = Gson()

        // Get the list of cart items
        fun getCartItems(context: Context): List<Item> {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val cartItemsString = prefs.getString(KEY_CART_ITEMS, null)
            return if (cartItemsString != null) {
                val type = object : TypeToken<List<Item>>() {}.type
                gson.fromJson(cartItemsString, type) ?: emptyList()
            } else {
                emptyList()
            }
        }

        // Add an item to the cart
        fun addItemToCart(context: Context, item: Item) {
            val cartItems = getCartItems(context).toMutableList()
            if (!cartItems.any { it.name == item.name }) { // Prevent duplicate by name
                cartItems.add(item)
                saveCartItems(context, cartItems)
            }
        }

        // Save the list of cart items
        fun saveCartItems(context: Context, cartItems: List<Item>) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val cartItemsString = gson.toJson(cartItems)
            editor.putString(KEY_CART_ITEMS, cartItemsString)
            editor.apply()
        }
    }
}
