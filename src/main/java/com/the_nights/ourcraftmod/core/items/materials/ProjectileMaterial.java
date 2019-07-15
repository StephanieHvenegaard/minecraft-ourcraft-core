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
public enum ProjectileMaterial {
            // BLOWGUN("weaponmod:dart", "blowgun", 250, 1),
            // CROSSBOW("weaponmod:bolt", "crossbow", 250, 1),
            FLINT_LOCK_AMMO(MiscItems.flintlockAmmo, 1),
            // BLUNDERBUSS("weaponmod:shot", "blunderbuss", 80, 1),
            Rifle_AMMO(MiscItems.flintlockAmmo, 2);

            ProjectileMaterial(Item ammoitem, int id) {
        this.ammoItem = ammoitem;;
        this.id = id;
    }

    public Item ammoItem;
    public final int id;
}
