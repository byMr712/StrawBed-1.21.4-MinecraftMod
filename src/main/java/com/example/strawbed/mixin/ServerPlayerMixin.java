package com.example.strawbed.mixin;

import com.example.strawbed.block.StrawBedBlock;
import com.example.strawbed.world.StrawBedTracker;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerMixin {
    @Inject(
            method = "setSpawnPoint",
            at = @At("HEAD"),
            cancellable = true
    )
    private void strawbed$preserveSpawn(RegistryKey<World> dimension, @Nullable BlockPos pos, float angle, boolean forced, boolean sendMessage, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        if (StrawBedTracker.shouldCancelSpawnSet(player)) {
            ci.cancel();
            return;
        }
        if (pos != null) {
            ServerWorld world = player.getServerWorld();
            if (world != null && world.getRegistryKey().equals(dimension)) {
                if (world.getBlockState(pos).getBlock() instanceof StrawBedBlock) {
                    ci.cancel();
                }
            }
        }
    }
}
