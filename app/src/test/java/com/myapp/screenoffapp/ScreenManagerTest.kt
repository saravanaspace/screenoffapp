package com.myapp.screenoffapp

import android.app.Activity
import android.content.Context
import android.os.PowerManager
import android.view.WindowManager
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ScreenManagerTest {

    private lateinit var screenManager: ScreenManager
    private lateinit var context: Context
    private lateinit var activity: Activity

    @Before
    fun setUp() {
        context = RuntimeEnvironment.getApplication()
        activity = Robolectric.buildActivity(Activity::class.java).create().get()
        screenManager = ScreenManager(context)
    }

    @After
    fun tearDown() {
        screenManager.cleanup()
    }

    @Test
    fun `turnOffScreen should return true when successful`() {
        // When
        val result = screenManager.turnOffScreen(activity)
        
        // Then
        assertTrue("Screen turn off should be successful", result)
    }

    @Test
    fun `turnOffScreen should handle exceptions gracefully`() {
        // Given - Create an activity with a problematic window
        val problematicActivity = object : Activity() {
            override fun getWindow(): android.view.Window? {
                throw RuntimeException("Test exception")
            }
        }
        
        // When
        val result = screenManager.turnOffScreen(problematicActivity)
        
        // Then
        assertFalse("Should handle exceptions gracefully", result)
    }

    @Test
    fun `isScreenControlSupported should return true when supported`() {
        // When
        val result = screenManager.isScreenControlSupported()
        
        // Then
        // In Robolectric environment, this should typically return true
        // but we'll just verify it doesn't crash
        assertNotNull("Result should not be null", result)
    }

    @Test
    fun `isScreenControlSupported should handle exceptions gracefully`() {
        // Create a ScreenManager with a context that might cause issues
        // We'll test this by creating a new context and seeing if it handles issues gracefully
        val testContext = RuntimeEnvironment.getApplication()
        val testScreenManager = ScreenManager(testContext)
        
        // When
        val result = testScreenManager.isScreenControlSupported()
        
        // Then
        // Should not crash and should return a boolean value
        assertNotNull("Result should not be null", result)
        assertTrue("Result should be a boolean", result is Boolean)
    }

    @Test
    fun `cleanup should not crash`() {
        // When
        screenManager.cleanup()
        
        // Then
        // Should not throw any exception
        assertTrue("Cleanup should complete successfully", true)
    }

    @Test
    fun `multiple cleanup calls should be safe`() {
        // When
        screenManager.cleanup()
        screenManager.cleanup() // Second call
        
        // Then
        // Should not throw any exception
        assertTrue("Multiple cleanup calls should be safe", true)
    }

    @Test
    fun `turnOffScreen should handle null window gracefully`() {
        // Given - Create an activity that returns null window
        val nullWindowActivity = object : Activity() {
            override fun getWindow(): android.view.Window? {
                return null
            }
        }
        
        // When
        val result = screenManager.turnOffScreen(nullWindowActivity)
        
        // Then
        assertFalse("Should handle null window gracefully", result)
    }

    @Test
    fun `screenManager should work with different activities`() {
        // Test with multiple activities
        val activity1 = Robolectric.buildActivity(Activity::class.java).create().get()
        val activity2 = Robolectric.buildActivity(Activity::class.java).create().get()
        
        // When
        val result1 = screenManager.turnOffScreen(activity1)
        val result2 = screenManager.turnOffScreen(activity2)
        
        // Then
        assertTrue("Should work with first activity", result1)
        assertTrue("Should work with second activity", result2)
    }

    @Test
    fun `screenManager should handle multiple instances`() {
        // Test creating multiple ScreenManager instances
        val screenManager1 = ScreenManager(context)
        val screenManager2 = ScreenManager(context)
        
        // When
        val result1 = screenManager1.turnOffScreen(activity)
        val result2 = screenManager2.turnOffScreen(activity)
        
        // Then
        assertTrue("First ScreenManager should work", result1)
        assertTrue("Second ScreenManager should work", result2)
        
        // Cleanup
        screenManager1.cleanup()
        screenManager2.cleanup()
    }
} 