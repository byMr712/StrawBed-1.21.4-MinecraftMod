package com.example.strawbed.block;

import com.example.strawbed.registry.ModSounds;
import com.example.strawbed.world.StrawBedTracker;
import com.mojang.datafixers.util.Either;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Unit;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class StrawBedBlock extends BedBlock {
    private static final VoxelShape FOOT_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);
    private static final VoxelShape HEAD_SOUTH = VoxelShapes.union(
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 8.0),
            Block.createCuboidShape(0.0, 0.0, 8.0, 16.0, 5.0, 16.0)
    );
    private static final VoxelShape HEAD_NORTH = VoxelShapes.union(
            Block.createCuboidShape(0.0, 0.0, 8.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 5.0, 8.0)
    );
    private static final VoxelShape HEAD_EAST = VoxelShapes.union(
            Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 4.0, 16.0),
            Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 5.0, 16.0)
    );
    private static final VoxelShape HEAD_WEST = VoxelShapes.union(
            Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 5.0, 16.0)
    );

    public StrawBedBlock(Settings settings) {
        super(DyeColor.YELLOW, settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(PART) == BedPart.FOOT) {
            return FOOT_SHAPE;
        }
        Direction direction = state.get(FACING);
        return switch (direction) {
            case SOUTH -> HEAD_SOUTH;
            case NORTH -> HEAD_NORTH;
            case EAST -> HEAD_EAST;
            case WEST -> HEAD_WEST;
            default -> HEAD_SOUTH;
        };
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        return attemptSleep(state, world, pos, player);
    }

    private ActionResult attemptSleep(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (isClientSide(world)) {
            return ActionResult.CONSUME;
        }

        if (state.get(PART) != BedPart.HEAD) {
            pos = pos.offset(state.get(FACING));
            state = world.getBlockState(pos);
            if (!state.isOf(this)) {
                return ActionResult.CONSUME;
            }
        }

        if (!bedWorks(world, pos)) {
            removeBothHalves(world, pos, state, ModSounds.STRAW_BED_BREAK.get());
            return ActionResult.SUCCESS;
        }

        if (state.get(OCCUPIED)) {
            displayOverlay(player, Text.translatable("block.minecraft.bed.occupied"));
            return ActionResult.SUCCESS;
        }

        if (player instanceof ServerPlayerEntity serverPlayer) {
            StrawBedTracker.beginSleepAttempt(serverPlayer, pos);
            Either<PlayerEntity.SleepFailureReason, Unit> either = serverPlayer.trySleep(pos);
            if (either.left().isPresent()) {
                StrawBedTracker.finishSleepAttempt(serverPlayer, false);
                PlayerEntity.SleepFailureReason reason = either.left().get();
                Text message = sleepingProblemMessage(reason);
                if (message != null) {
                    displayOverlay(player, message);
                }
                return ActionResult.SUCCESS;
            }
            StrawBedTracker.finishSleepAttempt(serverPlayer, true);
        }

        return ActionResult.SUCCESS;
    }

    private static boolean isClientSide(World world) {
        return world.isClient();
    }

    private static boolean bedWorks(World world, BlockPos pos) {
        return BedBlock.isBedWorking(world);
    }

    @Nullable
    private static Text sleepingProblemMessage(PlayerEntity.SleepFailureReason reason) {
        return reason.getMessage();
    }

    private static void displayOverlay(PlayerEntity player, Text message) {
        player.sendMessage(message, true);
    }

    public static void removeBothHalves(World world, BlockPos headPos, BlockState state, SoundEvent sound) {
        BlockPos footPos = headPos.offset(state.get(FACING).getOpposite());
        world.breakBlock(headPos, false);
        BlockState footState = world.getBlockState(footPos);
        if (footState.isOf(state.getBlock()) && footState.contains(PART)) {
            world.breakBlock(footPos, false);
        }
        world.playSound(null, headPos, sound, SoundCategory.BLOCKS, 0.9f, 1.0f);
    }
}
