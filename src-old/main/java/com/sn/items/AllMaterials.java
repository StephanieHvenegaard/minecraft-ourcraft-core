/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/**
 *
 * @author Stephanie
 */
public class AllMaterials {
        public static Item.ToolMaterial MatObsidianTool = 
            EnumHelper.addToolMaterial("Obsidian", 2, 1562*2 , 30.0F, 2.0F, 10);
        public static ItemArmor.ArmorMaterial MatGoldenDiamondArmor = 
            EnumHelper.addArmorMaterial("GoldenDiamondArmor", 1562*2 ,new int []{2,7,5,3}, 20);
        public static Item.ToolMaterial MatGoldenDiamondTool = 
            EnumHelper.addToolMaterial("GoldenDiamondTool", 2, 1562+(2*33) , 18.0F, 4.0F, 20);
}
