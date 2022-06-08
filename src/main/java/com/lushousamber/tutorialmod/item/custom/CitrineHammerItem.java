package com.lushousamber.tutorialmod.item.custom;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import org.jetbrains.annotations.NotNull;

public class CitrineHammerItem extends Item {
    public CitrineHammerItem(Properties p_41383_) {
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

            // Vec3 looking = player.getForward();
            // Vec3 launching = looking.scale(-5f);
            // player.setDeltaMovement(launching);
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 1, 10));
            //TODO: check if on client side and then launch when baby is hurt -> use code below
        }

        return super.hurtEnemy(p_41395_, p_41396_, p_41397_);
    }

    //@Override
    //public @NotNull InteractionResultHolder<ItemStack> use(Level p_41432_, @NotNull Player p_41433_, @NotNull InteractionHand p_41434_) {
    //    if (p_41432_.isClientSide()) {
    //       Vec3 looking = p_41433_.getForward();
    //       Vec3 launching = looking.scale(-5f);
    //       p_41433_.setDeltaMovement(launching);
    //   }

    //   return super.use(p_41432_, p_41433_, p_41434_);
    //}
}
