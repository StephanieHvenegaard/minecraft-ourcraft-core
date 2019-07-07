/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.items.components;

import com.the_nights.ourcraftmod.core.lists.items.MiscItems;
import net.minecraft.item.Item;

/**
 *
 * @author Stephanie
 */
public enum RangedSpecs {
    //BLOWGUN("weaponmod:dart", "blowgun", 250, 1),
    //CROSSBOW("weaponmod:bolt", "crossbow", 250, 1),
    MUSKET(MiscItems.musketShot, "musket", 80, 1),
    //BLUNDERBUSS("weaponmod:shot", "blunderbuss", 80, 1),
    FLINTLOCK(MiscItems.musketShot, "flintlock", 80, 1);

    RangedSpecs(Item ammoitem, String reloadtimetag, int durability, int stacksize) {
        this.ammoItem = ammoitem;
        this.reloadTimeTag = reloadtimetag;
        this.durability = durability;
        this.stackSize = stacksize;
        this.reloadTime = -1;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public Item getAmmoItem() {
        return ammoItem;
    }

    private int reloadTime;
    private Item ammoItem;
    public final String reloadTimeTag;
    public final int durability, stackSize;
}
