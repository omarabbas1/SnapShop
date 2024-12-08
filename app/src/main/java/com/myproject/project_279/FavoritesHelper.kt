package com.myproject.project_279

import android.content.Context

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoritesHelper {

    companion object {
        private const val PREFS_NAME = "favoritesPrefs"
        private const val KEY_FAVORITES = "favorites"

        private val gson = Gson()


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


        fun addFavorite(context: Context, item: Item) {
            val favorites = getFavorites(context).toMutableList()
            if (!favorites.any { it.name == item.name }) {
                favorites.add(item)
                saveFavorites(context, favorites)
            }
        }


        fun removeFavorite(context: Context, item: Item) {
            val favorites = getFavorites(context).toMutableList()
            favorites.removeAll { it.name == item.name }
            saveFavorites(context, favorites)
        }


        fun isFavorite(context: Context, item: Item): Boolean {
            val favorites = getFavorites(context)
            return favorites.any { it.name == item.name }
        }


        fun saveFavorites(context: Context, favorites: List<Item>) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val favoritesString = gson.toJson(favorites)
            editor.putString(KEY_FAVORITES, favoritesString)
            editor.apply()
        }
    }
}