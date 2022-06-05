package com.lushousamber.tutorialmod.item.custom;

import com.lushousamber.tutorialmod.util.ModTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i));

                if (isValuableBlock(blockBelow)) {
                    assert player != null;
                    outputValuableCoordinates(positionClicked.below(i), player, blockBelow.getBlock());
                    foundBlock = true;
                    break;
                }
            }

            if (!foundBlock) {
                assert player != null;
                player.sendMessage(new TranslatableComponent("item.tutorialmod.dowsing_rod.no_valuables"),
                        player.getUUID());
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, Objects.requireNonNull(pContext.getPlayer()),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendMessage(new TextComponent("Found " + Objects.requireNonNull(blockBelow.asItem().getRegistryName()) +
                " at " + "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), player.getUUID());
    }

    private boolean isValuableBlock(BlockState block) {
        return block.is(ModTags.Blocks.DOWSING_ROD_VALUABLES);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack p_41421_, @Nullable Level p_41422_, @NotNull List<Component> p_41423_, @NotNull TooltipFlag p_41424_) {
        if (!Screen.hasShiftDown()) {
            p_41423_.add(new TranslatableComponent("tooltip.tutorialmod.dowsing_rod.tooltip.shift"));
        } else {
            p_41423_.add(new TranslatableComponent("tooltip.tutorialmod.dowsing_rod.tooltip"));
        }
    }
}
