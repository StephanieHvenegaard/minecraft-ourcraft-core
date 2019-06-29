/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.Materials;

import com.the_nights.ourcraftmod.core.Main;
import com.the_nights.ourcraftmod.core.lists.ObsidianItems;
import net.minecraft.client.Minecraft;
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
    obsidian("obsidian", ObsidianItems.ingot, "", 400, 25, new int[]{13, 15, 16, 11}, 0.0f),
    wood("wooden", null, "", 100, 2, new int[]{3, 5, 6, 1}, 1.0f),
    kelp("kelp", Items.DRIED_KELP, "", 20, 25, new int[]{1, 2, 1, 1}, 0.0f);

    private static final int[] MAX_DAMAGE = new int[]{13, 15, 16, 11};
    private String name;
    private String equipSound;
    private int durability;
    private int enchantability;
    private int[] dmgReduction;
    private float toughness;
    private Item repairItem;

    private ArmorMaterial(String name, Item repItem, String equipSound, int durability, int enchantability, int[] dmgReduction, float toughness) {
        this.name = name;
        this.equipSound = equipSound;
        this.durability = durability;
        this.enchantability = enchantability;
        this.dmgReduction = dmgReduction;
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
        return Main.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

}
