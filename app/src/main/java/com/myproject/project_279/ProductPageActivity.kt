package com.myproject.project_279

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductPageActivity : AppCompatActivity() {

    private lateinit var activeTab: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_page)

        val productContent = findViewById<TextView>(R.id.productContent)
        val quantityText = findViewById<TextView>(R.id.quantityText)
        var quantity = 1
        val descriptionTab = findViewById<Button>(R.id.descriptionTab)
        val ingredientsTab = findViewById<Button>(R.id.ingredientsTab)

        activeTab = descriptionTab
        setTabActive(descriptionTab)

        descriptionTab.setOnClickListener {
            productContent.text = "La French Rose - Moisturizing Hand & Nail Crème is a luxurious blend designed to deeply hydrate and rejuvenate your hands and nails with the enchanting essence of French rose. This lightweight, non-greasy formula absorbs quickly, delivering essential nourishment to dry skin and cuticles while strengthening nails and leaving your hands feeling soft, smooth, and refreshed. Enriched with antioxidants, it helps reduce signs of aging, promoting a youthful glow, and its delicate floral fragrance provides an uplifting, sensory experience with every application. Perfectly sized at 30 mL (1.0 fl. oz), it’s an ideal companion for on-the-go hydration and self-care indulgence."
            setTabActive(descriptionTab)
        }

        ingredientsTab.setOnClickListener {
            productContent.text = "Aqua (Water), Glycerin, Rosa Damascena Flower Extract, Shea Butter, Cetearyl Alcohol, Sweet Almond Oil, Tocopherol (Vitamin E), Panthenol (Provitamin B5), Allantoin, Carbomer, Phenoxyethanol, Fragrance (Parfum), Citric Acid, Sodium Hydroxide, Dimethicone, Stearic Acid, Ethylhexylglycerin, Caprylyl Glycol, Limonene, Linalool, Benzyl Alcohol, Citral, Geraniol, Hexyl Cinnamal, Benzyl Salicylate, CI 77288 (Chromium Oxide Green), CI 15985 (Yellow 6 Lake), CI 45380 (Red 27 Lake)."
            setTabActive(ingredientsTab)
        }

        findViewById<ImageButton>(R.id.decrementButton).setOnClickListener {
            if (quantity > 1) {
                quantity--
                quantityText.text = quantity.toString()
            }
        }
        findViewById<ImageButton>(R.id.incrementButton).setOnClickListener {
            quantity++
            quantityText.text = quantity.toString()
        }


        findViewById<Button>(R.id.addToCartButton).setOnClickListener {
            // Code to add item to cart
        }
    }

    private fun setTabActive(selectedTab: Button) {
        // Reset the background tint of the previous active tab
        activeTab.backgroundTintList = ContextCompat.getColorStateList(this, R.color.light_orange)

        // Set the background tint of the newly selected tab
        selectedTab.backgroundTintList = ContextCompat.getColorStateList(this, R.color.orange)

        // Update the activeTab to the currently selected tab
        activeTab = selectedTab
    }

}