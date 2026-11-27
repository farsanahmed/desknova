# DeskNova — Product Specification

## Vision
DeskNova gives the user the real feeling of using a PC on a phone: a desktop with a taskbar, Start menu, movable/resizable windows, a file manager, text editor, terminal, task manager, APK manager, and more. It is NOT just a launcher. Everything must be real — real Android APIs, real data. If something is unavailable on the device, show "Unavailable" with a short reason. Never fake data.

## Target
- Language: Kotlin, UI: Jetpack Compose
- minSdk 24, compileSdk/targetSdk 34, works on Android 9 (API 28)
- Phone RAM: 3–6 GB (keep the process lean; avoid heavy in-memory caches)
- Android 9 limitations: no Wireless Debugging, no running Windows apps without a separate emulator, other apps cannot be displayed inside our windows (Android does not embed arbitrary app UIs)

## Modules

### 1. Window Manager
Open, close, focus, minimize, maximize, restore, drag, resize windows. Each window hosts a real Compose/Android view. Window stack with z-order, snap-to-grid/snapping edges, remember bounds per session.

### 2. Taskbar
Start button, pinned/running app icons, system tray (clock, battery, network, notifications), show desktop, task switching.

### 3. Start Menu
Real installed apps (query PackageManager), grouped/pinned, search within menu, launch into a window.

### 4. Desktop Icons
Wallpaper, icon grid, add/remove shortcuts (apps, folders, files), open on tap/double-tap.

### 5. Files
Browse real storage (Documents, Downloads, internal), folder ops (create/rename/delete), copy/cut/paste, sort (name/date/size/type), quick access, multi-select, search, properties, share, dual-pane mode, confirm before any destructive file op.

### 6. Built-in Apps
- Text Editor: real create/read/write/save of text files
- Image Viewer: view real images
- Media Player: play real local media (MediaPlayer/ExoPlayer)
- ZIP/7z/TAR/RAR: list and extract real archives (no fake contents)
- APK Manager: list installed packages, uninstall (with confirmation), install local APKs, show details
- Task Manager: real running processes/tasks, kill with confirmation
- Storage Analyzer: real disk usage via StorageManager/File paths
- Network Center: real Wi-Fi/cellular status via ConnectivityManager (read-only where permission-limited)
- Clipboard: real system clipboard read/write
- Local Terminal: real shell (command execution via ProcessBuilder/APK shell where available)
- ADB client: adb over TCP/USB where available, else "Unavailable"

### 7. Settings / Themes / Wallpaper
Theme switching, wallpaper picker (real image file), font/scale, language.

### 8. Notifications Center
Real posted notifications via NotificationManager, dismiss, open.

### 9. Global Search & Command Palette
Search apps, files, settings; run commands (open app, open file, settings).

### 10. Keyboard & Mouse Support
Keyboard shortcuts, mouse hover/click, external keyboard.

### 11. Recents & Favorites
Recent apps/files, pinned favorites.

### 12. Session Restore
Save/restore open windows and bounds across process death/reboot.

### 13. Virtual Desktops
Multiple named desktops, switch between them.

### 14. AI Assistant
Optional local/remote assistant. If unavailable, show "Unavailable" with reason. Never fake responses.

### 15. Diagnostics
Device info, RAM, storage, battery health, build version, logs.

### 16. Crash Handler
Catch uncaught exceptions, show a real error dialog, offer to save logs; never crash silently.

### 17. Release Process
Debug builds only locally; APKs never pushed to GitHub; release via sideload only.

## Extra Features (excellence)
- Window snapping (drag to edge/corner)
- First-run tutorial
- External display note (document behavior on second display)
- Accessibility (content descriptions, talkback-friendly)
- Performance pass (startup time, jank, memory under 3–6 GB RAM)

## Non-Goals for Phase 0
No app features. Only environment setup and project documents (AGENT.md, SPEC.md, STATUS.md, MEMORY.md, PLAN.md).
