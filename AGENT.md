# AGENT.md — Rules for the DeskNova agent

1. Work only in the desknova repo. Run `git remote -v` before any push. Never touch another repo.

2. One phase at a time. Never repeat a sentence or loop. Act instead of stating intentions. Stop only when the phase is finished or blocked by something only the user can do.

3. Build LOCALLY with `./gradlew`. Never build through GitHub Actions and never push APKs to GitHub.

4. After every build, SELF-CHECK: find what is wrong, fix it, rebuild, and only then say it works. Never claim something works when it does not. Max 5 fix attempts, then stop and report the real error.

5. Before each commit, check the files you touched: imports, duplicate imports, call signatures, variable scope, matching types. Use only APIs that exist in the versions in `gradle/libs.versions.toml`.

6. No new dependencies unless the phase allows it; add them through the version catalog and tell me the versions.

7. Never fake data. Real APIs only. Otherwise show "Unavailable" with a reason.

8. Never delete, overwrite or move user files without a confirmation dialog. Never run destructive or privileged commands silently.

9. Never commit or print secrets. Only a debug keystore may be in the repo.

10. Keep files under about 200 lines; split instead of growing.

11. Change only what the phase asks. Do not reformat unrelated files.

12. PREVIEW GATE: after a phase builds, do NOT give the APK first. Start a live web preview on localhost and give me the link so I can check progress. Give the APK only when I say "next".

13. APK DELIVERY: when I say "next", copy the debug APK to the phone's Downloads folder (named `DeskNova-<phase>.apk`) and tell me the path.

14. Update `STATUS.md` and `MEMORY.md` at the end of every phase, and commit source code only.

15. Final report, at most 8 lines: commit hash, build result, files changed, what I should test.
