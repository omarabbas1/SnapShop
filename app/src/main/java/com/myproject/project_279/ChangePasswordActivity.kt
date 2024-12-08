package com.myproject.project_279

import android.content.Intent
import android.os.Bundle
import android.text.InputType

import android.widget.EditText
import android.widget.ImageButton

import androidx.appcompat.app.AppCompatActivity

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var etOldPassword: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var etReEnterNewPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)


        etOldPassword = findViewById(R.id.etOldPassword)
        etNewPassword = findViewById(R.id.etNewPassword)
        etReEnterNewPassword = findViewById(R.id.etReEnterNewPassword)


        setupPasswordToggle(etOldPassword)
        setupPasswordToggle(etNewPassword)
        setupPasswordToggle(etReEnterNewPassword)

        findViewById<ImageButton>(R.id.back_button).setOnClickListener {

            startActivity(Intent(this, ProfilePage::class.java))
        }

        findViewById<ImageButton>(R.id.home_button).setOnClickListener {

            startActivity(Intent(this, MainPageActivity::class.java))
        }

        findViewById<ImageButton>(R.id.favorite_button).setOnClickListener {

            val intent = Intent(this,FavoritesActivity::class.java)
            startActivity(intent)

        }

        findViewById<ImageButton>(R.id.scan_button).setOnClickListener {

            startActivity(Intent(this, ProductPageActivity::class.java))
        }



        findViewById<ImageButton>(R.id.shop_button).setOnClickListener {

            startActivity(Intent(this, CartFragment::class.java))
        }
    }

    private fun setupPasswordToggle(editText: EditText) {
        editText.setOnTouchListener { _, event ->
            if (event.action == android.view.MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                if (event.rawX >= (editText.right - editText.compoundDrawables[drawableEnd].bounds.width())) {
                    togglePasswordVisibility(editText)
                    return@setOnTouchListener true
                }
            }
            false
        }

    }

    private fun togglePasswordVisibility(editText: EditText) {
        if (editText.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0)
        } else {

            editText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0)
        }
        // Move cursor to end
        editText.setSelection(editText.text.length)
    }
}
