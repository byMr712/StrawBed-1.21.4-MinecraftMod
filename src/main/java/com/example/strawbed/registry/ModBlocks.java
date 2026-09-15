package com.example.strawbed.registry;

import com.example.strawbed.block.StrawBedBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public final class ModBlocks {
    public static final Supplier<Block> STRAW_BED;

    private static AbstractBlock.Settings createProperties(RegistryKey<Block> key) {
        return AbstractBlock.Settings.create()
                .registryKey(key)
                .mapColor(MapColor.YELLOW)
                .sounds(ModSounds.STRAW_BED_SOUNDS)
                .strength(0.2f)
                .nonOpaque()
                .burnable();
    }

    private static Supplier<Block> register(String name) {
        Identifier id = Identifier.of("strawbed", name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block block = new StrawBedBlock(createProperties(key));
        Block registered = Registry.register(Registries.BLOCK, key, block);
        return () -> registered;
    }

    private ModBlocks() {}

    static {
        STRAW_BED = register("straw_bed");
    }
}
