# DeskNova — Status

## Phases
| # | Phase | Status | Notes |
|---|-------|--------|-------|
| 0 | Environment, docs, plan | done | Repo scaffolded; toolchain verified locally |
| 0.1 | Working local build | done | `bash scripts/build.sh` produces debug APK (~9.4MB) |
| 1 | Desktop + taskbar + clock + battery | todo | |
| 2 | Start menu with real installed apps | todo | |
| 3 | Window manager (open/close/focus/minimize/maximize/drag/resize) | todo | |
| 4 | Files (browse/folder ops/copy-cut-paste/sort/quick access/multi-select/search/properties/share) | todo | |
| 5 | Built-in apps | todo | |
| 6 | Terminal and ADB | todo | |
| 7 | Desktop experience (icons, wallpaper/themes, snapping, notifications, search, keyboard/mouse, recents, session restore) | todo | |
| 8 | AI and extras | todo | |
| 9 | Quality and release | todo | |

## Last build result
**GREEN** — `bash scripts/build.sh` → `BUILD SUCCESSFUL` (35 tasks, 11 executed)
- APK: app/build/outputs/apk/debug/app-debug.apk (~9,429,019 bytes)
- Package: com.farsan.desknova, versionCode 1, versionName 1.0
- minSdk 24, targetSdk 34, compileSdk 34
- Verified: `aapt2 dump badging` confirms valid APK with package com.farsan.desknova

## Build environment (summary)
- JAVA_HOME: /data/data/com.androidharness.app/files/linux/lib/jvm/java-21-openjdk (JDK 21.0.13.1)
- Gradle: 8.11.1 standalone (HARNESS_SCRATCH), run via `bash gradlew` (workspace is noexec)
- AGP: 8.9.0, Kotlin 1.9.24, Compose BOM 2024.10.00
- SDK: $HOME/android-sdk (cmdline-tools latest, platforms;android-34, build-tools;35.0.0, platform-tools)
- aapt2: ARM64 binary at /data/user/0/com.androidharness.app/files/linux/bin/aapt2 (x86_64 Google build-tools won't run on aarch64)
- TMPDIR=$HOME/tmp, GRADLE_OPTS="-Djava.io.tmpdir=$HOME/tmp -Xmx1024m"
- Rebuild: `bash scripts/build.sh` (passes -Pandroid.aapt2FromMavenOverride=<arm64-aapt2>)

## Next step
Start web preview on localhost showing build status (APK size, package name, versions). Wait for user to say "next" before copying APK to Downloads. Then proceed to Phase 1.
