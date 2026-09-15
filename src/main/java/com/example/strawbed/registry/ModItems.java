package com.example.strawbed.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public final class ModItems {
    public static final Supplier<Item> STRAW_BED;

    private static Item.Settings createProperties(RegistryKey<Item> key) {
        return new Item.Settings().registryKey(key).maxCount(16).useBlockPrefixedTranslationKey();
    }

    private static Supplier<Item> register(String name) {
        Identifier id = Identifier.of("strawbed", name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        BlockItem item = new BlockItem(ModBlocks.STRAW_BED.get(), createProperties(key));
        Item registered = Registry.register(Registries.ITEM, key, item);
        return () -> registered;
    }

    private ModItems() {}

    static {
        STRAW_BED = register("straw_bed");
    }
}
