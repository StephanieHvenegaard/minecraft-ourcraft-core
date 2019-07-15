/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.items.materials;

import com.the_nights.ourcraftmod.core.lists.items.MiscItems;
import net.minecraft.item.Item;

/**
 *
 * @author Stephanie
 */
public enum RangedMaterial {
    
    FLINTLOCK_MUSKET(ProjectileMaterial.FLINT_LOCK_AMMO, 1, 1, 8.0f, 80, 720000),
    FLINTLOCK_BLUNDERBUSS(ProjectileMaterial.FLINT_LOCK_AMMO, 1, 5, 1.0f, 80, 720000),
    FLINTLOCK_PISTOL(ProjectileMaterial.FLINT_LOCK_AMMO, 1, 1, 0.0f, 80, 2000);

    RangedMaterial(ProjectileMaterial ammo, int magazinCapasity, int projectiles, float projectileVelocity, int durability, int reloadtime) {
        this.ammoType = ammo;
        this.magazinCapasity = magazinCapasity;
        this.durability = durability;
        this.stackSize = 1;
        this.reloadTime = reloadtime;
        this.projectiles = projectiles;
        this.projectileVelocity = projectileVelocity;
    }

    public final ProjectileMaterial ammoType;
    public final int durability, stackSize, magazinCapasity, reloadTime, projectiles;
    public final float projectileVelocity;
}
