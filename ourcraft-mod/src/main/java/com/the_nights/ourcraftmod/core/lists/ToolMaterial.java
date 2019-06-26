/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.lists;

import com.the_nights.ourcraftmod.core.items.lists.ObsidianItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

/**
 *
 * @author Stephanie
 */
public enum ToolMaterial implements IItemTier
{
    obsidian(3.0f,25.0f,1562*10,3,25,ObsidianItems.Ingot);
    private float dmg;
    private float effiency;
    private int durability;
    private int harvestLVL;
    private int enchantAbility;
    private Item repItem;

    private ToolMaterial(float dmg, float effiency, int durability, int harvestLVL, int enchantAbility, Item repItem) {
        this.dmg = dmg;
        this.effiency = effiency;
        this.durability = durability;
        this.harvestLVL = harvestLVL;
        this.enchantAbility = enchantAbility;
        this.repItem = repItem;
    }

    public float getDmg() {
        return dmg;
    }

    public float getEffiency() {
        return effiency;
    }

    public int getHarvestLVL() {
        return harvestLVL;
    }

    public int getEnchantAbility() {
        return enchantAbility;
    }

    public Ingredient getRepItem() {
        return Ingredient.fromItems(this.repItem);
    }

    @Override
    public int getMaxUses() {return durability; }

    @Override
    public float getEfficiency() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getAttackDamage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHarvestLevel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getEnchantability() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient getRepairMaterial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

