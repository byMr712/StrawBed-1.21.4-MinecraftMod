> **Language:** Русский · [English](README.en.md)

# StrawBed (Minecraft 1.21.4 Fabric Port)

![Java 21](https://img.shields.io/badge/Java-21-blue.svg)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21.4-blue.svg)
![Fabric](https://img.shields.io/badge/Loader-Fabric-blue.svg)
![ModMenu](https://img.shields.io/badge/ModMenu-Supported-blue.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

Порт и обновление мода **StrawBed BackPort** для **Minecraft 1.21.4 (Fabric)**.

Оригинальный разработчик: [gvoze32](https://github.com/gvoze32/strawbedbackport) 


## О моде

**StrawBed** добавляет в игру простую и дешевую одноразовую кровать из соломы, идеально подходящую для первых ночей выживания или длительных путешествий.

## Галерея

![seed_1771637347649852568_coodinates_-1_75_8](/images/StrawBed.webp)


## Возможности

- **Дешевый крафт**: создается всего из 3 снопов пшеницы и 3 досок.
- **Пропуск ночи без сброса спавна**: соломенная кровать позволяет пропустить ночь, **не перезаписывая точку возрождения (spawn point)** игрока.
- **Одноразовое использование**: после пробуждения кровать разрушается с шуршащим звуком соломы.
- **Статистика**: отслеживание количества использованных соломенных кроватей в окне статистики.


## Что изменено в порте

- Полная миграция на **Minecraft 1.21.4** (Fabric Loom 1.10.1, Yarn `1.21.4+build.7`, Java 21 LTS).
- Адаптация под новые сигнатуры `Block.Settings` (`RegistryKey<Block>`, `RegistryKey<Item>`).
- Регистрация предметов и блоков через современные реестры `Registries.BLOCK` и `Registries.ITEM`.
- Добавление в креативную вкладку `ItemGroups.FUNCTIONAL` через `ItemGroupEvents`.
- Client Item Definitions: добавлен `assets/strawbed/items/straw_bed.json` по стандартам 1.21.4.
- Добавлена полная русская (`ru_ru.json`) и английская (`en_us.json`) локализация.


## Установка

1. Скачайте последнюю версию со страницы [GitHub Releases](https://github.com/byMr712/StrawBed-1.21.4-MinecraftMod/releases).
2. Требуются:
   - [Fabric API](https://modrinth.com/mod/fabric-api)
3. Поместите `.jar` файл в папку `mods`.
4. Запустите игру.


## Сборка

1. Требуется Java 21 и Fabric Loader для Minecraft 1.21.4.
2. Для сборки выполните:
   ```bash
   ./gradlew build
   ```
3. Собранный файл находится в `build/libs/StrawBed-Fabric-1.21.4-byMr712-v1.0.jar`.


## Авторы и лицензия

- Оригинальный автор: [gvoze32](https://github.com/gvoze32).
- Порт и адаптация для 1.21.4: [Mr712](https://github.com/byMr712).
- Распространяется под лицензией [MIT License](LICENSE).
