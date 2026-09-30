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
