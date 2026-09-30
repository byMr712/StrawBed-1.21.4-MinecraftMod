> **Language:** [Русский](README.md) · English

# StrawBed (Minecraft 1.21.4 Fabric Port)

![Java 21](https://img.shields.io/badge/Java-21-blue.svg)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21.4-blue.svg)
![Fabric](https://img.shields.io/badge/Loader-Fabric-blue.svg)
![ModMenu](https://img.shields.io/badge/ModMenu-Supported-blue.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

Port and update of the **StrawBed BackPort** mod for **Minecraft 1.21.4 (Fabric)**.

Original Developer: [gvoze32](https://github.com/gvoze32/strawbedbackport)


## About

**StrawBed** adds a cheap and practical disposable straw bed to Minecraft, perfect for the first few nights of survival or long exploration trips.

## Gallery

![seed_1771637347649852568_coodinates_-1_75_8](/images/StrawBed.webp)


## Features

- **Inexpensive Recipe**: Crafted from 3 wheat and 3 wooden planks.
- **Pass the Night Without Resetting Spawn**: Sleeping in a straw bed passes the night **without overriding your player spawn point**.
- **Single-Use**: Breaks upon waking up with a satisfying rustle sound.
- **Custom Statistics**: Tracks the number of straw beds used in the statistics menu.


## Changes in 1.21.4 Port

- Full migration to **Minecraft 1.21.4** (Fabric Loom 1.10.1, Yarn `1.21.4+build.7`, Java 21 LTS).
- Adapted for updated `Block.Settings` signatures (`RegistryKey<Block>`, `RegistryKey<Item>`).
- Modern registry migration via `Registries.BLOCK` and `Registries.ITEM`.
- Added to `ItemGroups.FUNCTIONAL` creative tab via `ItemGroupEvents`.
- Client Item Definitions: added `assets/strawbed/items/straw_bed.json` for 1.21.4.
- Added full Russian (`ru_ru.json`) and English (`en_us.json`) translations.


## Installation

1. Download the latest release from [GitHub Releases](https://github.com/byMr712/StrawBed-1.21.4-MinecraftMod/releases).
2. Requires:
   - [Fabric API](https://modrinth.com/mod/fabric-api)
3. Place the `.jar` file into your `mods` folder.
4. Launch the game.


## Building

1. Requires Java 21 and Fabric Loader for Minecraft 1.21.4.
2. To build the project, run:
   ```bash
   ./gradlew build
   ```
3. The built jar file will be located at `build/libs/StrawBed-Fabric-1.21.4-byMr712-v1.0.jar`.


## Credits & License

- Original Author: [gvoze32](https://github.com/gvoze32).
- Ported and adapted for 1.21.4 by: [Mr712](https://github.com/byMr712).
- Distributed under the [MIT License](LICENSE).
