/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items.food;

import com.sn.core.UMCMain;
import com.sn.core.UMCTabs;
import com.sn.items.misc.IronCan;
import com.sn.items.misc.MiscItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

/**
 *
 * @author Stephanie
 */
public class FoodItems {
    public static Item rawChickenOnACan; 
    public static Item cookedChickenOnACan;
    @Mod.EventHandler
    public static void init() {
        rawChickenOnACan = new RawChickenOnACan()
                .setPotionEffect(Potion.hunger.id, 30, 0, 0.3F)
                .setUnlocalizedName("chicken_on_a_can_raw")                
                .setTextureName(UMCMain.MODID + ":chicken_on_a_can_raw")
                .setCreativeTab(UMCTabs.tabMats)
                .setMaxStackSize(16);
        
        // registre        
        GameRegistry.registerItem(rawChickenOnACan, rawChickenOnACan.getUnlocalizedName());
      
        // Inits ObsidianRecipes
        GameRegistry.addRecipe(new ItemStack(rawChickenOnACan),
                "   ",
                "  Y",
                "  X",
                'X', MiscItems.IronCan,
                'Y', Items.chicken );
        

                                                       
    }
}
