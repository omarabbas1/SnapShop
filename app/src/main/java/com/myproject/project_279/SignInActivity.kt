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
import android.widget.Button

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        val signUpText = findViewById<TextView>(R.id.signUpText)


        val fullText = "Don’t have an Account? Sign up"


        val spannableString = SpannableString(fullText)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {

                val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(intent)
            }
        }


        val signUpColor = ForegroundColorSpan(Color.RED)


        val startIndex = fullText.indexOf("Sign up") // Start index of "Sign up"
        val endIndex = startIndex + "Sign up".length // End index of "Sign up"

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(signUpColor, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        signUpText.text = spannableString


        signUpText.movementMethod = LinkMovementMethod.getInstance()

        val btnLogIn = findViewById<Button>(R.id.btnLogIn)
        btnLogIn.setOnClickListener {

            val intent = Intent(this@SignInActivity, MainPageActivity::class.java)
            startActivity(intent)
        }
    }
}
