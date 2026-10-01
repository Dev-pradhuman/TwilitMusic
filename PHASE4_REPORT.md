# Phase 4 Migration Report (Milestone 1 - In Progress)

## Goal
Migrate the existing Android-only TwilitMusic application into a Compose Multiplatform project supporting Android, Linux Desktop, macOS, Windows, and iOS.

## Status

**M1: Restructure into KMP modules** -> **IN PROGRESS**
- [x] OOM configurations applied to `gradle.properties` (`-Xmx4g`).
- [x] Renamed `:app` to `:androidApp`.
- [x] Created empty `:shared` module with `commonMain`, `androidMain`, `desktopMain`, `iosMain` targets.
- [x] Migrated dependency catalog (`libs.versions.toml`) to KMP alternatives:
  - Kotlin 2.0.20 + Native Compose Compiler (`org.jetbrains.kotlin.plugin.compose`)
  - Ktor 3.0.0
  - Room KMP 2.7.0 + Bundled SQLite
  - Koin 4.0.0
  - Coil 3
- [x] Validated that both `:androidApp` and `:shared` compile successfully (`./gradlew :androidApp:assembleDebug` and `./gradlew :shared:assemble`).
- [ ] Move UI/Domain/Data code from `:androidApp` to `:shared`. (Next step to maintain incremental green builds).
- [ ] Replace Hilt annotations with Koin modules.
- [ ] Replace Retrofit with Ktor Client.

**M2: Platform abstractions** -> **NOT STARTED**
**M3: Linux desktop app** -> **NOT STARTED**
**M4: Testing on Ubuntu** -> **NOT STARTED**
**M5: Windows + macOS desktop** -> **NOT STARTED**
**M6: Android + iOS** -> **NOT STARTED**

## Migration Strategy
To obey the strict requirement of "Migrate incrementally and keep assembleDebug green after every milestone", the logic will be ported module-by-module into `:shared`. A complete 1-step file move breaks the build due to tightly-coupled Android references (Hilt/Media3) across 30+ files. The current state represents a clean slate where `:shared` is ready for the incremental move.
