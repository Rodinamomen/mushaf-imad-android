# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build all modules
./gradlew assembleDebug -x lint

# Build individual modules
./gradlew :mushaf-core:assembleDebug
./gradlew :mushaf-ui:assembleDebug

# Run sample app
./gradlew :sample:installDebug

# Run tests
./gradlew testDebugUnitTest                      # All tests
./gradlew :mushaf-core:testDebugUnitTest         # Core module only
./gradlew :mushaf-ui:testDebugUnitTest           # UI module only

# Build release
./gradlew assembleRelease
```

## Architecture

This is a **Quran reader Android library** with a three-module Clean Architecture design:

```
mushaf-core/     → Headless data layer (NO Compose dependencies)
    ├── domain/  → Public API: models, repository interfaces
    ├── data/    → Implementations: repositories, DAOs, services
    └── di/      → Koin module (CoreModule.kt)

mushaf-ui/       → Jetpack Compose UI (depends on mushaf-core)
    ├── mushaf/  → Main reader: MushafView, MushafViewModel
    ├── player/  → Audio: QuranPlayerView, QuranPlayerViewModel
    ├── search/  → SearchView, SearchViewModel
    └── di/      → Koin ViewModels (UiModule.kt)

sample/          → Demo app showcasing all features
```

**Dependency flow:** `sample → mushaf-ui → mushaf-core`

### Key Patterns

- **Repository Pattern:** Interface in `domain/repository/`, implementation in `data/repository/` with `Default` prefix
- **DI:** Koin (runtime, no code generation) - register singletons in `CoreModule.kt`, ViewModels in `UiModule.kt`
- **Auto-initialization:** ContentProvider (`MushafInitProvider`) starts Koin before `Application.onCreate()` - zero configuration required
- **State Management:** Kotlin Flow throughout, `StateFlow<T>` for UI state
- **Database:** Realm Kotlin with entities in `data/local/entities/`

### Adding a New Feature

1. Domain model → `mushaf-core/domain/models/`
2. Repository interface → `mushaf-core/domain/repository/`
3. Repository implementation → `mushaf-core/data/repository/Default*.kt`
4. Register in Koin → `mushaf-core/di/CoreModule.kt`
5. ViewModel → `mushaf-ui/` with Koin injection
6. Composable → access ViewModel via `koinViewModel()`

## Key Technologies

| Component | Library |
|-----------|---------|
| UI | Jetpack Compose (BOM 2024.12.01) |
| Database | Realm Kotlin 1.16.0 |
| Audio | Media3/ExoPlayer 1.5.0 |
| DI | Koin 3.5.6 |
| Async | Kotlin Coroutines + Flow |

## Important Files

- `gradle/libs.versions.toml` - Centralized dependency versions
- `mushaf-core/di/CoreModule.kt` - All repository/service singletons
- `mushaf-ui/di/UiModule.kt` - All ViewModel definitions
- `mushaf-core/internal/MushafInitProvider.kt` - Auto-initialization
- `mushaf-core/data/audio/AudioPlaybackService.kt` - Background playback (MediaSessionService)

## Notes

- **Min SDK 24**, Target SDK 35, Kotlin 1.9.25, Java 17
- Pre-populated Realm database at `assets/quran.realm` (schema version 24)
- 18 reciters defined in `ReciterService.kt`
- Image assets: `assets/quran-images/[page]/[line].png`
