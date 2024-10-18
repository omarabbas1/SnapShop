package com.myproject.project_279

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.text.method.LinkMovementMethod

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Find the TextView by its ID
        val signUpText = findViewById<TextView>(R.id.signUpText)

        // Full sentence
        val fullText = "Don’t have an Account? Sign up"

        // Create a SpannableString
        val spannableString = SpannableString(fullText)

        // Clickable span for "Sign up"
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Navigate to SignUpActivity
                val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(intent)
            }
        }

        // Color span for "Sign up"
        val signUpColor = ForegroundColorSpan(Color.RED)

        // Apply clickable and color span only to "Sign up"
        val startIndex = fullText.indexOf("Sign up") // Start index of "Sign up"
        val endIndex = startIndex + "Sign up".length // End index of "Sign up"

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(signUpColor, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set the spannable string to the TextView
        signUpText.text = spannableString

        // Make the TextView clickable for the "Sign up" part
        signUpText.movementMethod = LinkMovementMethod.getInstance()
    }
}
