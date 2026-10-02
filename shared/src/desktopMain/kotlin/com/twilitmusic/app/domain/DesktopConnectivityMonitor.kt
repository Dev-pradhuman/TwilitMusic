package com.twilitmusic.app.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.net.InetSocketAddress
import java.net.Socket

class DesktopConnectivityMonitor : ConnectivityMonitor {
    private val _isOffline = MutableStateFlow(false)
    override val isOffline: StateFlow<Boolean> = _isOffline.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                _isOffline.value = !isReachable()
                delay(5000L) // every 5 seconds
            }
        }
    }

    private fun isReachable(): Boolean {
        return try {
            Socket().use { socket ->
                socket.connect(InetSocketAddress("8.8.8.8", 53), 2000)
                true
            }
        } catch (e: Exception) {
            false
        }
    }
}
