/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items.coins;

import com.sn.core.UMCMain;
import com.sn.core.UMCTabs;
import com.sn.items.goldendiamond.GoldenDiamondItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Stephanie
 */
public class CoinItems {

    public static Item WoodenCoin;
    public static Item IronCoin;
    public static Item GoldCoin;
    public static Item EmaraldCoin;
    public static Item GoldenDiamondCoin;
    public static Item DiamondCoin;

    @Mod.EventHandler
    public static void init() {
        WoodenCoin = new Coin()
                .setUnlocalizedName("wooden_coin")
                .setTextureName(UMCMain.MODID + ":wooden_coin")
                .setCreativeTab(UMCTabs.tabMats)
                .setMaxStackSize(64);
        IronCoin = new Coin()
                .setUnlocalizedName("iron_coin")
                .setTextureName(UMCMain.MODID + ":iron_coin")
                .setCreativeTab(UMCTabs.tabMats)
                .setMaxStackSize(64);
        GoldCoin = new Coin()
                .setUnlocalizedName("gold_coin")
                .setTextureName(UMCMain.MODID + ":gold_coin")
                .setCreativeTab(UMCTabs.tabMats)
                .setMaxStackSize(64);
        EmaraldCoin = new Coin()
                .setUnlocalizedName("emarald_coin")
                .setTextureName(UMCMain.MODID + ":emarald_coin")
                .setCreativeTab(UMCTabs.tabMats)
                .setMaxStackSize(64);
        GoldenDiamondCoin = new Coin()
                .setUnlocalizedName("goldendiamond_coin")
                .setTextureName(UMCMain.MODID + ":goldendiamond_coin")
                .setCreativeTab(UMCTabs.tabMats)
                .setMaxStackSize(64);
        DiamondCoin = new Coin()
                .setUnlocalizedName("diamond_coin")
                .setTextureName(UMCMain.MODID + ":diamond_coin")
                .setCreativeTab(UMCTabs.tabMats)
                .setMaxStackSize(64);

        // registre        
        GameRegistry.registerItem(WoodenCoin, WoodenCoin.getUnlocalizedName());
        GameRegistry.registerItem(IronCoin, IronCoin.getUnlocalizedName());
        GameRegistry.registerItem(GoldCoin, GoldCoin.getUnlocalizedName());
        GameRegistry.registerItem(EmaraldCoin, EmaraldCoin.getUnlocalizedName());
        GameRegistry.registerItem(GoldenDiamondCoin, GoldenDiamondCoin.getUnlocalizedName());
        GameRegistry.registerItem(DiamondCoin, DiamondCoin.getUnlocalizedName());
        // Inits ObsidianRecipes
        GameRegistry.addRecipe(new ItemStack(WoodenCoin, 16),
                "   ",
                " YY",
                " YX",
                'X', Items.diamond,
                'Y', Items.stick);
        GameRegistry.addRecipe(new ItemStack(IronCoin, 4),
                "   ",
                " YY",
                " YX",
                'X', WoodenCoin,
                'Y', Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(GoldCoin, 4),
                "   ",
                " YY",
                " YX",
                'X', IronCoin,
                'Y', Items.gold_ingot);
        GameRegistry.addRecipe(new ItemStack(EmaraldCoin, 4),
                "   ",
                " YY",
                " YX",
                'X', GoldCoin,
                'Y', Items.emerald);
        GameRegistry.addRecipe(new ItemStack(GoldenDiamondCoin, 4),
                "   ",
                " YY",
                " YX",
                'X', EmaraldCoin,
                'Y', GoldenDiamondItems.Ingot);
        GameRegistry.addRecipe(new ItemStack(DiamondCoin, 4),
                "   ",
                " YY",
                " YX",
                'X', GoldenDiamondCoin,
                'Y', Items.diamond);                                                        
    }
}
