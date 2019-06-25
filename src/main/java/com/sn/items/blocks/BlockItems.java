/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items.blocks;

import com.sn.core.UMCMain;
import com.sn.core.UMCTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Stephanie
 */
public class BlockItems {

    public static Block RDirt;
    public static Block RGraval;
    public static Block RStone;

    public static void init() {
        RDirt = new ReinforcedDirt()
                .setBlockName("reinforced_dirt")
                .setBlockTextureName(UMCMain.MODID + ":reinforced_dirt")
                .setHardness(3.0F)
                .setResistance(5.0F)
                .setCreativeTab(UMCTabs.tabMats);
        RGraval = new ReinforcedGravel()
                .setBlockName("reinforced_gravel")
                .setBlockTextureName(UMCMain.MODID + ":reinforced_gravel")
                .setHardness(3.0F)
                .setResistance(5.0F)
                .setCreativeTab(UMCTabs.tabMats);
        RStone = new ReinforcedStone()
                .setBlockName("reinforced_stone")
                .setBlockTextureName(UMCMain.MODID + ":reinforced_stone")
                .setHardness(3.0F)
                .setResistance(5.0F)
                .setCreativeTab(UMCTabs.tabMats);

        //Register        
        GameRegistry.registerBlock(RGraval, RGraval.getUnlocalizedName());
        GameRegistry.registerBlock(RDirt, RDirt.getUnlocalizedName());
        GameRegistry.registerBlock(RStone, RStone.getUnlocalizedName());

        //Recipes
        GameRegistry.addRecipe(new ItemStack(RDirt, 3),
                "   ",
                " YY",
                " YX",
                'X', Items.iron_ingot,
                'Y', Blocks.dirt);
        GameRegistry.addRecipe(new ItemStack(RGraval, 3),
                "   ",
                " YY",
                " YX",
                'X', Items.iron_ingot,
                'Y', Blocks.gravel);
        GameRegistry.addRecipe(new ItemStack(RStone, 3),
                "   ",
                " YY",
                " YX",
                'X', Items.iron_ingot,
                'Y', Blocks.stone);
        GameRegistry.addRecipe(new ItemStack(Blocks.gravel, 4),
                "   ",
                " YY",
                " YY",
                'Y', Blocks.dirt);
        GameRegistry.addRecipe(new ItemStack(Blocks.dirt, 4),
                "   ",
                " YY",
                " YY",
                'Y', Blocks.gravel);
    }

}
