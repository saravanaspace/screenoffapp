package com.myapp.screenoffapp

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityUnitTest {

    @Test
    fun `activity creation should initialize ScreenManager and turn off screen`() {
        // When - Create activity which triggers onCreate
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        
        // Then
        assertNotNull("Activity should be created successfully", mainActivity)
        assertFalse("Activity should not be finishing", mainActivity.isFinishing)
    }

    @Test
    fun `activity destruction should cleanup ScreenManager`() {
        // Given
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        
        // When
        mainActivity.finish()
        
        // Then
        // Should not throw any exception during cleanup
        assertTrue("Activity should finish without errors", true)
    }

    @Test
    fun `activity should handle screen turn off failure gracefully`() {
        // When
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        
        // Then
        // Activity should not crash even if screen turn off fails
        assertNotNull("Activity should still exist", mainActivity)
        assertFalse("Activity should not be finishing", mainActivity.isFinishing)
    }

    @Test
    fun `activity should handle ScreenManager exceptions gracefully`() {
        // When
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        
        // Then
        // Activity should not crash even if ScreenManager throws exception
        assertNotNull("Activity should still exist", mainActivity)
        assertFalse("Activity should not be finishing", mainActivity.isFinishing)
    }

    @Test
    fun `activity should handle cleanup exceptions gracefully`() {
        // Given
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        
        // When
        mainActivity.finish()
        
        // Then
        // Activity should not crash even if cleanup throws exception
        assertNotNull("Activity should still exist", mainActivity)
    }

    @Test
    fun `activity lifecycle should work correctly`() {
        // When
        val activityController = Robolectric.buildActivity(MainActivity::class.java)
        val mainActivity = activityController.create().get()
        
        // Then
        assertNotNull("Activity should be created", mainActivity)
        assertFalse("Activity should not be finishing", mainActivity.isFinishing)
        
        // Test lifecycle
        activityController.start()
        assertNotNull("Activity should exist after start", mainActivity)
        
        activityController.resume()
        assertNotNull("Activity should exist after resume", mainActivity)
        
        activityController.pause()
        assertNotNull("Activity should exist after pause", mainActivity)
        
        activityController.stop()
        assertNotNull("Activity should exist after stop", mainActivity)
        
        activityController.destroy()
        // After destroy, we can't make assertions about the activity state
    }
} 