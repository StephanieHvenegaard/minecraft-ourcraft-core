/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraft.core.items.materials;

/**
 *
 * @author Stephanie
 */
public enum RangedMaterial {

    FLINTLOCK_MUSKET(ProjectileMaterial.FLINT_LOCK_MUSKET_AMMO, 1, 5.0f, 2.3f, 80, 720000),
    FLINTLOCK_BLUNDERBUSS(ProjectileMaterial.FLINT_LOCK_BLUNDERBUSS_AMMO, 1, 2.0f, 15.0f, 80, 720000),
    FLINTLOCK_PISTOL(ProjectileMaterial.FLINT_LOCK_PISTOL_AMMO, 1, 1.5f, 3.0f, 80, 2000);

    RangedMaterial(ProjectileMaterial ammo, int magazinCapasity, float projectileVelocity, float spread, int durability, int reloadtime) {
        this.ammoType = ammo;
        this.magazinCapasity = magazinCapasity;
        this.durability = durability;
        this.reloadTime = reloadtime;
        this.projectileVelocity = projectileVelocity;
        this.spread = spread;
    }

    public final ProjectileMaterial ammoType;
    public final int durability, magazinCapasity, reloadTime;
    public final float projectileVelocity, spread;
}