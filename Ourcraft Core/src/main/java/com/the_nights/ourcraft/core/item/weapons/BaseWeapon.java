package com.the_nights.ourcraft.core.item.weapons;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public abstract class BaseWeapon extends SwordItem {
    public BaseWeapon(IItemTier itemTier, int attackDamageIn, float attackSpeedIn, Item.Properties props) {
        super(itemTier, 0, -2.6f, props);
    }
}

