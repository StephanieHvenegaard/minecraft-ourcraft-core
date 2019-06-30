/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.items;

import com.the_nights.ourcraftmod.core.Materials.ToolMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

/**
 *
 * @author Stephanie
 */
public class ItemCustomKatana extends SwordItem {

    public ItemCustomKatana(IItemTier itemTier,  Item.Properties props) {
        super(itemTier, 1, -1.6f, props);
    }


    
    
}
