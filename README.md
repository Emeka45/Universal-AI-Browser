# Universal AI Browser

Professional Android browser focused on running ChatGPT with the official Idlen Firefox WebExtension in the same application.

## Current architecture

- GeckoView browser engine (required for embedded WebExtension support).
- Official Idlen Firefox add-on fetched from Mozilla Add-ons at build time and installed as a built-in extension.
- ChatGPT opens directly at launch.
- The app does not request Android audio focus; it is intentionally designed not to interrupt external music playback.
- Target device: Android 12 Go / Redmi A1, while keeping the project release-ready.

## Important

Idlen's official listings document ChatGPT support and a Firefox extension. The app packages that extension rather than recreating its behavior. If the upstream extension changes its manifest or installation requirements, the build task will fail or require an update.

## Roadmap

1. Verify Idlen actually detects ChatGPT sends and displays its own ads inside GeckoView.
2. Preserve login/session state across launches.
3. Add browser controls, downloads, permissions, and crash-safe recovery.
4. Add automated build and release artifacts.
5. Add a settings switch for Idlen and an explicit "Do not interrupt music" policy.
