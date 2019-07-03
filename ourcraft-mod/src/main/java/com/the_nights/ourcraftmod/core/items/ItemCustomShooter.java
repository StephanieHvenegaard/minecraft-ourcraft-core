/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.items;

import java.util.function.Predicate;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
/**
 *
 * @author Stephanie
 */
public class ItemCustomShooter extends ShootableItem{

    Predicate<ItemStack> AMMO;
    Item ammoType;
    boolean loaded = false;
    
    public ItemCustomShooter(Item ammo,Properties props) {
        super(props);
        this.ammoType = ammo;
        
    }    

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return AMMO;
    }
}
