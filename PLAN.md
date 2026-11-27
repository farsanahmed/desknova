# PLAN.md - DeskNova Project Phases

## Phase 0: Environment, Docs, and Plan
- **Goal**: Set up environment, write project documentation, initialize repo
- **Acceptance Test**: All files written (AGENT.md, SPEC.md, STATUS.md, MEMORY.md, PLAN.md), git repo initialized, toolchain versions documented
- **Phone Test**: Verify ADB/shell commands work, check available tools

## Phase 1: Desktop + Taskbar + Clock + Battery
- **Goal**: Basic desktop environment with taskbar showing clock and battery status
- **Acceptance Test**: Home screen shows desk, taskbar at bottom with time/battery icons
- **Phone Test**: Launch DeskNova, verify taskbar appears, clock updates, battery level shows

## Phase 2: Start Menu with Real Installed Apps
- **Goal**: Start menu displays real installed applications from the system
- **Acceptance Test**: Start menu opens, lists apps with icons and names from package manager
- **Phone Test**: Tap launcher icon, verify app list populates with real apps

## Phase 3: Window Manager (open, close, focus, minimize, maximize, drag, resize)
- **Goal**: Windows can be created, moved, resized, minimized, maximized, closed
- **Acceptance Test**: New windows can be dragged, resized via handles, minimized to taskbar, maximized, closed
- **Phone Test**: Drag window with finger, use resize handles, minimize/maximize buttons work

## Phase 4: Files (browse, folder ops, copy/cut/paste, sort, quick access, multi-select, search, share)
- **Goal**: File manager with full operations
- **Acceptance Test**: Browse directories, create/delete folders, copy/paste files, multi-select, search, share via system intent
- **Phone Test**: Navigate to /sdcard, copy a file, paste elsewhere, search for filename

## Phase 5: Built-in Apps
- **Goal**: Text editor, image viewer, media player working with real data
- **Acceptance Test**: Open/create text file, view image, play local audio file
- **Phone Test**: Create text file, edit it, view a photo from DCIM, play a short audio clip

## Phase 6: Terminal and ADB
- **Goal**: Local terminal with shell commands, basic ADB client functionality
- **Acceptance Test**: Run `ls`, `ps`, `cat` commands; list installed packages
- **Phone Test**: Terminal shows busybox commands, adb devices lists connected state

## Phase 7: Desktop Experience (icons, wallpaper/themes, snapping, notifications, search, keyboard/mouse, recents, session restore)
- **Goal**: Full desktop experience with personalization
- **Acceptance Test**: Change wallpaper, snap window to edge, view notifications, search for apps, switch between recents, restore last session
- **Phone Test**: Long-press desktop to change wallpaper, drag window to screen edge snaps, notification appears and can be tapped

## Phase 8: AI and Extras
- **Goal**: AI assistant, diagnostics, accessibility features
- **Acceptance Test**: AI assistant responds to basic queries, diagnostics shows memory usage, accessibility settings accessible
- **Phone Test**: AI chat interface, diagnostics screen shows battery/ memory info, accessibility switch toggles

## Phase 9: Quality and Release
- **Goal**: Testing, bug fixes, release signing, final polishing
- **Acceptance Test**: Debug APK builds successfully, passes basic tests, signed release ready
- **Phone Test**: Install final APK, run through all features, verify no crashes