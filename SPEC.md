Vision:
DeskNova is an Android app that gives the user the real feeling of using a PC on a phone: a desktop with a taskbar, Start menu, movable and resizable windows, a file manager, text editor, terminal, task manager, APK manager and more. It is NOT just a launcher. Everything must be real (real Android APIs, real data). If something is unavailable on the device, show "Unavailable" with a short reason.

Target Device Limits (Android 9, minSdk 24, 3-6 GB RAM):
- No Wireless Debugging (Android 9 does not support it natively)
- No Windows apps without a separate emulator
- Other apps cannot be shown inside our windows
- No direct file system access outside scoped storage
- No SELinux disabling without root
- Activities launched from within windows follow standard Android lifecycle

Modules:
1. Window Manager: open, close, focus, minimize, maximize, drag, resize windows
2. Taskbar: persistent, app icons, clickable, shows running apps
3. Start Menu: app drawer, recent apps, power options
4. Desktop Icons: placeable, clickable, long-press to move
5. Files: browse, folder ops, copy/cut/paste, sort, quick access, multi-select, search, properties/share
6. Text Editor: create, open, edit, save text files
7. Image Viewer: view PNG/JPEG/WebP/GIF, basic zoom/pan
8. Media Player: play/pause/stop, seek, volume control for common audio/video
9. ZIP/7z/TAR/RAR: extract, list contents, simple compress
10. APK Manager: install, uninstall, view permissions, list installed apps
11. Task Manager: show running processes, memory usage, force stop
12. Storage Analyzer: scan storage, show used space, identify large files
13. Network Center: WiFi/Cellular status, data usage per app
14. Clipboard: copy/paste text between apps
15. Local Terminal: shell commands, adb commands
16. ADB Client: install/uninstall APKs, shell commands
17. Settings/Themes/Wallpaper: appearance customization
18. Notifications Center: view and interact with notifications
19. Global Search and Command Palette: search apps, files, settings
20. Keyboard and Mouse Support: trackball, mouse events, keyboard input
21. Recents and Favorites: switch between apps, pin frequently used
22. Session Restore: restore previous session state
23. Dual-Pane Files: two-pane view on large screens
24. Virtual Desktops: multiple desktop spaces, switch between them
25. AI Assistant: help with commands, generate code snippets
26. Diagnostics: performance monitoring, memory usage, battery stats
27. Release Process: build, sign, align, release APK

Extra Features:
- Window snapping: auto-snap to edges/halves/quarters
- First-run tutorial: guided tour on first launch
- External display note: extended display support
- Crash handler: graceful error reporting
- Accessibility: screen reader support, contentDescription support
- Performance pass: memory optimization, ANR prevention

Window Snapping:
- User can drag window to screen edge to auto-snap
- Support: left, right, top, bottom halves
- Optional: quadrant snap (TL, TR, BL, BR)
- Visual feedback during drag

First-Run Tutorial:
- Show on first launch only
- Covers: window dragging, taskbar use, Start menu, file manager basics
- Option to skip and re-enable in settings

External Display Note:
- If device supports video output, note capability
- Show mirroring status or limitations

Crash Handler:
- Catch unhandled exceptions
- Provide report sending option
- Log essential state (not private user data)

Accessibility:
- All controls have contentDescription
- Screen reader compatible
- High contrast theme option
- Font size scaling

Performance:
- Window composition limited to visible windows
- Memory usage monitoring
- Battery optimization whitelist
- ANR prevention for main UI thread