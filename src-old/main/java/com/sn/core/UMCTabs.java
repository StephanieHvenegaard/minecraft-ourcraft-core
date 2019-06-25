/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.core;

import com.sn.items.ObsidianItems;
import com.sn.items.goldendiamond.GoldenDiamondItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;


/**
 *
 * @author Stephanie
 */
public class UMCTabs {
        public static CreativeTabs tabTools = new CreativeTabs("tabTools") {
            @Override
            public Item getTabIconItem() {
                return new ItemStack(ObsidianItems.Pickaxe).getItem();
            }
        }; 
        public static CreativeTabs tabMats = new CreativeTabs("tabMats") {
            @Override
            public Item getTabIconItem() {
                return new ItemStack(GoldenDiamondItems.Ingot).getItem();
            }
        }; 
        public static CreativeTabs tabCombats = new CreativeTabs("tabCombats") {
            @Override
            public Item getTabIconItem() {
                return new ItemStack(ObsidianItems.Sword).getItem();
            }
        }; 
}
