/* 

MIT License

Copyright (c) 2019 Stephanie Hvenegaard

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */
package com.the_nights.ourcraftmod.core;

import com.the_nights.ourcraftmod.core.items.ItemCustomAxe;
import com.the_nights.ourcraftmod.core.items.ItemCustomHoe;
import com.the_nights.ourcraftmod.core.items.ItemCustomPickAxe;
import com.the_nights.ourcraftmod.core.items.ItemCustomShovel;
import com.the_nights.ourcraftmod.core.items.ItemCustomSword;
import com.the_nights.ourcraftmod.core.lists.CoinItems;
import com.the_nights.ourcraftmod.core.lists.ObsidianItems;
import com.the_nights.ourcraftmod.core.lists.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("ourcraftmod")
public class Main {

    public static Main INSTANCE;                                                    // public instance of the main mod class
    public static final String MODID = "ourcraftmod";                               // Mod id 
    public static final ItemGroup OURCRAFT_GROUP = new OurcraftItemGroup(); // creative tap  
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger(MODID);               // Logger

    public Main() {
        INSTANCE = this;
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistry);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Setup of the mod. 
    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("Setup registred");
    }

    // Client Side code
    private void clientRegistry(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Client registred");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            // register a new item here
            LOGGER.info("HELLO from Register Item");
            event.getRegistry().registerAll(
                    // Ingots
                    ObsidianItems.o_ingot = new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(getLocation("obsidian_ingot")),
                    // Weapons                    
                    ObsidianItems.o_sword = new ItemCustomSword(ToolMaterial.Obsidian,0,4.0f, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("obsidian_sword")),
                    ObsidianItems.Axe = new ItemCustomAxe(ToolMaterial.Obsidian,0.0f,4.0f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("obsidian_axe")),
                    ObsidianItems.Hoe = new ItemCustomHoe(ToolMaterial.Obsidian,4.0f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("obsidian_hoe")),
                    ObsidianItems.Pickaxe = new ItemCustomPickAxe(ToolMaterial.Obsidian,0,4.0f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("obsidian_pickaxe")),
                    ObsidianItems.Spade = new ItemCustomShovel(ToolMaterial.Obsidian,0.0f,4.0f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("obsidian_spade")),
                    // Coins
                    CoinItems.wooden_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("wooden_coin")),
                    CoinItems.iron_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("iron_coin")),
                    CoinItems.gold_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("gold_coin")),
                    CoinItems.golden_diamond_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("golden_diamond_coin")),
                    CoinItems.diamond_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("diamond_coin")),
                    CoinItems.emarald_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("emarald_coin"))
            );
        }

//        @SubscribeEvent
//        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
//            // register a new block here
//            LOGGER.info("HELLO from Register Block");
//        }
        private static ResourceLocation getLocation(String name) {
            return new ResourceLocation(MODID, name);
        }
   
    }

    /* ========================================================
    Other Crap
    ======================================================== */
    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}
