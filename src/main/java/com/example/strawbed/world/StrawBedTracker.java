package com.example.strawbed.world;

import com.example.strawbed.block.StrawBedBlock;
import com.example.strawbed.registry.ModBlocks;
import com.example.strawbed.registry.ModSounds;
import com.example.strawbed.registry.ModStats;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class StrawBedTracker {
    private static final Map<UUID, BlockPos> PENDING_BEDS = new HashMap<>();
    private static final Map<UUID, BlockPos> ACTIVE_BEDS = new HashMap<>();

    public static void beginSleepAttempt(ServerPlayerEntity player, BlockPos pos) {
        PENDING_BEDS.put(player.getUuid(), pos.toImmutable());
    }

    public static void finishSleepAttempt(ServerPlayerEntity player, boolean success) {
        BlockPos pos = PENDING_BEDS.remove(player.getUuid());
        if (success && pos != null) {
            ACTIVE_BEDS.put(player.getUuid(), pos);
        }
    }

    public static boolean shouldCancelSpawnSet(PlayerEntity player) {
        return PENDING_BEDS.containsKey(player.getUuid());
    }

    public static void onWake(PlayerEntity player) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) {
            return;
        }

        UUID uuid = serverPlayer.getUuid();
        BlockPos pos = ACTIVE_BEDS.remove(uuid);
        if (pos == null) {
            return;
        }

        World world = serverPlayer.getWorld();
        BlockState state = world.getBlockState(pos);
        if (!state.isOf(ModBlocks.STRAW_BED.get())) {
            return;
        }

        if (state.get(BedBlock.PART) == BedPart.FOOT) {
            Direction facing = state.get(BedBlock.FACING);
            pos = pos.offset(facing);
            state = world.getBlockState(pos);
        }

        if (state.isOf(ModBlocks.STRAW_BED.get())) {
            StrawBedBlock.removeBothHalves(world, pos, state, ModSounds.STRAW_BED_BREAK_LEAVE.get());
            ModStats.award(serverPlayer);
        }
    }

    private StrawBedTracker() {}
}
