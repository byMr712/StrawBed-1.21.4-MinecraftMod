package com.example.strawbed.mixin;

import com.example.strawbed.world.StrawBedTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
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
        if (StrawBedTracker.shouldCancelSpawnSet((PlayerEntity) (Object) this)) {
            ci.cancel();
        }
    }
}
