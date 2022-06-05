package com.lushousamber.tutorialmod.item.custom;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class HammerItem extends Item {
    public HammerItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack p_41395_, LivingEntity p_41396_, @NotNull LivingEntity p_41397_) {
        if (!p_41396_.isBaby()) {
            Player player = (Player) p_41397_;
            p_41396_.hurt(DamageSource.playerAttack(player), 1);
        } else {
            Player player = (Player) p_41397_;

            player.sendMessage(new TranslatableComponent("item.tutorialmod.hammer.no_hitting_babies"), player.getUUID());

            player.drop(p_41395_, false);
            player.getInventory().removeItem(p_41395_);

            Vec3 launching = new Vec3(0, 10, 0);
            player.moveTo(player.position().add(launching));
            //TODO: make this work in a way that i want it to
        }
        return false;
    }
}
