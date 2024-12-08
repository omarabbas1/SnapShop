package com.myproject.project_279

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : AppCompatActivity() {

    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var emptyImageView: View
    private lateinit var emptyTextView: TextView
    private lateinit var subTextView: TextView
    private lateinit var returnButton: Button
    private lateinit var proceedButton: Button
    private var cartItems = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_fragment)


        cartRecyclerView = findViewById(R.id.cart_list)
        emptyImageView = findViewById(R.id.empty_cart_image)
        emptyTextView = findViewById(R.id.cart_empty_text)
        subTextView = findViewById(R.id.cart_empty_subtext)
        returnButton = findViewById(R.id.button_return_home)
        proceedButton = findViewById(R.id.button_proceed_to_checkout)


        cartItems = AddToCartHelper.getCartItems(this).toMutableList()


        if (cartItems.isNotEmpty()) {
            cartRecyclerView.visibility = View.VISIBLE
            emptyImageView.visibility = View.GONE
            emptyTextView.visibility = View.GONE
            subTextView.visibility = View.GONE
            returnButton.visibility = View.GONE
            proceedButton.visibility = View.VISIBLE


            val adapter = AddToCartAdapter(cartItems, this) { item ->

                cartItems.remove(item)
                AddToCartHelper.saveCartItems(this, cartItems)


                if (cartItems.isEmpty()) {
                    cartRecyclerView.visibility = View.GONE
                    emptyImageView.visibility = View.VISIBLE
                    emptyTextView.visibility = View.VISIBLE
                    subTextView.visibility = View.VISIBLE
                    returnButton.visibility = View.VISIBLE
                    proceedButton.visibility = View.GONE
                } else {

                    cartRecyclerView.adapter?.notifyDataSetChanged()
                }
            }

            cartRecyclerView.layoutManager = LinearLayoutManager(this)
            cartRecyclerView.adapter = adapter
        } else {

            cartRecyclerView.visibility = View.GONE
            emptyImageView.visibility = View.VISIBLE
            emptyTextView.visibility = View.VISIBLE
            subTextView.visibility = View.VISIBLE
            returnButton.visibility = View.VISIBLE
            proceedButton.visibility = View.GONE
        }

        proceedButton.setOnClickListener {

            val checkoutIntent = Intent(this, CartActivity::class.java)
            checkoutIntent.putParcelableArrayListExtra("cartItems", ArrayList(cartItems))
            startActivity(checkoutIntent)
        }


        returnButton.setOnClickListener {
            startActivity(Intent(this, MainPageActivity::class.java))
        }


        findViewById<ImageButton>(R.id.home_button).setOnClickListener {
            startActivity(Intent(this, MainPageActivity::class.java))
        }

        findViewById<ImageButton>(R.id.favorite_button).setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }

        findViewById<ImageButton>(R.id.scan_button).setOnClickListener {
            startActivity(Intent(this, ProductPageActivity::class.java))
        }

        findViewById<ImageButton>(R.id.profile_button).setOnClickListener {
            startActivity(Intent(this, ProfilePage::class.java))
        }
    }
}
