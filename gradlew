#!/system/bin/sh
# DeskNova local Gradle entry point.
# Invokes Gradle 8.11.1 (required by AGP 8.9.0) with Android-safe JVM settings.
APP_HOME=$(cd "$(dirname "$0")" && pwd) || exit 1
cd "$APP_HOME"

export JAVA_HOME=/data/data/com.androidharness.app/files/linux/lib/jvm/java-21-openjdk
export PATH="$JAVA_HOME/bin:$PATH"
export GRADLE_USER_HOME="${GRADLE_USER_HOME:-$HOME/.gradle}"
export TMPDIR="${TMPDIR:-$HOME/tmp}"
export GRADLE_OPTS="-Djava.io.tmpdir=$HOME/tmp"
mkdir -p "$HOME/tmp"

exec /data/user/0/com.androidharness.app/files/.harness-scratch/gradle-8.11.1/bin/gradle --no-daemon "$@"
