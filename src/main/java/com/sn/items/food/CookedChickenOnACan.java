/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items.food;

import net.minecraft.item.ItemFood;

/**
 *
 * @author Stephanie
 */
public class CookedChickenOnACan  extends ItemFood{
    
     public CookedChickenOnACan() {
        super(18,   //Hunger
            1.5F,   //saturationModifier
            false); //isWolfFavorit
    }
}
