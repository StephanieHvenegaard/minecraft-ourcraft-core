/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraft.core.items.parts;

import com.the_nights.ourcraft.core.items.ItemCustomFireArm;
import com.the_nights.ourcraft.core.items.materials.RangedMaterial;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Stephanie
 */
public class FirearmPart {

    private int MagCount;
    private boolean loaded;
    private RangedMaterial specs;

    public FirearmPart(RangedMaterial specs) {
        this.specs = specs;
    }

    public int getDamage() {
        int projectiles = specs.ammoType.projectilesPerBullet;
        int dmg = specs.ammoType.dmg;
        return projectiles * dmg;

    }
    public RangedMaterial getSpecs()
    {
        return this.specs;
    }
    public void setSpecs(RangedMaterial specs)
    {
        this.specs = specs;
    }
}
