#!/system/bin/sh
# DeskNova build script — Phase 0.1 working local build environment.
# Sets Android-safe JVM/env vars and runs ./gradlew :app:assembleDebug.
# Usage: ./scripts/build.sh [extra gradle args...]
set -eu

APP_HOME=$(cd "$(dirname "$0")/.." && pwd)
cd "$APP_HOME"

# Java 21 (OpenJDK) — required for Gradle 8.11.1 / AGP 8.9.0
export JAVA_HOME=/data/data/com.androidharness.app/files/linux/lib/jvm/java-21-openjdk
export PATH="$JAVA_HOME/bin:$PATH"

# Gradle native-service extraction needs a writable, exec-capable TMPDIR.
# $HOME is on /data (ext4, executable); the workspace is noexec (sdcardfs).
export TMPDIR="${TMPDIR:-$HOME/tmp}"
export GRADLE_USER_HOME="${GRADLE_USER_HOME:-$HOME/.gradle}"
export GRADLE_OPTS="-Djava.io.tmpdir=$HOME/tmp -Xmx1024m"
mkdir -p "$HOME/tmp"

# SDK lives on /data (executable). local.properties (gitignored) sets sdk.dir.
export ANDROID_HOME="${ANDROID_HOME:-$HOME/android-sdk}"

# ARM64 aapt2 override: Google's Maven aapt2 is x86_64 and cannot run on aarch64 Android.
# The working ARM64 aapt2 (2.20-android-16.0.0_r4) lives on /data.
export ANDROID_AAPT2_OVERRIDE="/data/user/0/com.androidharness.app/files/linux/bin/aapt2"

exec bash gradlew :app:assembleDebug --console=plain \
    -Pandroid.aapt2FromMavenOverride="$ANDROID_AAPT2_OVERRIDE" \
    "$@"
