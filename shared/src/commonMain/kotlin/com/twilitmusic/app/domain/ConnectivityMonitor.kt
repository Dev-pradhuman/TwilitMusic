package com.twilitmusic.app.domain

import kotlinx.coroutines.flow.StateFlow

interface ConnectivityMonitor {
    val isOffline: StateFlow<Boolean>
}
