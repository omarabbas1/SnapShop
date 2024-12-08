package com.myproject.project_279

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class ProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilepage)


        findViewById<Button>(R.id.editProfile).setOnClickListener {

            val intent = Intent(this, UploadPage::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.changePassword).setOnClickListener {

            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
        findViewById<ImageButton>(R.id.FavoriteButton).setOnClickListener {

            val intent = Intent(this,FavoritesActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeButton).setOnClickListener {

            startActivity(Intent(this, MainPageActivity::class.java))
        }



        findViewById<ImageButton>(R.id.ScanButton).setOnClickListener {
            startActivity(Intent(this, ProductPageActivity::class.java))
        }



        findViewById<ImageButton>(R.id.CartButton).setOnClickListener {

            startActivity(Intent(this, CartFragment::class.java))
        }

    }
}
