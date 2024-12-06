package com.myproject.project_279


import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPageActivity : AppCompatActivity() {
    private lateinit var searchResultsRecyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchInput: EditText
    private lateinit var searchButton: Button


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

    // Save state variables
    private var searchText: String? = null
    private var isHeart1Checked: Boolean = false
    private var isHeart2Checked: Boolean = false

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
            isHeart1Checked = isChecked
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
            isHeart2Checked = isChecked
        }

        buttonHome.setOnClickListener {
            // Handle home action
        }
        buttonFavorite.setOnClickListener {
            val intent = Intent(this@MainPageActivity, FavoritesActivity::class.java)
            startActivity(intent)
            // Handle favorite action
        }
//        buttonScan.setOnClickListener {
//            // Handle scan action
//            val intent = Intent(this@MainPageActivity, ProductPageActivity::class.java)
//            startActivity(intent)
//        }
        buttonCart.setOnClickListener {
            // Handle cart action
            val intent = Intent(this@MainPageActivity, CartFragment::class.java)
            startActivity(intent)
        }
        buttonProfile.setOnClickListener {
            // Handle profile action
            val intent = Intent(this@MainPageActivity, ProfilePage::class.java)
            startActivity(intent)
        }
        val fashionImageView = findViewById<ImageView>(R.id.category_fashion)
        fashionImageView.setOnClickListener {
            val intent = Intent(this@MainPageActivity, FashionCategoryActivity::class.java)
            startActivity(intent)
        }
        val ShoesImageView = findViewById<ImageView>(R.id.category_shoes)
        ShoesImageView.setOnClickListener {
            val intent = Intent(this@MainPageActivity, ShoesCategoryActivity::class.java)
            startActivity(intent)
        }
        val ElectronicImageView = findViewById<ImageView>(R.id.category_electronics)
        ElectronicImageView.setOnClickListener {
            val intent = Intent(this@MainPageActivity, ElectronicsCategoryActivity::class.java)
            startActivity(intent)
        }
        val SportImageView = findViewById<ImageView>(R.id.category_sports)
        SportImageView.setOnClickListener {
            val intent = Intent(this@MainPageActivity, SportsCategoryActivity::class.java)
            startActivity(intent)
        }
        val CosmeticImageView = findViewById<ImageView>(R.id.category_cosmetics)
        CosmeticImageView.setOnClickListener {
            val intent = Intent(this@MainPageActivity, CosmeticsCategoryActivity::class.java)
            startActivity(intent)
        }



        // Inside MainPageActivity, where the search icon is clicked
        searchIcon.setOnClickListener {
            val searchText = searchEditText.text.toString()
            if (searchText.isNotEmpty()) {
                val intent = Intent(this, SearchResultsActivity::class.java)
                intent.putExtra("SEARCH_QUERY", searchText)  // Pass the search query to the next activity
                startActivity(intent)
            }
        }

    }

    private fun searchItems(query: String) {
        val call = ApiClient.retrofitService.searchItems(query)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val searchResponse = response.body()
                    if (searchResponse != null && searchResponse.items.isNotEmpty()) {
                        searchAdapter.updateItems(searchResponse.items)
                    } else {
                        Toast.makeText(this@MainPageActivity, "No items found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainPageActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Toast.makeText(this@MainPageActivity, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }



    // Save instance state when activity is paused or destroyed
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("SEARCH_TEXT", searchEditText.text.toString())
        outState.putBoolean("HEART1_CHECKED", heartCheckBox1.isChecked)
        outState.putBoolean("HEART2_CHECKED", heartCheckBox2.isChecked)
    }

    // Restore saved instance state when activity is recreated
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        searchText = savedInstanceState.getString("SEARCH_TEXT")
        isHeart1Checked = savedInstanceState.getBoolean("HEART1_CHECKED")
        isHeart2Checked = savedInstanceState.getBoolean("HEART2_CHECKED")
        searchEditText.setText(searchText)
        heartCheckBox1.isChecked = isHeart1Checked
        heartCheckBox2.isChecked = isHeart2Checked
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
