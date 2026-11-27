# MEMORY.md - DeskNova Project

## Decisions Made

### Toolchain Versions
- **JDK**: OpenJDK 21.0.13.1 (available via engine extensions)
- **OpenJDK 17**: Also available (17.0.21.1)
- **Node.js**: v24.22.2 (via engine)
- **pnpm**: 12.23.0 (via engine)
- **Git**: 2.56.1 (via engine extensions)
- **Gradle**: 9.7.0 downloaded but functional issues with wrapper
- **Android SDK**: platform-34.zip downloaded but corrupted (contains HTML instead of SDK)

### Project Structure
- Root: /storage/emulated/0/DeskNova/
- Android module: /storage/emulated/0/DeskNova/Deskova/ (subdirectory)
- SDK: /storage/emulated/0/DeskNova/android-sdk/ (partial - platforms dir exists but empty)
- Gradle wrapper: references harness-specific installation

### Known Problems
1. **Android platform 34**: Downloaded file is HTML (Google changed download URL or blocking), need alternative source
2. **Gradle wrapper**: Points to /data/user/0/com.androidharness.app/files/.harness-scratch/gradle-8.10.2/ which is not accessible (Permission denied)
3. **SDK platforms**: Only platform-34.zip attempted, no build-tools yet
4. **API level**: compileSdk 24 used as fallback, minSdk 24 confirmed workable
5. **Device constraints**: Android 9 (API 28) minSdk, 3-6 GB RAM target

### Lessons Learned
- Direct SDK zip downloads from Google may be blocked or redirect; consider mirrors or Termux packages
- Gradle wrapper in Android-on-Android setups often references internal paths; using standalone gradle 9.7.0 bin is more reliable
- Java 21 works but some SSL/TLS issues may occur with remote downloads
- Termux extension catalog (pnpm exec termux-*) may provide easier SDK installation
- Always verify downloaded files (file command, checksums) before assuming they are valid
- bionic libc vs glibc: Android builds need platform 34 jars, not generic linux jars

### Next Steps (to be updated after Phase 0)
- Fix Android platform 34 source
- Resolve Gradle wrapper to use standalone gradle 9.7.0
- Attempt minimal Compose project build
- Report build result and APK status