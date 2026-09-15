package com.example.strawbed.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public final class ModSounds {
    public static final Supplier<SoundEvent> STRAW_BED_BREAK;
    public static final Supplier<SoundEvent> STRAW_BED_BREAK_LEAVE;
    public static final Supplier<SoundEvent> STRAW_BED_STEP;
    public static final Supplier<SoundEvent> STRAW_BED_PLACE;
    public static final Supplier<SoundEvent> STRAW_BED_HIT;
    public static final Supplier<SoundEvent> STRAW_BED_FALL;

    public static final BlockSoundGroup STRAW_BED_SOUNDS;

    private static Supplier<SoundEvent> register(String name) {
        Identifier id = Identifier.of("strawbed", name);
        SoundEvent soundEvent = SoundEvent.of(id);
        SoundEvent registered = Registry.register(Registries.SOUND_EVENT, id, soundEvent);
        return () -> registered;
    }

    private ModSounds() {}

    static {
        STRAW_BED_BREAK = register("block.straw_bed.break");
        STRAW_BED_BREAK_LEAVE = register("block.straw_bed.break_leave");
        STRAW_BED_STEP = register("block.straw_bed.step");
        STRAW_BED_PLACE = register("block.straw_bed.place");
        STRAW_BED_HIT = register("block.straw_bed.hit");
        STRAW_BED_FALL = register("block.straw_bed.fall");

        STRAW_BED_SOUNDS = new BlockSoundGroup(
                1.0f,
                1.0f,
                STRAW_BED_BREAK.get(),
                STRAW_BED_STEP.get(),
                STRAW_BED_PLACE.get(),
                STRAW_BED_HIT.get(),
                STRAW_BED_FALL.get()
        );
    }
}
