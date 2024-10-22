package com.example.kotlin_test

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.ComponentActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    private val SPLASH_DELAY = 2000L // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        // Adding animations
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        val slideUp = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)

        // Applying animations to views
        findViewById<TextView>(R.id.appTitle).startAnimation(fadeIn)
        findViewById<TextView>(R.id.appSubtitle).startAnimation(slideUp)

        // Handle splash screen transition
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_DELAY)
    }
}