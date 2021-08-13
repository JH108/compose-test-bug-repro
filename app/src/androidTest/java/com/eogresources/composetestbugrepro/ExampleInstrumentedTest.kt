package com.eogresources.composetestbugrepro

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Test
import org.junit.Rule

class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun setValueWithDropdown() {
        composeTestRule.setContent {
            SimpleForm()
        }
        val wellName = composeTestRule.onNodeWithText("Input")

        wellName.performClick()

        composeTestRule.onNodeWithText("First").assertIsDisplayed()

        // Hangs after item is chosen (Still hangs if using the latest version of compose)
        // Logcat shows "These resources are not idle: [Compose-Espresso link]"
        composeTestRule.onNodeWithText("First").performClick()

        composeTestRule.onNodeWithText("First").assertIsDisplayed()
    }

    @Test
    fun setTextValue() {
        composeTestRule.setContent {
            SimpleForm()
        }
        composeTestRule.mainClock.autoAdvance = false
        val ticketNumber = composeTestRule.onNodeWithText("Label")

        ticketNumber.assertExists()
        ticketNumber.assertIsDisplayed()

        // Hangs after text is set (Doesn't hang if using the latest version of compose)
        // Logcat shows "These resources are not idle: [Compose-Espresso link]"
        ticketNumber.performTextInput("some message")
    }
}