/* 

MIT License

Copyright (c) 2019 Stephanie Hvenegaard

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */
package com.the_nights.ourcraftmod.core.Materials;

import com.the_nights.ourcraftmod.core.lists.items.ObsidianItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

/**
 *
 * @author Stephanie
 */
public enum ToolMaterial implements IItemTier
{
    Obsidian(3.0f,25.0f,1562*10,3,25,ObsidianItems.ingot);
    
    
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

    @Override
    public int getMaxUses() {
        return durability; }

    @Override
    public float getEfficiency() {return effiency;}

    @Override
    public float getAttackDamage() {return dmg; }

    @Override
    public int getHarvestLevel() {  return harvestLVL;  }

    @Override
    public int getEnchantability() {return enchantAbility; }

    @Override
    public Ingredient getRepairMaterial() {  return Ingredient.fromItems(this.repItem);  }
    
}

