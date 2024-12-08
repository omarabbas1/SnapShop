package com.myproject.project_279


import android.os.Bundle
import android.widget.ImageButton

import androidx.appcompat.app.AppCompatActivity


class OrderDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)


        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {

            finish()
        }

    }
}
