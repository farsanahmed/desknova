# DeskNova — Status

## Phases
| # | Phase | Status | Notes |
|---|-------|--------|-------|
| 0 | Environment, docs, plan | done | Repo scaffolded; toolchain verified locally |
| 0.1 | Working local build | done | `bash scripts/build.sh` produces debug APK (~9.4MB) |
| 1 | Desktop + taskbar + clock + battery | done | Build GREEN, APK ~9.5MB |
| 2 | Start menu with real installed apps | todo | |
| 3 | Window manager (open/close/focus/minimize/maximize/drag/resize) | todo | |
| 4 | Files (browse/folder ops/copy-cut-paste/sort/quick access/multi-select/search/properties/share) | todo | |
| 5 | Built-in apps | todo | |
| 6 | Terminal and ADB | todo | |
| 7 | Desktop experience (icons, wallpaper/themes, snapping, notifications, search, keyboard/mouse, recents, session restore) | todo | |
| 8 | AI and extras | todo | |
| 9 | Quality and release | todo | |

## Last build result
**GREEN** — `bash scripts/build.sh` → `BUILD SUCCESSFUL`
- APK: app/build/outputs/apk/debug/app-debug.apk (~9,494,365 bytes)
- Package: com.farsan.desknova, versionCode 1, versionName 1.0
- minSdk 24, targetSdk 34, compileSdk 34
- Verified: `aapt2 dump badging` confirms valid APK with package com.farsan.desknova and ACCESS_NETWORK_STATE permission

## Phase 1 implemented (no app features, desktop shell only)
- Full-screen immersive desktop (landscape + portrait, configChanges handles rotation)
- FLAG_KEEP_SCREEN_ON keeps screen on while app is in foreground
- Wallpaper: Compose Brush.verticalGradient behind everything
- Taskbar: 48dp high, semi-transparent dark (0xDD222222), docked at bottom
- Start button: original four-square icon drawn in Canvas, opens Start panel placeholder
- Center: empty reserved area for future open-window buttons
- System tray: Wi-Fi (ConnectivityManager), battery (ACTION_BATTERY_CHANGED BroadcastReceiver), clock (java.time)
- Clock: real device time, updates every second via LaunchedEffect (stops when composable leaves composition/background), respects 12/24-hour setting, tap opens popup with full date/time
- Battery: percentage + charging state from BroadcastReceiver (register on start, unregister on stop), drawn battery icon with fill level and charging mark
- Wi-Fi: connected/not from ConnectivityManager, ACCESS_NETWORK_STATE permission in manifest; "Unavailable" if unknown
- Start panel: header "DeskNova" + "Apps arrive in the next phase", tap outside closes
- Files: DesktopScreen, Taskbar, StartPanel, TrayIcons, Clock, BatteryState, NetworkState, DesktopClockController — each under ~200 lines
- Accessibility: contentDescription on every icon-only button, IconButton touch targets ≥48dp
- No new dependencies beyond existing Compose/Material3
- local.properties (sdk.dir) is gitignored, not committed

## Build environment
- JAVA_HOME: JDK 21.0.13.1; Gradle 8.11.1; AGP 8.9.0; Kotlin 1.9.24; Compose BOM 2024.10.00
- SDK: $HOME/android-sdk; ARM64 aapt2 at /data/user/0/com.androidharness.app/files/linux/bin/aapt2
- Rebuild: `bash scripts/build.sh` (passes -Pandroid.aapt2FromMavenOverride=<arm64-aapt2>)
- TMPDIR=$HOME/tmp, GRADLE_OPTS="-Djava.io.tmpdir=$HOME/tmp -Xmx1024m"

## Next step
Preview gate: web preview served at http://localhost:8080/.build-preview.html. Wait for user to say "next" before copying APK to Downloads/DeskNova-phase1.apk. Then proceed to Phase 2.
