package com.myapp.screenoffapp

import android.content.Context
import android.os.PowerManager
import android.view.WindowManager
import android.app.Activity

/**
 * Utility class for managing screen state
 */
class ScreenManager(private val context: Context) {
    
    private val powerManager: PowerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    private var wakeLock: PowerManager.WakeLock? = null
    
    /**
     * Turns off the screen using multiple methods for better compatibility
     * @param activity The current activity
     * @return true if successful, false otherwise
     */
    fun turnOffScreen(activity: Activity): Boolean {
        return try {
            // Method 1: Use window flags
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            
            // Method 2: Use wake lock
            createWakeLock()
            releaseWakeLock()
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * Creates a wake lock for screen control
     */
    private fun createWakeLock() {
        wakeLock = powerManager.newWakeLock(
            PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK,
            "ScreenOffApp::ScreenOffWakeLock"
        )
    }
    
    /**
     * Releases the wake lock if it's held
     */
    private fun releaseWakeLock() {
        wakeLock?.let { lock ->
            if (lock.isHeld) {
                lock.release()
            }
        }
        wakeLock = null
    }
    
    /**
     * Checks if the device supports screen control
     * @return true if supported, false otherwise
     */
    fun isScreenControlSupported(): Boolean {
        return try {
            powerManager.isWakeLockLevelSupported(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK)
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Cleanup resources
     */
    fun cleanup() {
        releaseWakeLock()
    }
} 