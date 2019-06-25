/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Stephanie
 */
public class ObsidianRecipes {
    public static void init()
    {
        GameRegistry.addRecipe(new ItemStack(ObsidianItems.Pickaxe), 
                "XXX",
                " Y ",
                " Y ",
                'X', Blocks.obsidian,
                'Y', Items.stick);
        GameRegistry.addRecipe(new ItemStack(ObsidianItems.Axe), 
                "XX ",
                "XY ",
                " Y ",
                'X', Blocks.obsidian,
                'Y', Items.stick);
        GameRegistry.addRecipe(new ItemStack(ObsidianItems.Hoe), 
                "XX ",
                " Y ",
                " Y ",
                'X', Blocks.obsidian,
                'Y', Items.stick);
        GameRegistry.addRecipe(new ItemStack(ObsidianItems.Spade), 
                " X ",
                " Y ",
                " Y ",
                'X', Blocks.obsidian,
                'Y', Items.stick);
        GameRegistry.addRecipe(new ItemStack(ObsidianItems.Sword), 
                "XXX",
                " Y ",
                " Y ",
                'X', Blocks.obsidian,
                'Y', Items.stick);
    }
}
