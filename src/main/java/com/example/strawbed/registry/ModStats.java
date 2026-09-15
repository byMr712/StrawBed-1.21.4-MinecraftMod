package com.example.strawbed.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public final class ModStats {
    public static final Supplier<Identifier> SLEEP_IN_STRAW_BED;

    private static Supplier<Identifier> register() {
        Identifier id = Identifier.of("strawbed", "sleep_in_straw_bed");
        Registry.register(Registries.CUSTOM_STAT, id, id);
        return () -> id;
    }

    public static void award(ServerPlayerEntity player) {
        player.incrementStat(Stats.CUSTOM.getOrCreateStat(SLEEP_IN_STRAW_BED.get(), StatFormatter.DEFAULT));
    }

    private ModStats() {}

    static {
        SLEEP_IN_STRAW_BED = register();
    }
}
