import sys

with open('app/src/test/java/com/twilitmusic/app/playback/MusicControllerTest.kt', 'r') as f:
    content = f.read()

content = content.replace('import org.junit.Test', 'import org.junit.Test\nimport org.junit.Assert.assertEquals\nimport org.junit.Assert.assertTrue\nimport org.junit.Assert.assertFalse')

new_tests = '''
    @Test
    fun testShuffleToggle() {
        assertFalse(controller.shuffleModeEnabled.value)
        controller.toggleShuffle()
        assertTrue(controller.shuffleModeEnabled.value)
    }

    @Test
    fun testRepeatCycle() {
        assertEquals(androidx.media3.common.Player.REPEAT_MODE_OFF, controller.repeatMode.value)
        controller.cycleRepeatMode()
        assertEquals(androidx.media3.common.Player.REPEAT_MODE_ALL, controller.repeatMode.value)
        controller.cycleRepeatMode()
        assertEquals(androidx.media3.common.Player.REPEAT_MODE_ONE, controller.repeatMode.value)
        controller.cycleRepeatMode()
        assertEquals(androidx.media3.common.Player.REPEAT_MODE_OFF, controller.repeatMode.value)
    }
'''

content = content.replace('}', new_tests + '\n}')

with open('app/src/test/java/com/twilitmusic/app/playback/MusicControllerTest.kt', 'w') as f:
    f.write(content)
