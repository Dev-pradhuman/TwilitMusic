package com.twilitmusic.app.domain

actual object Config {
    // For Android, we could inject BuildConfig from the androidApp module, 
    // but a quick workaround is a mutable property set from Application.onCreate
    var androidClientId: String = "655938da"
    actual val jamendoClientId: String get() = androidClientId
}
