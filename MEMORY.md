# DeskNova — Memory (decisions, versions, known problems, lessons)

## Versions used
- JDK: 21.0.13.1 (OpenJDK) — JAVA_HOME=/data/data/com.androidharness.app/files/linux/lib/jvm/java-21-openjdk
- Gradle: 8.11.1 (downloaded to HARNESS_SCRATCH; system `gradle` package is broken: missing libnative-platform.so)
- AGP: 8.9.0 (pinned via settings.gradle resolutionStrategy; AGP 8.10.0 requires Gradle >= 8.11.1)
- Kotlin: 1.9.24 (kotlin-gradle-plugin)
- Compose BOM: 2024.10.00; Compose compiler extension 1.5.14
- compileSdk/targetSdk: 34; minSdk: 24
- package: com.farsan.desknova
- Git: 2.56.1; gh: 2.106.0 (authenticated)
- Android SDK: $HOME/android-sdk (platforms/android-34 via android_sdkinstall; build-tools via sdkmanager/cmdline-tools)

## Environment notes
- Device: Android 9, API 28, ABI aarch64, app UID 10859
- Workspace filesystem (/storage/emulated/0/...) is mounted `noexec` — `./gradlew` cannot direct-exec; run via `bash gradlew`
- Gradle native-service extraction needs a writable/exec TMPDIR: export TMPDIR=$HOME/tmp, GRADLE_OPTS="-Djava.io.tmpdir=$HOME/tmp", GRADLE_USER_HOME=$HOME/.gradle
- HARNESS_SCRATCH=/data/user/0/com.androidharness.app/files/.harness-scratch (holds downloaded gradle distributions)
- git/gh authenticated; HTTPS remotes auto-rewritten with token; no ssh binary

## Build blocker (open)
`./gradlew :app:assembleDebug` is BLOCKED at AGP configuration:
- `Failed to find target with hash string 'android-34'`
- `Build properties not found for package Android SDK Platform 34`
- `Build-tool 35.0.0 is missing <aapt|aidl|dexdump|zipalign|split-select>`
- sdkmanager lists platforms;android-34, build-tools;34.0.0, build-tools;35.0.0 as installed, but AGP cannot resolve the target
- Root cause: incomplete SDK metadata on this Android 9 device (sdkmanager: "SDK XML versions up to 3 but ... version 4 was encountered")
- Requires a complete/working Android SDK to unblock. Do NOT fake a build.

## Lessons learned
- System `gradle` package is unusable (missing libnative-platform.so); download a standalone Gradle distribution instead
- `python3 zipfile` extraction drops exec bits — `chmod +x` scripts after extracting
- Do not stub individual SDK binaries for AGP; it demands ever more files
- local.properties is machine-specific — gitignored, not committed

## Decisions
- AGP 8.9.0 + Gradle 8.11.1 (AGP 8.10.0 needs Gradle >= 8.11.1; 8.9.0 is the newest that declares compatibility with 8.11.1 per the version-check error)
- Single `app` module; removed stray pre-existing `DeskNova/` subproject (pinned AGP 9.3.1, incompatible with Gradle 8.x)
- No app features in Phase 0
