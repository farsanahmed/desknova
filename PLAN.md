# DeskNova — Plan (numbered phases)

Each phase has ONE feature, an acceptance test, and what I (the user) test on the phone. Work one phase at a time. Build locally with `./gradlew`; preview via localhost before any APK.

## 0. Setup — environment, docs, plan
- Feature: JDK/Gradle/Android SDK configured, AGENT.md/SPEC.md/STATUS.md/MEMORY.md/PLAN.md written, repo pushed to github.com/farsanahmed/desknova
- Acceptance: `git remote -v` confirms the repo; documents exist; toolchain versions recorded
- You test: browse the repo on GitHub; confirm README/docs are present

## 1. Desktop + taskbar + clock + battery
- Feature: Compose desktop surface, taskbar with Start button and system tray; real clock (System.currentTimeMillis) and real battery (BatteryManager)
- Acceptance: app launches on API 24+; taskbar renders; clock updates; battery % reads from BatteryManager (or shows "Unavailable" with reason if permission missing)
- You test: open the app; confirm desktop + taskbar + clock + battery appear with real values

## 2. Start menu with real installed apps
- Feature: Start menu listing real installed apps via PackageManager (queryIntentActivities with MAIN/LAUNCHER)
- Acceptance: menu lists at least one real app with correct label/icon; tapping launches it in a window
- You test: open Start menu; confirm installed apps appear; tap one to launch

## 3. Window manager
- Feature: open/close/focus/minimize/maximize/restore, drag and resize windows; z-order; snap-to-grid; remember bounds
- Acceptance: multiple windows open simultaneously; dragging moves a window; maximize fills; minimize hides; closing removes it
- You test: open two apps; drag/resize/minimize/maximize; close one; confirm focus shifts correctly

## 4. Files
- Feature: browse real storage (Documents/Downloads/internal); create/rename/delete folders; copy/cut/paste; sort; quick access; multi-select; search; properties; share; dual-pane
- Acceptance: real file listing; copy duplicates a file; delete removes it; share invokes a real share sheet; no destructive op without confirmation
- You test: create a folder, copy a file into it, rename it, share it, delete it; confirm each reflects in a real file manager

## 5. Built-in apps
- Feature: Text Editor, Image Viewer, Media Player, ZIP/7z/TAR/RAR, APK Manager, Task Manager, Storage Analyzer, Network Center, Clipboard
- Acceptance: editor saves/reads a real text file; image viewer shows a real image; media player plays a real local file; archive lister shows real entries; APK manager lists real packages; task manager lists real tasks
- You test: edit and save a note; view a photo; play an audio file; list an archive's contents; view installed packages

## 6. Terminal and ADB
- Feature: local terminal (real shell via ProcessBuilder); ADB client where available
- Acceptance: terminal runs a real command (e.g. `ls`); ADB shows devices or "Unavailable" with reason
- You test: type a command in terminal; confirm output; try ADB, confirm devices or "Unavailable"

## 7. Desktop experience
- Feature: desktop icons/wallpaper/themes, window snapping, notifications, global search, keyboard/mouse, recents, session restore
- Acceptance: wallpaper applies from a real image; snapping moves window to edge; notification posts via NotificationManager; search finds a real app
- You test: set wallpaper; snap a window; trigger a notification; use keyboard shortcuts; reboot and confirm session restores

## 8. AI and extras
- Feature: AI assistant, virtual desktops, diagnostics, crash handler, accessibility, performance pass
- Acceptance: assistant returns real answer or "Unavailable" with reason; diagnostics shows real RAM/storage/battery; crash handler catches and reports
- You test: ask the assistant something; open diagnostics; confirm no fake data anywhere

## 9. Quality and release
- Feature: performance pass under 3–6 GB RAM, accessibility audit, debug build only (no APKs on GitHub), release notes
- Acceptance: app starts in reasonable time; memory stays lean; `./gradlew :app:assembleDebug` builds cleanly
- You test: run the app on the phone for extended use; confirm stability
