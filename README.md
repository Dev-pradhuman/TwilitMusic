# TwilitMusic (Phase 1)

TwilitMusic is an open-source, modern Android music player featuring a "twilight" theme (deep indigo and amber/coral accents), Material 3 design, and smooth playback built entirely from scratch. 

## Features (Phase 1)
- **Home Screen**: Lists Featured and New tracks from a local Dummy Source.
- **Playback**: Fully functional background playback using Media3 (ExoPlayer and MediaSessionService).
- **Mini Player**: Persistent mini-player with play/pause and track info.
- **Now Playing**: Full-screen player with seek bar, playback controls, and artwork.
- **Queue Management**: Add, view, reorder, and remove tracks in the playback queue.

## Tech Stack
- **Kotlin** & **Jetpack Compose**
- **Media3** (ExoPlayer + MediaSessionService)
- **Hilt** (Dependency Injection)
- **Navigation Compose**
- **Coil** for image loading
- **Coroutines & Flow**
- **Clean Architecture** (UI, Domain, Data)

## Setup
1. Clone the repository: `git clone https://github.com/Dev-pradhuman/TwilitMusic`
2. Open in Android Studio Hedgehog or newer (or run from CLI with JDK 17).
3. Build and run on an emulator or device (API 26+).

## Architecture Overview
- `ui`: Contains Jetpack Compose UI, ViewModels, and theme definitions. Single Activity architecture.
- `domain`: Contains business logic, models (`Track`), and interfaces (`MusicSource`).
- `data`: Contains implementations, such as `DemoMusicSource` serving royalty-free tracks.
- `playback`: Contains `MusicService` for Media3 background playback and `MusicController` connecting Media3 to the ViewModels.

## Open-source Licenses
- AndroidX Core, Lifecycle, Compose, Media3
- Hilt (Dagger)
- Coil
- Kotlinx Coroutines
- Reorderable (org.burnoutcrew)

## Acknowledgements
- Audio tracks in DemoMusicSource are provided by [SoundHelix.com](https://www.soundhelix.com/audio-examples).

## Phase 2
- Implemented working seek bar.
- Added Shuffle and Repeat modes.
- Queue persistence across app restarts.
- Room database added for library features.
- Search and Library screens implemented with real data.

## Phase 4: Cross-Platform (Compose Multiplatform)

TwilitMusic now supports cross-platform execution via Kotlin Multiplatform!

### Platforms Supported
- **Android** (fully functional, uses ExoPlayer)
- **Linux Desktop** (fully functional via Kubuntu/Ubuntu, uses `vlcj` for playback)
- **Windows / macOS Desktop** (Supported via KMP backend)
- **iOS** (Stubbed UI via Compose Multiplatform)

### How to Build & Run
**Android:**
```bash
./gradlew :androidApp:assembleDebug
```

**Desktop (Linux/Windows/macOS):**
*Note: Make sure VLC is installed on the host system to allow `vlcj` to work.*
```bash
# Run locally
./gradlew :desktopApp:run

# Package DEB for Ubuntu/Debian
./gradlew :desktopApp:packageDeb

# Package MSI for Windows
./gradlew :desktopApp:packageMsi

# Package DMG for macOS
./gradlew :desktopApp:packageDmg
```
