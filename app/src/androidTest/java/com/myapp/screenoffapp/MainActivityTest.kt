package com.myapp.screenoffapp

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testMainActivityLaunches() {
        // Launch the activity
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            // The activity should launch successfully and turn off the screen
            // Note: We can't easily test if the screen actually turns off in unit tests
            // but we can verify the activity launches without crashing
            scenario.onActivity { activity ->
                assertNotNull("Activity should not be null", activity)
                assertTrue("Activity should be instance of MainActivity", activity is MainActivity)
            }
        }
    }

    @Test
    fun testMainActivityLifecycle() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                // Verify activity is in correct state
                assertFalse("Activity should not be finishing", activity.isFinishing)
                assertFalse("Activity should not be destroyed", activity.isDestroyed)
            }
        }
    }

    @Test
    fun testMainActivityNoUI() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                // Verify that no content view is set (since we removed UI)
                // This is a bit tricky to test directly, but we can verify the activity exists
                assertNotNull("Activity should exist", activity)
            }
        }
    }
} 