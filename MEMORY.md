# DeskNova — Memory (decisions, versions, known problems, lessons)

## Versions used
- JDK: 21.0.13.1 (OpenJDK) — JAVA_HOME=/data/data/com.androidharness.app/files/linux/lib/jvm/java-21-openjdk
- Gradle: 8.11.1 (standalone download from services.gradle.org, in HARNESS_SCRATCH)
- AGP: 8.9.0 (pinned via settings.gradle resolutionStrategy)
- Kotlin: 1.9.24 (kotlin-gradle-plugin)
- Compose BOM: 2024.10.00; Compose compiler extension 1.5.14
- compileSdk/targetSdk: 34; minSdk: 24
- package: com.farsan.desknova
- Git: 2.56.1; gh: 2.106.0 (authenticated)
- Android SDK: $HOME/android-sdk (cmdline-tools latest, platforms;android-34, build-tools;35.0.0, platform-tools installed via sdkmanager)

## Build result: GREEN
- app/build/outputs/apk/debug/app-debug.apk (~9.4MB)
- package com.farsan.desknova, versionCode 1, versionName 1.0
- minSdk 24, targetSdk 34, compileSdk 34
- Verified with `aapt2 dump badging` — valid APK

## Environment notes
- Device: Android 9, API 28, ABI aarch64, app UID 10859
- Workspace (/storage/emulated/0/...) is sdcardfs `noexec` — `./gradlew` cannot direct-exec; run via `bash gradlew`
- $HOME = /data/user/0/com.androidharness.app/files/linux/home — binaries execute OK here (proven)
- /data/local/tmp — test binary: Permission denied (not executable)
- free RAM: 5.4Gi total, 1.8Gi available
- HARNESS_SCRATCH=/data/user/0/com.androidharness.app/files/.harness-scratch (holds Gradle distributions)

## What fixed the build (Phase 0.1)
1. Old system `gradle` package broken (missing libnative-platform.so); downloaded standalone Gradle 8.11.1 to HARNESS_SCRATCH
2. Old cmdline-tools incompatible with SDK repo XML v4 ("SDK XML versions up to 3 but ... version 4"); downloaded newest official commandlinetools-linux-latest.zip (Java, ARM-safe) to HARNESS_SCRATCH; extracted to $HOME/android-sdk/cmdline-tools/latest; chmod +x bin/*
3. sdkmanager installed platforms;android-34, build-tools;35.0.0, platform-tools (proper SDK metadata, not hand-assembled)
4. Google's build-tools 35.0.0 are x86_64 — "Exec format error" on aarch64 Android; cannot run natively
5. Found working ARM64 aapt2 at /data/user/0/com.androidharness.app/files/linux/bin/aapt2 (2.20-android-16.0.0_r4, arm64 ELF); d8.jar (Java) and apksigner script work natively
6. Passed -Pandroid.aapt2FromMavenOverride=<arm64-aapt2-path> to gradlew so AGP uses ARM64 aapt2
7. Gradle env: TMPDIR=$HOME/tmp, GRADLE_OPTS="-Djava.io.tmpdir=$HOME/tmp -Xmx1024m", JAVA_HOME=<jdk-21>, run via `bash gradlew`
8. android.useAndroidX=true required in gradle.properties

## Build environment recipe
```
# Rebuild (one command):
#   bash scripts/build.sh
#
# Script sets: JAVA_HOME=jdk-21-openjdk, TMPDIR=$HOME/tmp,
#   GRADLE_OPTS="-Djava.io.tmpdir=$HOME/tmp -Xmx1024m", ANDROID_HOME=$HOME/android-sdk,
#   passes -Pandroid.aapt2FromMavenOverride=/data/user/0/com.androidharness.app/files/linux/bin/aapt2
#
# Files NOT committed (local paths): local.properties (sdk.dir)
# Files committed: gradle.properties, scripts/build.sh, app/ sources, docs
#
# One-time setup already done:
#   - Gradle 8.11.1 downloaded to HARNESS_SCRATCH
#   - commandlinetools-latest installed at $HOME/android-sdk/cmdline-tools/latest
#   - sdkmanager installed platforms;android-34, build-tools;35.0.0, platform-tools
#   - ARM64 aapt2 available at /data/user/0/com.androidharness.app/files/linux/bin/aapt2
```

## Lessons learned
- System `gradle` package unusable (missing libnative-platform.so); use standalone Gradle distribution
- python3 zipfile extraction drops exec bits — `chmod +x` scripts after extracting
- Do not stub individual SDK binaries for AGP; it demands ever more files
- local.properties contains machine-specific paths — gitignored, not committed
- /storage/emulated is noexec; all scripts and native tools must live under $HOME (/data, ext4)
- Google's build-tools are x86_64 only; ARM64 aapt2 must come from another source (existing system binary)

## Decisions
- AGP 8.9.0 + Gradle 8.11.1 (compatible)
- Single `app` module; removed stray `DeskNova/` subproject (pinned AGP 9.3.1, incompatible)
- android.aapt2FromMavenOverride passed via -P in scripts/build.sh (not committed — contains local path)
- No app features in Phase 0/0.1

## Known problems
- gradlew cannot be direct-executed (workspace noexec); must use `bash gradlew` or scripts/build.sh
- aapt2 override path in scripts/build.sh is machine-specific (/data/user/0/.../aapt2)
