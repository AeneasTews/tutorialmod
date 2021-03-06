package com.lushousamber.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class JumpyBlock extends Block {
    private final float launch_strength;
    private final Vec3 launch_direction;

    public JumpyBlock(Properties p_49795_, float launch_strength, Vec3 launch_direction) {
        super(p_49795_);
        this.launch_strength = launch_strength;
        this.launch_direction = launch_direction;
    }

    @Override
    public void stepOn(Level p_152431_, @NotNull BlockPos p_152432_, @NotNull BlockState p_152433_, @NotNull Entity p_152434_) {
        if (!p_152431_.isClientSide()) {
            p_152434_.moveRelative(launch_strength, launch_direction);
        } else {
            if (p_152434_ instanceof Player player) {
                player.moveRelative(launch_strength, launch_direction);
            }
        }

        super.stepOn(p_152431_, p_152432_, p_152433_, p_152434_);
    }
}
