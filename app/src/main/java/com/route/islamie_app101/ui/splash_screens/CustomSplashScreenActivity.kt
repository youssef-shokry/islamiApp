package com.route.islamie_app101.ui.splash_screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.route.islamie_app101.MainActivity
import com.route.islamie_app101.databinding.ActivityCustomSplashScreenBinding
import kotlinx.coroutines.Runnable
import java.util.logging.Handler

@SuppressLint("CustomSplashScreen")
class CustomSplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityCustomSplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityCustomSplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreenIntentActivation()
    }

    private fun splashScreenIntentActivation(){
        android.os.Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        }, 3000)
    }
}