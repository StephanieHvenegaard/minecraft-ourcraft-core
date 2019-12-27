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
package com.the_nights.ourcraft_core.core;

import java.util.stream.Collectors;

import com.the_nights.ourcraft_core.lists.blocks.OurcraftBlocks;
import com.the_nights.ourcraft_core.lists.items.CoinItems;
import com.the_nights.ourcraft_core.lists.items.EmaraldItems;
import com.the_nights.ourcraft_core.lists.items.MiscItems;
import com.the_nights.ourcraft_core.lists.items.ObsidianItems;
import com.the_nights.ourcraft_core.tap.OurcraftCoreTap;
import com.the_nights.ourcraft_core.world.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import net.minecraft.item.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OurcraftCore.MODID)
public class OurcraftCore {

    public static OurcraftCore INSTANCE;                                                // public instance of the main mod class
    public static final String MODID = "ourcraft-core";                                 // Mod id 
    public static final ItemGroup OURCRAFT_GROUP = new OurcraftCoreTap();               // creative tap  
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger(MODID);                    // Logger
    //public static ArmoryCommonProxy proxy = DistExecutor.runForDist(() -> ArmoryClientProxy::new, () -> ArmoryCommonProxy::new);

    public OurcraftCore() {
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

        OreGeneration.setupOregeneration();
        LOGGER.info("Setup registred");
    }

    // Client Side code
    private void clientRegistry(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Client registred");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = OurcraftCore.MODID)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            // register a new item here           
            event.getRegistry().registerAll(
                    // OurcraftBlocks
                    MiscItems.leadOreItem = new BlockItem(OurcraftBlocks.LeadOre,new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("lead_ore")),
                    MiscItems.obsidianIngotBlockItem = new BlockItem(OurcraftBlocks.ObsidianIngotBlock,new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_ingot_block")),
                    MiscItems.thoriumOreItem = new BlockItem(OurcraftBlocks.ThoriumOre,new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("thorium_ore")),
                    MiscItems.uraniumOreItem = new BlockItem(OurcraftBlocks.UraniumOre,new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("uran_ore")),
                    // Ingots
                    MiscItems.leadNugget = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("lead_nugget")),
                    MiscItems.leadIngot = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("lead_ingot")),
                    ObsidianItems.ingot = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_ingot")),
                    // Misc Items 

                    // Coins
                    CoinItems.wooden_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("wooden_coin")),
                    CoinItems.iron_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("iron_coin")),
                    CoinItems.gold_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("gold_coin")),
                    CoinItems.diamond_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("diamond_coin")),
                    CoinItems.emarald_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("emarald_coin"))
            );
            LOGGER.info("All Items Registered.");
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            // register a new item here
            event.getRegistry().registerAll(
                    OurcraftBlocks.UraniumOre = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 15.0f).harvestLevel(2).sound(SoundType.STONE)).setRegistryName(getLocation("uran_ore")),                    
                    OurcraftBlocks.LeadOre = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 15.0f).harvestLevel(1).sound(SoundType.STONE)).setRegistryName(getLocation("lead_ore")),
                    OurcraftBlocks.ThoriumOre = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 15.0f).harvestLevel(2).sound(SoundType.STONE)).setRegistryName(getLocation("thorium_ore")),
                    OurcraftBlocks.ObsidianIngotBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 15.0f).harvestLevel(1).sound(SoundType.STONE)).setRegistryName(getLocation("obsidian_ingot_block"))
            );
            LOGGER.info("All Blocks Registered.");
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
