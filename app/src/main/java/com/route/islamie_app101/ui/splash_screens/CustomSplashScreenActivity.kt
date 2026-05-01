package com.route.islamie_app101.ui.splash_screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.route.islamie_app101.MainActivity
import com.route.islamie_app101.databinding.ActivityCustomSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class CustomSplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomSplashScreenBinding
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityCustomSplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreenIntentActivation()
    }

    private fun splashScreenIntentActivation(){
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        handler.postDelayed(runnable, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}