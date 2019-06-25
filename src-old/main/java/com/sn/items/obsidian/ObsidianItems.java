/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items;

import com.sn.core.UMCMain;
import com.sn.core.UMCTabs;
import com.sn.items.obsidian.OPickaxe;
import com.sn.items.obsidian.OAxe;
import com.sn.items.obsidian.OShovel;
import com.sn.items.obsidian.OHoe;
import com.sn.items.obsidian.OSword;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import static com.sn.items.AllMaterials.MatObsidianTool;


/**
 *
 * @author Stephanie
 */
public class ObsidianItems {
    public static Item Pickaxe;
    public static Item Axe;
    public static Item Spade;
    public static Item Hoe;
    public static Item Sword;
    
    @Mod.EventHandler
    public static void init()
    {
        Pickaxe = new OPickaxe(MatObsidianTool)
                .setUnlocalizedName("obsidian_pickaxe")
                .setTextureName(UMCMain.MODID+":obsidian_pickaxe")
                .setCreativeTab(UMCTabs.tabTools);
        Axe = new OAxe(MatObsidianTool)
                .setUnlocalizedName("obsidian_axe")
                .setTextureName(UMCMain.MODID+":obsidian_axe")
                .setCreativeTab(UMCTabs.tabTools);
        Spade = new OShovel(MatObsidianTool)
                .setUnlocalizedName("obsidian_spade")
                .setTextureName(UMCMain.MODID+":obsidian_spade")
                .setCreativeTab(UMCTabs.tabTools);
        Hoe = new OHoe(MatObsidianTool)
                .setUnlocalizedName("obsidian_hoe")
                .setTextureName(UMCMain.MODID+":obsidian_hoe")
                .setCreativeTab(UMCTabs.tabTools);
        Sword = new OSword(MatObsidianTool)
                .setUnlocalizedName("obsidian_sword")
                .setTextureName(UMCMain.MODID+":obsidian_sword")
                .setCreativeTab(UMCTabs.tabCombats); 
        // registre        
        GameRegistry.registerItem(Pickaxe,
                                  Pickaxe.getUnlocalizedName());
        GameRegistry.registerItem(Axe, 
                                  Axe.getUnlocalizedName());
        GameRegistry.registerItem(Spade, 
                                  Spade.getUnlocalizedName());
        GameRegistry.registerItem(Hoe, 
                                  Hoe.getUnlocalizedName());
        GameRegistry.registerItem(Sword, 
                                  Sword.getUnlocalizedName());
        // Inits ObsidianRecipes
        ObsidianRecipes.init();
        
    }
}


