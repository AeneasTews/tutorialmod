package com.lushousamber.tutorialmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier CITRINE = new ForgeTier(3, 1600, 8, 3,
            22, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.CITRINE.get()));
}
