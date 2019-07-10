/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.materials;

import com.the_nights.ourcraftmod.core.lists.items.MiscItems;
import net.minecraft.item.Item;

/**
 *
 * @author Stephanie
 */
public enum RangedMaterial {
    //BLOWGUN("weaponmod:dart", "blowgun", 250, 1),
    //CROSSBOW("weaponmod:bolt", "crossbow", 250, 1),
    FLINTLOCK_MUSKET(ProjectileMaterial.FLINT_LOCK_AMMO, 1, 80, 720000),
    //BLUNDERBUSS("weaponmod:shot", "blunderbuss", 80, 1),
    FLINTLOCK_PISTOL(ProjectileMaterial.FLINT_LOCK_AMMO, 1, 80, 2000);

    RangedMaterial(ProjectileMaterial ammo, int magazinCapasity, int durability,int reloadtime) {
       this.ammoType = ammo;
        this.magazinCapasity = magazinCapasity;
        this.durability = durability;
        this.stackSize = 1;
        this.reloadTime = reloadtime;
    }

    public ProjectileMaterial ammoType;
    public final int durability, stackSize,magazinCapasity,reloadTime;
}
