/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items.goldendiamond;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
/**
 *
 * @author Stephanie
 */
public class GoldenDiamondRecipes {
    public static void init()
    {
//        GameRegistry.addRecipe(new ItemStack(ObsidianItems.Pickaxe), 
//                "XXX",
//                " Y ",
//                " Y ",
//                'X', Blocks.obsidian,
//                'Y', Items.stick);
//        GameRegistry.addRecipe(new ItemStack(ObsidianItems.Axe), 
//                "XX ",
//                "XY ",
//                " Y ",
//                'X', Blocks.obsidian,
//                'Y', Items.stick);
//        GameRegistry.addRecipe(new ItemStack(ObsidianItems.Hoe), 
//                "XX ",
//                " Y ",
//                " Y ",
//                'X', Blocks.obsidian,
//                'Y', Items.stick);
//        GameRegistry.addRecipe(new ItemStack(ObsidianItems.Spade), 
//                " X ",
//                " Y ",
//                " Y ",
//                'X', Blocks.obsidian,
//                'Y', Items.stick);
//        GameRegistry.addRecipe(new ItemStack(ObsidianItems.Sword), 
//                "XXX",
//                " Y ",
//                " Y ",
//                'X', Blocks.obsidian,
//                'Y', Items.stick);
        
        GameRegistry.addRecipe(new ItemStack(GoldenDiamondItems.Ingot), 
                "   ",
                " YY",
                " YX",
                'X', Items.diamond,
                'Y', Items.gold_ingot);
        GameRegistry.addRecipe(new ItemStack(GoldenDiamondItems.ChestPlate), 
                "Y Y",
                "YYY",
                "YYY",
                'Y', GoldenDiamondItems.Ingot);
        GameRegistry.addRecipe(new ItemStack(GoldenDiamondItems.Leggings), 
                "   ",
                "Y Y",
                "Y Y",
                'Y', GoldenDiamondItems.Ingot);
        GameRegistry.addRecipe(new ItemStack(GoldenDiamondItems.ScullProtector), 
                "YYY",
                "Y Y",
                "   ",
                'Y', GoldenDiamondItems.Ingot);
        GameRegistry.addRecipe(new ItemStack(GoldenDiamondItems.Booties), 
                "   ",
                "Y Y",
                "Y Y",
                'Y', GoldenDiamondItems.Ingot);
    }
    
}
