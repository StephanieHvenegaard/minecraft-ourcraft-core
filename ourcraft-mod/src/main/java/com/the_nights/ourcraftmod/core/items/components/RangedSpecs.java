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
    MUSKET(MiscItems.musketShot, 1, 80, 1),
    //BLUNDERBUSS("weaponmod:shot", "blunderbuss", 80, 1),
    FLINTLOCK(MiscItems.musketShot, 1, 80, 1);

    RangedSpecs(Item ammoitem,int magazinCapasity, int durability,int reloadtime) {
        this.ammoItem = ammoitem;
        this.magazinCapasity = magazinCapasity;
        this.durability = durability;
        this.stackSize = 1;
        this.reloadTime = reloadtime;
    }

    public Item ammoItem;
    public final int durability, stackSize,magazinCapasity,reloadTime;
}
