package com.lushousamber.tutorialmod.item.custom;

import com.lushousamber.tutorialmod.item.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;


public class ModArmorItem extends ArmorItem {

    //private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
    //        (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
    //                .put(ModArmorMaterials.CITRINE, new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 1)).build();

    private static final Map<ArmorMaterial, MobEffectInstance[]> MATERIAL_TO_EFFECT_MAP = new HashMap<>();

    public ModArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings);

        MobEffectInstance[] CITRINE_EFFECTS = new MobEffectInstance[2];
        CITRINE_EFFECTS[0] = new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 1);
        CITRINE_EFFECTS[1] = new MobEffectInstance(MobEffects.ABSORPTION, 600, 4);
        MATERIAL_TO_EFFECT_MAP.put(ModArmorMaterials.CITRINE, CITRINE_EFFECTS);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!level.isClientSide()) {
            if (hasFullSuitOfArmor(player)) {
                evaluateArmorEffects(player);
            }
        }
    }

    private boolean hasFullSuitOfArmor(Player player) {
        ItemStack helmet = player.getInventory().getArmor(3);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack boots = player.getInventory().getArmor(0);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance[]> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial armorMaterial = entry.getKey();
            MobEffectInstance[] effects = entry.getValue();

            if (hasCorrectArmor(armorMaterial, player)) {
                addEffectsForMaterial(player, effects);
            }
        }
    }

    private boolean hasCorrectArmor(ArmorMaterial material, Player player) {
        ArmorItem helmet = (ArmorItem) player.getInventory().getArmor(3).getItem();
        ArmorItem chestplate = (ArmorItem) player.getInventory().getArmor(2).getItem();
        ArmorItem leggings = (ArmorItem) player.getInventory().getArmor(1).getItem();
        ArmorItem boots = (ArmorItem) player.getInventory().getArmor(0).getItem();

        return helmet.getMaterial() == material && chestplate.getMaterial() == material &&
            leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    private void addEffectsForMaterial(Player player, MobEffectInstance[] effects) {
        for (MobEffectInstance effect : effects) {
            if (!player.hasEffect(effect.getEffect())) {
                player.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier()));
            }
        }
    }
}
