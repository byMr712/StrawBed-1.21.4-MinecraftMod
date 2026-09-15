package com.example.strawbed;

import com.example.strawbed.registry.ModBlocks;
import com.example.strawbed.registry.ModItems;
import com.example.strawbed.registry.ModStats;
import com.example.strawbed.world.StrawBedTracker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroups;

public class StrawBedMod implements ModInitializer {
    public static final String MOD_ID = "strawbed";

    @Override
    public void onInitialize() {
        ModBlocks.STRAW_BED.get();
        ModItems.STRAW_BED.get();
        ModStats.SLEEP_IN_STRAW_BED.get();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModItems.STRAW_BED.get());
        });

        EntitySleepEvents.STOP_SLEEPING.register((entity, sleepingPos) -> {
            if (entity instanceof PlayerEntity player) {
                StrawBedTracker.onWake(player);
            }
        });
    }
}
