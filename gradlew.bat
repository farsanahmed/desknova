@rem DeskNova local Gradle entry point (Windows stub; not used on device).
@if "%DEBUG%"=="" @echo off
setlocal
set GRADLE_USER_HOME=%GRADLE_USER_HOME%%USERPROFILE%\.gradle
set TMPDIR=%TMPDIR%%USERPROFILE%\tmp
set GRADLE_OPTS=-Djava.io.tmpdir=%USERPROFILE%\tmp
"%~dp0\gradle-8.10.2\bin\gradle.bat" --no-daemon %*
