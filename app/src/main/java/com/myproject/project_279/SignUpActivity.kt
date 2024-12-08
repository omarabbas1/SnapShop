package com.myproject.project_279

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.text.method.LinkMovementMethod

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val email = findViewById<EditText>(R.id.email)
        val phoneNumber = findViewById<EditText>(R.id.phoneNumber)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirmPassword)
        val btnCreateAccount = findViewById<Button>(R.id.btnCreateAccount)
        val signInText = findViewById<TextView>(R.id.signInText)

        btnCreateAccount.setOnClickListener {
            val emailText = email.text.toString()
            val phoneText = phoneNumber.text.toString()
            val passwordText = password.text.toString()
            val confirmPasswordText = confirmPassword.text.toString()

            if (emailText.isNotEmpty() && phoneText.isNotEmpty() && passwordText == confirmPasswordText) {

                Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }


        val fullText = "Already have an Account? Sign in"
        val spannableString = SpannableString(fullText)


        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                startActivity(intent)
            }
        }


        val signInColor = ForegroundColorSpan(Color.rgb(59, 150, 220))


        val startIndex = fullText.indexOf("Sign in")
        val endIndex = startIndex + "Sign in".length

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(signInColor, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        signInText.text = spannableString


        signInText.movementMethod = LinkMovementMethod.getInstance()

        val btnCreateAccounte = findViewById<Button>(R.id.btnCreateAccount)
        btnCreateAccounte.setOnClickListener {

            val intent = Intent(this@SignUpActivity, MainPageActivity::class.java)
            startActivity(intent)
        }
    }
}
