> **Language:** [Русский](README.md) · English

# Straw Bed 🌾🛏️ (Minecraft 1.21.4 Fabric Port)

Unofficial port and update of **Straw Bed** mod for **Minecraft 1.21.4 (Fabric)** by **byMr712**.

Original author: [gvoze32](https://github.com/gvoze32) ([Modrinth](https://modrinth.com/mod/straw-bed) / [CurseForge](https://www.curseforge.com/minecraft/mc-mods/straw-bed)).

---

## 📖 About

**Straw Bed** adds a simple and cheap single-use bed made from straw (wheat), perfect for early game survival or long explorations.

### Features:
- 🌾 **Cheap Recipe**: Crafted with just 3 hay bales (wheat) and 3 wooden planks.
- 💤 **Skip the Night Without Overwriting Spawn**: The straw bed allows you to sleep through the night **without changing your spawn point**!
- 🍂 **Single-Use**: Upon waking up, the bed breaks and plays a rustling straw sound effect.
- 📊 **Custom Statistics**: Tracks the number of straw beds used in your player statistics.

---

## 🛠️ Changes in 1.21.4 Port (byMr712)

- ⚡ **Minecraft 1.21.4 & Fabric Loader Migration**:
  - Migrated to Fabric Loom 1.10.1, Yarn mappings `1.21.4+build.7`, and Java 21 LTS.
  - Adapted to new `Block.Settings` registry key signatures (`RegistryKey<Block>`, `RegistryKey<Item>`).
  - Unified registration via `Registries.BLOCK` and `Registries.ITEM`.
  - Added to `ItemGroups.FUNCTIONAL` via `ItemGroupEvents`.
- 🎨 **Client Item Definitions**:
  - Added `assets/strawbed/items/straw_bed.json` conforming to Minecraft 1.21.4 format for proper GUI and in-hand rendering.
- 🌐 **Full Bilingual Localization**:
  - Complete English (`en_us.json`) and Russian (`ru_ru.json`) translations for blocks, stats, and sleep notifications.

---

## 📦 Building and Installation

1. Requires **Java 21** and **Fabric Loader** for Minecraft 1.21.4.
2. To build from source:
   ```bash
   ./gradlew build
   ```
   or run `build.bat` on Windows.
3. The compiled jar will be located at `build/libs/StrawBed-1.21.4-byMr712.jar`.

---

## 📄 License

The original mod and this port are licensed under the **MIT License**.
