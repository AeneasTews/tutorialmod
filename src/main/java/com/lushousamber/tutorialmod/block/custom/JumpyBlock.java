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
    public JumpyBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void stepOn(Level p_152431_, @NotNull BlockPos p_152432_, @NotNull BlockState p_152433_, @NotNull Entity p_152434_) {
        if (!p_152431_.isClientSide()) {
            if (!(p_152434_ instanceof Player player)) {
                p_152434_.moveRelative(0.5f, new Vec3(0, 1, 0));
            } else {
                player.moveRelative(0.5f, new Vec3(0, 1, 0));
            }
        }
        super.stepOn(p_152431_, p_152432_, p_152433_, p_152434_);
    }
}
