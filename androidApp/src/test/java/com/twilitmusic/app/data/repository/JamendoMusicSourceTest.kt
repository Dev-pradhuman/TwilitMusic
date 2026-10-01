package com.twilitmusic.app.data.repository

import com.twilitmusic.app.data.remote.JamendoApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class JamendoMusicSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: JamendoApi
    private lateinit var source: JamendoMusicSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(JamendoApi::class.java)

        source = JamendoMusicSource(api)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `search parses correctly`() = runBlocking {
        val mockResponse = """
            {
              "headers": {
                "status": "success",
                "code": 0,
                "error_message": "",
                "warnings": "",
                "results_count": 1
              },
              "results": [
                {
                  "id": "1",
                  "name": "Test Track",
                  "duration": 120,
                  "artist_id": "2",
                  "artist_name": "Test Artist",
                  "artist_idstr": "testartist",
                  "album_name": "Test Album",
                  "album_id": "3",
                  "license_ccurl": "",
                  "position": 1,
                  "releasedate": "2023-01-01",
                  "album_image": "",
                  "audio": "http://example.com/audio.mp3",
                  "audiodownload": "",
                  "prourl": "",
                  "shorturl": "",
                  "shareurl": "",
                  "waveform": "",
                  "image": "http://example.com/image.jpg",
                  "audiodownload_allowed": true,
                  "musicinfo": {
                    "vocalinstrumental": "vocal",
                    "lang": "en",
                    "gender": "male",
                    "acousticelectric": "acoustic",
                    "speed": "medium"
                  }
                }
              ]
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse).setResponseCode(200))

        val results = source.search("Test").getOrThrow()
        assertEquals(1, results.size)
        assertEquals("Test Track", results[0].title)
        assertEquals("http://example.com/audio.mp3", results[0].sourceUrl)
    }
}
