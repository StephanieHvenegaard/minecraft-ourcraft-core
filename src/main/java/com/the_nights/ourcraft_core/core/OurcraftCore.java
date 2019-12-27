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

import com.the_nights.ourcraft_core.armory.Configs;
import com.the_nights.ourcraft_core.armory.proxy.ArmoryClientProxy;
import com.the_nights.ourcraft_core.armory.proxy.ArmoryCommonProxy;
import java.util.stream.Collectors;

import com.the_nights.ourcraft_core.core.item.tool.ItemAxe;
import com.the_nights.ourcraft_core.core.item.weapon.ItemFireArm;
import com.the_nights.ourcraft_core.core.item.tool.ItemHoe;
import com.the_nights.ourcraft_core.core.item.weapon.ItemKatana;
import com.the_nights.ourcraft_core.core.item.tool.ItemPickAxe;
import com.the_nights.ourcraft_core.core.item.tool.ItemShovel;
import com.the_nights.ourcraft_core.core.item.weapon.ItemScythe;
import com.the_nights.ourcraft_core.core.item.weapon.ItemSword;
import com.the_nights.ourcraft_core.core.item.material.ArmorMaterial;
import com.the_nights.ourcraft_core.core.item.material.RangedMaterial;
import com.the_nights.ourcraft_core.core.item.material.ToolMaterial;
import com.the_nights.ourcraft_core.lists.armor.BlueGlassArmor;
import com.the_nights.ourcraft_core.lists.armor.CoinItems;
import com.the_nights.ourcraft_core.lists.armor.KelpArmor;
import com.the_nights.ourcraft_core.lists.armor.ObsidianArmor;
import com.the_nights.ourcraft_core.lists.armor.SapphireArmor;
import com.the_nights.ourcraft_core.lists.armor.WoodenArmor;
import com.the_nights.ourcraft_core.lists.blocks.OurcraftBlocks;
import com.the_nights.ourcraft_core.lists.items.BlueGlassItems;
import com.the_nights.ourcraft_core.lists.items.DiamondItem;
import com.the_nights.ourcraft_core.lists.items.EmaraldItems;
import com.the_nights.ourcraft_core.lists.items.GoldenItems;
import com.the_nights.ourcraft_core.lists.items.IronItems;
import com.the_nights.ourcraft_core.lists.items.MiscItems;
import com.the_nights.ourcraft_core.lists.items.ObsidianItems;
import com.the_nights.ourcraft_core.lists.items.StoneItems;
import com.the_nights.ourcraft_core.lists.items.WoodenItem;
import com.the_nights.ourcraft_core.tap.OurcraftCoreTap;
import com.the_nights.ourcraft_core.core.world.OreGeneration;
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
    public static ArmoryCommonProxy proxy = DistExecutor.runForDist(() -> ArmoryClientProxy::new, () -> ArmoryCommonProxy::new);

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
        // Register the bakeConfigs method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::bakeConfigs);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Setup of the mod. 
    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        proxy.postInit(event);
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
            event.getRegistry().registerAll(// OurcraftBlocks 
                    MiscItems.leadOreItem = new BlockItem(OurcraftBlocks.LeadOre,new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("lead_ore")),
                    MiscItems.obsidianIngotBlockItem = new BlockItem(OurcraftBlocks.ObsidianIngotBlock,new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_ingot_block")),
                    MiscItems.thoriumOreItem = new BlockItem(OurcraftBlocks.ThoriumOre,new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("thorium_ore")),
                    MiscItems.uraniumOreItem = new BlockItem(OurcraftBlocks.UraniumOre,new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("uran_ore")),
                    // Ingots
                    MiscItems.leadNugget = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("lead_nugget")),
                    MiscItems.leadIngot = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("lead_ingot")),
                    ObsidianItems.ingot = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_ingot")),
                    BlueGlassItems.ingot = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("blue_glass_ingot")),
                    BlueGlassItems.dust = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("blue_glass_dust")),
                    // Weapons
                    ObsidianItems.sword = new ItemSword(ToolMaterial.Obsidian, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_sword")),
                    ObsidianItems.katana = new ItemKatana(ToolMaterial.Obsidian, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_katana")),
                    EmaraldItems.katana = new ItemKatana(ToolMaterial.Emarald, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("emarald_katana")),
                    EmaraldItems.sword = new ItemSword(ToolMaterial.Emarald, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("emarald_sword")),
                    DiamondItem.katana = new ItemKatana(ToolMaterial.Diamond, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("diamond_katana")),
                    GoldenItems.katana = new ItemKatana(ToolMaterial.Golden, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("gold_katana")),
                    IronItems.katana = new ItemKatana(ToolMaterial.Iron, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("iron_katana")),
                    IronItems.musket = new ItemFireArm(RangedMaterial.FLINTLOCK_MUSKET, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("flintlock_musket")),
                    IronItems.blunderbuss = new ItemFireArm(RangedMaterial.FLINTLOCK_BLUNDERBUSS, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("flintlock_blunderbuss")),
                    IronItems.pistol = new ItemFireArm(RangedMaterial.FLINTLOCK_PISTOL, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("flintlock_pistol")),
                    StoneItems.katana = new ItemKatana(ToolMaterial.Stone, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("stone_katana")),
                    WoodenItem.katana = new ItemKatana(ToolMaterial.Wood, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("wood_katana")),
                    // Firearms

                    MiscItems.blunderAmmo = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("blunderbuss_ammo")),
                    MiscItems.flintlockAmmo = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("flintlock_ammo")),
                    MiscItems.blunderBarrelAssembly = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("flintlock_blunderbuss_barrelassembly")),
                    MiscItems.musketBarrelAssembly = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("flintlock_musket_barrelassembly")),
                    MiscItems.gunstock = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("gunstock")),
                    // Armor 
                    ObsidianArmor.helmet = new ArmorItem(ArmorMaterial.obsidian, EquipmentSlotType.HEAD, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_helmet")),
                    ObsidianArmor.chestplate = new ArmorItem(ArmorMaterial.obsidian, EquipmentSlotType.CHEST, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_chest")),
                    ObsidianArmor.leggings = new ArmorItem(ArmorMaterial.obsidian, EquipmentSlotType.LEGS, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_leggings")),
                    ObsidianArmor.boots = new ArmorItem(ArmorMaterial.obsidian, EquipmentSlotType.FEET, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_boots")),
                    SapphireArmor.helmet = new ArmorItem(ArmorMaterial.Sapphire, EquipmentSlotType.HEAD, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("sapphire_helmet")),
                    SapphireArmor.chestplate = new ArmorItem(ArmorMaterial.Sapphire, EquipmentSlotType.CHEST, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("sapphire_chest")),
                    SapphireArmor.leggings = new ArmorItem(ArmorMaterial.Sapphire, EquipmentSlotType.LEGS, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("sapphire_leggings")),
                    SapphireArmor.boots = new ArmorItem(ArmorMaterial.Sapphire, EquipmentSlotType.FEET, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("sapphire_boots")),
                    KelpArmor.helmet = new ArmorItem(ArmorMaterial.kelp, EquipmentSlotType.HEAD, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("kelp_helmet")),
                    KelpArmor.chestplate = new ArmorItem(ArmorMaterial.kelp, EquipmentSlotType.CHEST, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("kelp_chest")),
                    KelpArmor.leggings = new ArmorItem(ArmorMaterial.kelp, EquipmentSlotType.LEGS, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("kelp_leggings")),
                    KelpArmor.boots = new ArmorItem(ArmorMaterial.kelp, EquipmentSlotType.FEET, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("kelp_boots")),
                    WoodenArmor.helmet = new ArmorItem(ArmorMaterial.wood, EquipmentSlotType.HEAD, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("wood_helmet")),
                    WoodenArmor.chestplate = new ArmorItem(ArmorMaterial.wood, EquipmentSlotType.CHEST, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("wood_chest")),
                    WoodenArmor.leggings = new ArmorItem(ArmorMaterial.wood, EquipmentSlotType.LEGS, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("wood_leggings")),
                    WoodenArmor.boots = new ArmorItem(ArmorMaterial.wood, EquipmentSlotType.FEET, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("wood_boots")),
                    BlueGlassArmor.helmet = new ArmorItem(ArmorMaterial.blue_glass, EquipmentSlotType.HEAD, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("blue_glass_helmet")),
                    BlueGlassArmor.chestplate = new ArmorItem(ArmorMaterial.blue_glass, EquipmentSlotType.CHEST, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("blue_glass_chest")),
                    BlueGlassArmor.leggings = new ArmorItem(ArmorMaterial.blue_glass, EquipmentSlotType.LEGS, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("blue_glass_leggings")),
                    BlueGlassArmor.boots = new ArmorItem(ArmorMaterial.blue_glass, EquipmentSlotType.FEET, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("blue_glass_boots")),
                    // Tools 
                    ObsidianItems.axe = new ItemAxe(ToolMaterial.Obsidian, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_axe")),
                    ObsidianItems.hoe = new ItemScythe(ToolMaterial.Obsidian, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_hoe")),
                    ObsidianItems.pickaxe = new ItemPickAxe(ToolMaterial.Obsidian, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_pickaxe")),
                    ObsidianItems.spade = new ItemShovel(ToolMaterial.Obsidian, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("obsidian_spade")),
                    EmaraldItems.axe = new ItemAxe(ToolMaterial.Emarald, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("emarald_axe")),
                    EmaraldItems.hoe = new ItemHoe(ToolMaterial.Emarald, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("emarald_hoe")),
                    EmaraldItems.pickaxe = new ItemPickAxe(ToolMaterial.Emarald, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("emarald_pickaxe")),
                    EmaraldItems.spade = new ItemShovel(ToolMaterial.Emarald, new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("emarald_spade")),
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

    /* ========================================================
    Other Crap
    ======================================================== */
    @SubscribeEvent
    public void bakeConfigs(ModConfig.ModConfigEvent event) {
        if (event.getConfig().getSpec() == Configs.CLIENT_SPEC) {
            Configs.bake();
        }
    }

    public static class ConfigChange {

        @SubscribeEvent
        public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            //Only process events for this mod
            if (event.getModID().equals(MODID)) {
                proxy.onConfigChanged(event);
            }
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
