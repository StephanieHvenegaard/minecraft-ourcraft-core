/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraft.core.items.materials;

import com.the_nights.ourcraft.core.OurcraftCore;
import com.the_nights.ourcraft.core.lists.items.BlueGlassItems;
import com.the_nights.ourcraft.core.lists.items.ObsidianItems;
import com.the_nights.ourcraft.core.lists.items.PinkSapphireItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

/**
 *
 * @author Stephanie
 */
public enum ArmorMaterial implements IArmorMaterial {
    obsidian("obsidian",  200, 25, 10, 12, 9, 7, 3.0f, ObsidianItems.ingot),
    Sapphire("sapphire",  200, 25, 10, 12, 9, 7, 3.0f, PinkSapphireItems.ingot),
    blue_glass("blue_glass",  200, 25, 3, 7, 5, 3, 1.0f, BlueGlassItems.ingot),
    wood("wooden",  2, 2, 1, 1,1, 1, 1.0f, null),
    kelp("kelp", 1, 25,1, 1, 1, 1, 0.0f, Items.DRIED_KELP);

    private static final int[] MAX_DAMAGE = new int[]{13, 15, 16, 11};
    private final String name;
    private final String equipSound;
    private final int durability;
    private final int enchantability;
    private final int[] dmgReduction;
    private final float toughness;
    private final Item repairItem;

    private ArmorMaterial(String name, int durability, int enchantability, int dmgRedux_head,int dmgRedux_chest, int dmgRedux_legs, int dmgRedux_feet, float toughness,Item repItem) {
        this.name = name;
        this.equipSound = "";
        this.durability = durability;
        this.enchantability = enchantability;
        this.dmgReduction = new int[]{dmgRedux_head,dmgRedux_chest,dmgRedux_legs, dmgRedux_feet};
        this.toughness = toughness;
        this.repairItem = repItem;
    }

    @Override
    public int getDurability(EquipmentSlotType slot) {
        return MAX_DAMAGE[slot.getIndex()] * this.durability;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slot) {
        return dmgReduction[slot.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return new SoundEvent(new ResourceLocation(this.equipSound));
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(this.repairItem);
    }

    @Override
    public String getName() {
        return OurcraftCore.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

}
