package com.lushousamber.tutorialmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext p_41427_) {
        if (p_41427_.getLevel().isClientSide()) {
            BlockPos positionClicked = p_41427_.getClickedPos();
            Player player = p_41427_.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                Block blockBelow = p_41427_.getLevel().getBlockState(positionClicked.below(i)).getBlock();

                if (isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.below(i), player, blockBelow);
                    foundBlock = true;
                    break;
                }
            }

            if (!foundBlock) {
                player.sendMessage(new TranslatableComponent("item.tutorialmod.dowsing_rod.no_valuables"),
                        player.getUUID());
            }
        }

        p_41427_.getItemInHand().hurtAndBreak(1, p_41427_.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(p_41427_);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendMessage(new TextComponent("Found " + blockBelow.asItem().getRegistryName().toString() +
                " at " + "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), player.getUUID());
    }

    private boolean isValuableBlock(Block block) {
        return block == Blocks.COAL_BLOCK || block == Blocks.COPPER_ORE || block == Blocks.GOLD_ORE ||
                block == Blocks.IRON_ORE || block == Blocks.DIAMOND_ORE;
    }
}
