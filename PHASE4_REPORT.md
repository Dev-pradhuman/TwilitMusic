# Phase 4 Completion Report

## What Changed
- **M1**: Refactored the core architecture into a Kotlin Multiplatform `:shared` module. Transitioned from Hilt/Retrofit/Room to Koin, Ktor, and Room Multiplatform.
- **M2**: Abstracted OS-specific functionality (AudioPlayer, ConnectivityMonitor, Paths) using KMP expect/actual and Interfaces. Android uses ExoPlayer; Desktop utilizes `vlcj`.
- **M3**: Built a complete Compose Desktop application in `:desktopApp`. It supports a responsive UI (switching to a side navigation rail on wide screens), implements keyboard shortcuts (Space for Play/Pause, Ctrl+Left/Right for queue manipulation, Ctrl+F for search), sets minimum window size, and binds the Jamendo API Key dynamically.
- **M4**: Implemented Multiplatform ViewModels and UI automated tests. Integrated `runComposeUiTest` for Desktop logic verification. Headless run wrapper implemented via `xvfb-run` inside the provided smoke testing script.
- **M5**: Configured desktop deployment artifacts (DEB, MSI, DMG) via Compose Desktop packaging. Synchronized all builds via a GitHub Actions pipeline (`desktop.yml`).
- **M6**: Validated that Android `assembleRelease` compiles and shrinks natively with R8 without obfuscation crashes. Bridged an initial `iosApp` Compose UI Controller wrapper for macOS environments.

## Platform Status Table
| Platform | Target Status | Verification State |
| :--- | :--- | :--- |
| **Linux (Ubuntu/Debian)** | Fully Functional | Verified Locally (via `./gradlew :desktopApp:packageDeb` and Smoke Script) |
| **Android** | Fully Functional | Verified Locally (via `:androidApp:assembleRelease` + R8 and `assembleDebug`) |
| **Windows** | Supported via KMP | Verified in CI (GitHub Actions `.msi` build output) |
| **macOS** | Supported via KMP | Verified in CI (GitHub Actions `.dmg` build output) |
| **iOS** | Stubbed UI Wrapper | UNVERIFIED (Awaiting macOS compile host) |

## Known Issues
- `Xvfb` missing from bare local terminals prevents the execution of the Linux Smoke test locally unless dependencies are explicitly installed (`sudo apt install xvfb imagemagick`).
- The `iosApp` has not been compiled or linked through Xcode due to the lack of a macOS host environment, thus iOS audio integration (`AVPlayer`) remains stubbed.
- `MediaControls` (MPRIS on Linux) integration was skipped as `vlcj` provides standalone audio but MPRIS requires an external DBus library wrap which adds unnecessary bloat for Phase 4.

## Phase 5 Proposal (Next Steps)
1. **iOS Native Bring-up**: Transition the `.xcodeproj` to a macOS environment, link the `shared` framework via CocoaPods/SPM, and implement `AVPlayer` native bindings for `AudioPlayer`.
2. **Global Media Keys & MPRIS**: Integrate `java-mpris` or `dbus-java` to connect the Desktop application into the native OS media sessions (Windows System Media Transport Controls, macOS Now Playing, Linux MPRIS).
3. **Advanced Offline & Download Queueing**: Implement a parallel-download queue utilizing Ktor's streaming capabilities mapped against a robust Room DB sync layer for background multi-track downloading.
