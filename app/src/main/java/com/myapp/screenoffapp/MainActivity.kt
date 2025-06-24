package com.myapp.screenoffapp

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var screenManager: ScreenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize ScreenManager
        screenManager = ScreenManager(this)
        
        // Turn off the screen immediately
        screenManager.turnOffScreen(this)
        
        // No UI needed since screen will be off
        // The activity will remain active but invisible
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cleanup resources
        screenManager.cleanup()
    }
}