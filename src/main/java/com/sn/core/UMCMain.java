package com.sn.core;

import com.sn.items.goldendiamond.GoldenDiamondItems;
import com.sn.items.ObsidianItems;
import com.sn.items.blocks.BlockItems;
import com.sn.items.coins.CoinItems;
import com.sn.items.food.FoodItems;
import com.sn.items.misc.MiscItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = UMCMain.MODID, version = UMCMain.VERSION, name =UMCMain.NAME)
public class UMCMain
{
    public static final String NAME = "Ultimate Minecraft";
    public static final String MODID = "umc";
    public static final String VERSION = "1.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {       
    }    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ObsidianItems.init();
        GoldenDiamondItems.init();
        CoinItems.init();
        BlockItems.init();
        MiscItems.init();
        FoodItems.init();
    }   
}
