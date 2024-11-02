package com.myproject.project_279

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.core.content.ContextCompat

class MainPageActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var usernameTextView: TextView
    private lateinit var searchEditText: EditText
    private lateinit var searchIcon: ImageView
    private lateinit var adsViewPager: ViewPager2
    private lateinit var adsPagerAdapter: AdsPagerAdapter
    private lateinit var dots: Array<ImageView?>
    private lateinit var heartCheckBox1: CheckBox // First CheckBox
    private lateinit var heartCheckBox2: CheckBox // Second CheckBox
    private lateinit var buttonHome: ImageButton
    private lateinit var buttonFavorite: ImageButton
    private lateinit var buttonScan: ImageButton
    private lateinit var buttonCart: ImageButton
    private lateinit var buttonProfile: ImageButton
    private val adList = listOf(R.drawable.add1, R.drawable.add2, R.drawable.add3, R.drawable.add4)

    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)



        // Initialize views
        profileImageView = findViewById(R.id.imageView)
        usernameTextView = findViewById(R.id.username)
        searchEditText = findViewById(R.id.searchEditText)
        searchIcon = findViewById(R.id.searchIcon)
        adsViewPager = findViewById(R.id.adsViewPager)
        heartCheckBox1 = findViewById(R.id.heartIcon1) // Initialize first CheckBox
        heartCheckBox2 = findViewById(R.id.heartIcon2) // Initialize second CheckBox
        buttonHome = findViewById(R.id.button_home)
        buttonFavorite = findViewById(R.id.button_favorite)
        buttonScan = findViewById(R.id.button_scan)
        buttonCart = findViewById(R.id.button_cart)
        buttonProfile = findViewById(R.id.button_profile)

        // Set up the ads adapter
        adsPagerAdapter = AdsPagerAdapter(adList)
        adsViewPager.adapter = adsPagerAdapter

        // Set up the dots indicator
        setupDotsIndicator()

        // Set page change listener
        adsViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDotsIndicator(position)
            }
        })

        // Setup other listeners
        setupListeners()

        heartCheckBox1.setOnCheckedChangeListener { _, isChecked ->
            val color = if (isChecked) {
                ContextCompat.getColor(this, R.color.orange) // Change to selected color
            } else {
                ContextCompat.getColor(this, R.color.dark_icon) // Change to unselected color
            }
            heartCheckBox1.buttonTintList = ColorStateList.valueOf(color) // Change the color of the checkbox

            // Change the icon based on selection state
            heartCheckBox1.setBackgroundResource(if (isChecked) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
        }

        heartCheckBox2.setOnCheckedChangeListener { _, isChecked ->
            val color = if (isChecked) {
                ContextCompat.getColor(this, R.color.orange) // Change to selected color
            } else {
                ContextCompat.getColor(this, R.color.dark_icon) // Change to unselected color
            }
            heartCheckBox2.buttonTintList = ColorStateList.valueOf(color) // Change the color of the checkbox

            // Change the icon based on selection state
            heartCheckBox2.setBackgroundResource(if (isChecked) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
        }
        buttonHome.setOnClickListener {
            // Handle home action
        }
        buttonFavorite.setOnClickListener {
            // Handle favorite action
        }
        buttonScan.setOnClickListener {
            // Handle scan action
            val intent = Intent(this@MainPageActivity, ProductPageActivity::class.java)
            startActivity(intent)
        }
        buttonCart.setOnClickListener {
            // Handle cart action
            val intent = Intent(this@MainPageActivity, CartFragment::class.java)
            startActivity(intent)
        }
        buttonProfile.setOnClickListener {
            // Handle profile action
        }

    }


    private fun setupDotsIndicator() {
        val dotsIndicator = findViewById<LinearLayout>(R.id.dotsIndicator)
        dots = arrayOfNulls(adList.size)

        for (i in adList.indices) {
            val dot = ImageView(this)
            dot.setImageResource(R.drawable.rectangle_inactive) // Set inactive rectangle drawable
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(4, 0, 4, 0) // Margin between rectangles
            dot.layoutParams = params
            dotsIndicator.addView(dot)
            dots[i] = dot
        }

        updateDotsIndicator(0) // Set first rectangle active initially
    }

    private fun updateDotsIndicator(position: Int) {
        for (i in dots.indices) {
            dots[i]?.setImageResource(if (i == position) R.drawable.rectangle_active else R.drawable.rectangle_inactive)
        }
    }

    private fun setupListeners() {
        searchEditText.setOnEditorActionListener { _, _, _ ->
            val searchText = searchEditText.text.toString()
            println("Search text: $searchText")
            true
        }

        profileImageView.setOnClickListener {
            // Handle profile image click
        }
    }

}
