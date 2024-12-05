package com.myproject.project_279

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoritesHelper {

    companion object {
        private const val PREFS_NAME = "favoritesPrefs"
        private const val KEY_FAVORITES = "favorites"

        private val gson = Gson()

        // Get the list of favorite items
        fun getFavorites(context: Context): List<Item> {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val favoritesString = prefs.getString(KEY_FAVORITES, null)
            return if (favoritesString != null) {
                val type = object : TypeToken<List<Item>>() {}.type
                gson.fromJson(favoritesString, type) ?: emptyList()
            } else {
                emptyList()
            }
        }

        // Add a favorite item
        fun addFavorite(context: Context, item: Item) {
            val favorites = getFavorites(context).toMutableList()
            if (!favorites.any { it.name == item.name }) { // Prevent duplicate by name
                favorites.add(item)
                saveFavorites(context, favorites)
            }
        }

        // Remove a favorite item
        fun removeFavorite(context: Context, item: Item) {
            val favorites = getFavorites(context).toMutableList()
            favorites.removeAll { it.name == item.name }
            saveFavorites(context, favorites)
        }

        // Check if an item is a favorite
        fun isFavorite(context: Context, item: Item): Boolean {
            val favorites = getFavorites(context)
            return favorites.any { it.name == item.name }
        }

        // Save the list of favorite items
        fun saveFavorites(context: Context, favorites: List<Item>) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val favoritesString = gson.toJson(favorites)
            editor.putString(KEY_FAVORITES, favoritesString)
            editor.apply()
        }
    }
}
