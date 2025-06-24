package com.myapp.screenoffapp

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class IntegrationTest {

    @Test
    fun testCompleteScreenOffFlow() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                // Verify activity is properly initialized
                assertNotNull("Activity should not be null", activity)
                assertTrue("Activity should be instance of MainActivity", activity is MainActivity)
                
                // Verify activity is in correct state
                assertFalse("Activity should not be finishing", activity.isFinishing)
                assertFalse("Activity should not be destroyed", activity.isDestroyed)
                
                // The screen should be turned off by this point
                // We can't directly test if the screen is off, but we can verify
                // that the activity launched successfully without crashing
            }
        }
    }

    @Test
    fun testActivityLifecycleWithScreenOff() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            // Test that the activity can go through its lifecycle
            scenario.moveToState(androidx.lifecycle.Lifecycle.State.CREATED)
            scenario.moveToState(androidx.lifecycle.Lifecycle.State.STARTED)
            scenario.moveToState(androidx.lifecycle.Lifecycle.State.RESUMED)
            
            scenario.onActivity { activity ->
                assertNotNull("Activity should exist after lifecycle changes", activity)
            }
            
            // Test going back through lifecycle
            scenario.moveToState(androidx.lifecycle.Lifecycle.State.STARTED)
            scenario.moveToState(androidx.lifecycle.Lifecycle.State.CREATED)
            scenario.moveToState(androidx.lifecycle.Lifecycle.State.DESTROYED)
        }
    }

    @Test
    fun testMultipleActivityLaunches() {
        // Test that the app can be launched multiple times
        for (i in 1..3) {
            ActivityScenario.launch(MainActivity::class.java).use { scenario ->
                scenario.onActivity { activity ->
                    assertNotNull("Activity should not be null on launch $i", activity)
                    assertTrue("Activity should be instance of MainActivity on launch $i", activity is MainActivity)
                }
            }
        }
    }
} 