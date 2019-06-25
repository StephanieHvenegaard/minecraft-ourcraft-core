/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items.misc;

import com.sn.core.UMCMain;
import com.sn.core.UMCTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Stephanie
 */
public class MiscItems {
    public static Item IronCan; 
    @Mod.EventHandler
    public static void init() {
        IronCan = new IronCan()
                .setUnlocalizedName("iron_can")
                .setTextureName(UMCMain.MODID + ":iron_can")
                .setCreativeTab(UMCTabs.tabMats)
                .setMaxStackSize(64);
        
        // registre        
        GameRegistry.registerItem(IronCan, IronCan.getUnlocalizedName());
      
        // Inits ObsidianRecipes
        GameRegistry.addRecipe(new ItemStack(IronCan, 4),
                "   ",
                "  X",
                "  X",
                'X', Items.iron_ingot);

                                                       
    }
    
}
