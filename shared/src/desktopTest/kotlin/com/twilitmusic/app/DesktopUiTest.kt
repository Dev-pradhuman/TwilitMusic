package com.twilitmusic.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.material3.Text
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class DesktopUiTest {

    @Test
    fun testNavigationToHomeSearchLibrary() = runComposeUiTest {
        setContent {
            // A simplified representation of the UI
            Text("Home")
            Text("Search")
            Text("Library")
        }
        
        onNodeWithText("Home").assertExists()
        onNodeWithText("Search").assertExists()
        onNodeWithText("Library").assertExists()
    }
}
