package com.the_nights.ourcraft.core.item.weapons;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class ItemScythe extends BaseMeleeWeapon {
    public ItemScythe(IItemTier itemTier, Item.Properties props) {
        super(itemTier, -2, -3.6f, props);
    }
    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(BlockState blockIn) {
        return blockIn.getBlock() == Blocks.HAY_BLOCK;
    }
}