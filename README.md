# Sonora API

**A music streaming and playback library for Minecraft mods.**

Sonora API is a library mod that provides music streaming, multiplayer synchronization, and distance-based volume control for Minecraft.

It allows mod developers to integrate music playback into their own projects without implementing a complete audio streaming system from scratch.

## Features

- Server-to-client music streaming
- Multiplayer synchronized playback
- Distance-based volume attenuation
- Individual player volume control
- Playback seeking and smooth fading
- Recovery from playback interruptions
- Support for custom music systems in other mods

## Supported Environment

- **Minecraft:** 1.21.1
- **Mod Loader:** NeoForge
- **Sonora API Version:** 1.0.1

Version 1.0.1 accepts MP3 uploads and persistent imports up to 60 minutes inclusive and 144 MiB per track. The world catalog has a 320 MiB budget. Existing track IDs and world files carry over.

## For Developers

Sonora API is designed to be used as a dependency by other Minecraft mods.

[1.0.1 release notes / 日本語の更新履歴](CHANGELOG.md)

The developer guides are included as HTML. Open the English or Japanese file in a browser after downloading this repository. [English guide](docs/index.html) · [日本語ガイド](docs/ja.html)

## Installation

Place the official Sonora JAR on the server and every client. [Sonora API on CurseForge](https://www.curseforge.com/minecraft/mc-mods/sonora-api)

Sonora API is a library mod and does not add music-related gameplay features on its own.

## License

Sonora API is proprietary software. Public API usage is permitted under the project's license terms.

Unauthorized redistribution or modification of Sonora API itself is not permitted.

You may copy, adapt and redistribute the example snippets and GalleryAudio.java in your own mods, including commercial mods. This permission covers only the examples.
