package com.myproject.project_279

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainPageActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var usernameTextView: TextView
    private lateinit var searchEditText: EditText
    private lateinit var searchIcon: ImageView
    private lateinit var adsViewPager: ViewPager2
    private lateinit var adsPagerAdapter: AdsPagerAdapter
    private lateinit var dots: Array<ImageView?>
    private val adList = listOf(R.drawable.add1, R.drawable.add2, R.drawable.add3, R.drawable.add4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        // Initialize views
        profileImageView = findViewById(R.id.imageView)
        usernameTextView = findViewById(R.id.username)
        searchEditText = findViewById(R.id.searchEditText)
        searchIcon = findViewById(R.id.searchIcon)
        adsViewPager = findViewById(R.id.adsViewPager)

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
