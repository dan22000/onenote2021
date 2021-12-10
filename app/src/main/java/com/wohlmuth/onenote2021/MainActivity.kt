package com.wohlmuth.onenote2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set OnClickListener
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener(this)

        // Do not show MainActivity when user is already logged in
        if (Preferences().isUserLoggedIn(this)) {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onClick(view: View?) {
        // Display Toast
        Toast.makeText(this, R.string.logged_in, Toast.LENGTH_LONG).show()

        // Animate icon
        val rotation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        val ivIcon = findViewById<ImageView>(R.id.ivIcon)
        ivIcon.startAnimation(rotation)

        // Open ListActivity
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)

        // Save login state
        Preferences().setUserLoggedIn(this, true)

        // Close MainActivity
        finish()
    }
}