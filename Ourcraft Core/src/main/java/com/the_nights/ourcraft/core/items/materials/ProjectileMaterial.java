/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraft.core.items.materials;

import com.the_nights.ourcraft.core.lists.items.MiscItems;
import net.minecraft.item.Item;

/**
 *
 * @author Stephanie
 */
public enum ProjectileMaterial {
            // BLOWGUN("weaponmod:dart", "blowgun", 250, 1),
            // CROSSBOW("weaponmod:bolt", "crossbow", 250, 1),
            FLINT_LOCK_MUSKET_AMMO(MiscItems.flintlockAmmo,10,1),
            FLINT_LOCK_BLUNDERBUSS_AMMO(MiscItems.flintlockAmmo,10,5),
            FLINT_LOCK_PISTOL_AMMO(MiscItems.flintlockAmmo,5,1),
            // BLUNDERBUSS("weaponmod:shot", "blunderbuss", 80, 1),
            Rifle_AMMO(MiscItems.flintlockAmmo,10, 1);

            ProjectileMaterial(Item ammoitem,int dmg,int projectilesPerBullet) {
        this.ammoItem = ammoitem;
        this.dmg = dmg;
        //this.id = id;
        this.projectilesPerBullet = projectilesPerBullet;
    }

    public final Item ammoItem;
    public final int projectilesPerBullet,dmg;
}
