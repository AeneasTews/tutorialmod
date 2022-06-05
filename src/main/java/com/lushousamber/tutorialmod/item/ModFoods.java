package com.lushousamber.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CUCUMBER = (new FoodProperties.Builder()).nutrition(4)
            .saturationMod(0.2F).effect(new MobEffectInstance(MobEffects.LEVITATION, 6000, 1), 0.1f).build();

}
