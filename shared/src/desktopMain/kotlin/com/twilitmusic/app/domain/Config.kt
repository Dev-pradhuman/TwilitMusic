package com.twilitmusic.app.domain

actual object Config {
    actual val jamendoClientId: String
        get() = System.getProperty("JAMENDO_CLIENT_ID") ?: System.getenv("JAMENDO_CLIENT_ID") ?: "655938da"
}
