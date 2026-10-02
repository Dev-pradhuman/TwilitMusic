import re

path = 'README.md'
with open(path, 'r') as f:
    content = f.read()

new_content = """# TwilitMusic

TwilitMusic is a modern, cross-platform music player written in Kotlin, built with Compose Multiplatform.

## Platforms Supported

- **Android** (fully functional)
- **Linux Desktop** (fully functional via Kubuntu/Ubuntu)
- **Windows / macOS Desktop** (Supported via KMP backend)
- **iOS** (Stubbed UI via Compose Multiplatform)

## How to Build & Run

### Android
```bash
./gradlew :androidApp:assembleDebug
```

### Linux / Desktop
Make sure `vlc` is installed (e.g. `sudo apt install vlc`).
```bash
# Run locally
./gradlew :desktopApp:run

# Package Deb for Ubuntu/Debian
./gradlew :desktopApp:packageDeb

# Package MSI for Windows
./gradlew :desktopApp:packageMsi

# Package DMG for macOS
./gradlew :desktopApp:packageDmg
```

## Features
- Search and browse Jamendo music
- Stream and cache tracks (KMP AudioPlayer using ExoPlayer on Android and vlcj on Desktop)
- Mini-player & Now Playing expanded view
- Queue management with reordering
"""

with open(path, 'w') as f:
    f.write(new_content)
