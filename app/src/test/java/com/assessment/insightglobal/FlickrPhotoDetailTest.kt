package com.assessment.insightglobal

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import com.assessment.insightglobal.data.model.Media
import com.assessment.insightglobal.data.model.Photo
import com.assessment.insightglobal.presentation.screens.PhotoDetail
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class FlickrPhotoDetailTest {

    // Create a Compose test rule to initialize Compose
    @get:Rule
    val composeTestRule = createComposeRule()

    // Mock NavController
    private val navController = mock<NavController>()

    // Test function to verify the Photo Detail Screen behaviors
    @Test
    fun testDetailScreen() {

        val photo = Photo(
            title = "Oregon Zoo June 2024",
            link = "https://www.flickr.com/photos/djwudi/53816721164/",
            media = Media(imageUrl = "https://live.staticflickr.com/65535/53816721164_8e39fa8ac1_m.jpg"),
            published = "2024-06-26T02:30:10Z",
            description = "test",
            author = "nobody@flickr.com",
            authorId = "35034356271@N01",
            tags = "testTag"
        )

        //Launch the Photo Detail Screen with test data
        composeTestRule.setContent {
            PhotoDetail(photo, navController)
        }

        // Verify the presence of key elements in the DetailScreen
        composeTestRule.onNodeWithText("Photo Detail").assertExists()
        composeTestRule.onNodeWithText(photo.title).assertExists()
        composeTestRule.onNodeWithText("Written by ${photo.author}").assertExists()
        composeTestRule.onNodeWithText("Published on ${photo.published}").assertExists()
    }
}
