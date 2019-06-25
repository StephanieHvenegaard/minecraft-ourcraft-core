/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items.goldendiamond;
import com.sn.core.UMCMain;
import com.sn.core.UMCTabs;
import com.sn.items.AllMaterials;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
/**
 *
 * @author Stephanie
 */
public class GoldenDiamondItems {
    public static String matType = "goldendiamond";
    public static Item Pickaxe;
    public static Item Axe;
    public static Item Spade;
    public static Item Hoe;
    public static Item Sword; 
    
    public static Item ChestPlate;
    public static Item Booties;
    public static Item Leggings;
    public static Item ScullProtector;
    public static Item Ingot;
    
    @Mod.EventHandler
    public static void init()
    {   
        // TOOLS
        Pickaxe = new DGPickaxe(AllMaterials.MatGoldenDiamondTool)
                .setUnlocalizedName(matType+"_pickaxe")
                .setTextureName(UMCMain.MODID+":"+matType+"_pickaxe")
                .setCreativeTab(UMCTabs.tabCombats);
        Axe = new DGAxe(AllMaterials.MatGoldenDiamondTool)
                .setUnlocalizedName(matType+"_axe")
                .setTextureName(UMCMain.MODID+":"+matType+"_axe")
                .setCreativeTab(UMCTabs.tabCombats);
        Spade = new DGShovel(AllMaterials.MatGoldenDiamondTool)
                .setUnlocalizedName(matType+"_spade")
                .setTextureName(UMCMain.MODID+":"+matType+"_spade")
                .setCreativeTab(UMCTabs.tabCombats);
        Hoe = new GDHoe(AllMaterials.MatGoldenDiamondTool)
                .setUnlocalizedName(matType+"_hoe")
                .setTextureName(UMCMain.MODID+":"+matType+"_hoe")
                .setCreativeTab(UMCTabs.tabCombats);
        Sword = new GDSword(AllMaterials.MatGoldenDiamondTool)
                .setUnlocalizedName(matType+"_sword")
                .setTextureName(UMCMain.MODID+":"+matType+"_sword")
                .setCreativeTab(UMCTabs.tabCombats); 
        
        // Amor
        ScullProtector = new GDArmor(AllMaterials.MatGoldenDiamondArmor,0,0)
                .setUnlocalizedName(matType+"_scullprotector")
                .setTextureName(UMCMain.MODID+":"+matType+"_scullprotector")
                .setCreativeTab(UMCTabs.tabCombats);
        ChestPlate = new GDArmor(AllMaterials.MatGoldenDiamondArmor,0,1)
                .setUnlocalizedName(matType+"_chestplate")
                .setTextureName(UMCMain.MODID+":"+matType+"_chestplate")
                .setCreativeTab(UMCTabs.tabCombats);
        Leggings = new GDArmor(AllMaterials.MatGoldenDiamondArmor,0,2)
                .setUnlocalizedName(matType+"_leggings")
                .setTextureName(UMCMain.MODID+":"+matType+"_leggings")
                .setCreativeTab(UMCTabs.tabCombats);
        Booties = new GDArmor(AllMaterials.MatGoldenDiamondArmor,0,3)
                .setUnlocalizedName(matType+"_booties")
                .setTextureName(UMCMain.MODID+":"+matType+"_booties")
                .setCreativeTab(UMCTabs.tabCombats);  
        Ingot = new GDIngot()
                .setUnlocalizedName(matType+"_ingot")
                .setTextureName(UMCMain.MODID+":"+matType+"_ingot")
                .setCreativeTab(UMCTabs.tabMats)
                .setMaxStackSize(64);  
        
        
        // registre        
        GameRegistry.registerItem(Pickaxe,Pickaxe.getUnlocalizedName());
        GameRegistry.registerItem(Axe,Axe.getUnlocalizedName());
        GameRegistry.registerItem(Spade,Spade.getUnlocalizedName());
        GameRegistry.registerItem(Hoe,Hoe.getUnlocalizedName());
        GameRegistry.registerItem(Sword,Sword.getUnlocalizedName());
        
        GameRegistry.registerItem(ChestPlate,ChestPlate.getUnlocalizedName());
        GameRegistry.registerItem(Booties,Booties.getUnlocalizedName());
        GameRegistry.registerItem(Leggings,Leggings.getUnlocalizedName());
        GameRegistry.registerItem(ScullProtector,ScullProtector.getUnlocalizedName());
        GameRegistry.registerItem(Ingot,Ingot.getUnlocalizedName());
        // Inits ObsidianRecipes
        GoldenDiamondRecipes.init();        
    }
}
