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
package com.the_nights.ourcraft.core;

import com.the_nights.ourcraft.armory.Configs;
import com.the_nights.ourcraft.armory.proxy.ClientProxy;
import com.the_nights.ourcraft.armory.proxy.CommonProxy;
import java.util.stream.Collectors;

import com.the_nights.ourcraft.core.items.ItemCustomAxe;
import com.the_nights.ourcraft.core.items.ItemCustomFireArm;
import com.the_nights.ourcraft.core.items.ItemCustomHoe;
import com.the_nights.ourcraft.core.items.ItemCustomKatana;
import com.the_nights.ourcraft.core.items.ItemCustomPickAxe;
import com.the_nights.ourcraft.core.items.ItemCustomShovel;
import com.the_nights.ourcraft.core.items.ItemCustomSword;
import com.the_nights.ourcraft.core.items.materials.ArmorMaterial;
import com.the_nights.ourcraft.core.items.materials.RangedMaterial;
import com.the_nights.ourcraft.core.items.materials.ToolMaterial;
import com.the_nights.ourcraft.core.lists.armor.BlueGlassArmor;
import com.the_nights.ourcraft.core.lists.armor.CoinItems;
import com.the_nights.ourcraft.core.lists.armor.KelpArmor;
import com.the_nights.ourcraft.core.lists.armor.ObsidianArmor;
import com.the_nights.ourcraft.core.lists.armor.SapphireArmor;
import com.the_nights.ourcraft.core.lists.armor.WoodenArmor;
import com.the_nights.ourcraft.core.lists.blocks.Blocks;
import com.the_nights.ourcraft.core.lists.items.BlueGlassItems;
import com.the_nights.ourcraft.core.lists.items.DiamondItem;
import com.the_nights.ourcraft.core.lists.items.EmaraldItems;
import com.the_nights.ourcraft.core.lists.items.GoldenItems;
import com.the_nights.ourcraft.core.lists.items.IronItems;
import com.the_nights.ourcraft.core.lists.items.MiscItems;
import com.the_nights.ourcraft.core.lists.items.ObsidianItems;
import com.the_nights.ourcraft.core.lists.items.StoneItems;
import com.the_nights.ourcraft.core.lists.items.WoodenItem;
import com.the_nights.ourcraft.core.tap.OurcraftCoreTap;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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
    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

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
                    // Blocks 
                    MiscItems.LeadOreItem = new BlockItem(Blocks.LeadOre,new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("lead_ore_item")),                            
                            
                    // Ingots
                    ObsidianItems.ingot = new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(getLocation("obsidian_ingot")),
                    BlueGlassItems.ingot = new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(getLocation("blue_glass_ingot")),
                    BlueGlassItems.dust = new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(getLocation("blue_glass_dust")),
                    // Weapons                    
                    ObsidianItems.sword = new ItemCustomSword(ToolMaterial.Obsidian, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("obsidian_sword")),
                    ObsidianItems.katana = new ItemCustomKatana(ToolMaterial.Obsidian, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("obsidian_katana")),
                    EmaraldItems.katana = new ItemCustomKatana(ToolMaterial.Emarald, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("emarald_katana")),
                    EmaraldItems.sword = new ItemCustomSword(ToolMaterial.Emarald, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("emarald_sword")),
                    DiamondItem.katana = new ItemCustomKatana(ToolMaterial.Diamond, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("diamond_katana")),
                    GoldenItems.katana = new ItemCustomKatana(ToolMaterial.Golden, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("gold_katana")),
                    IronItems.katana = new ItemCustomKatana(ToolMaterial.Iron, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("iron_katana")),
                    IronItems.musket = new ItemCustomFireArm(RangedMaterial.FLINTLOCK_MUSKET, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("flintlock_musket")),
                    IronItems.blunderbuss = new ItemCustomFireArm(RangedMaterial.FLINTLOCK_BLUNDERBUSS, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("flintlock_blunderbuss")),
                    IronItems.pistol = new ItemCustomFireArm(RangedMaterial.FLINTLOCK_PISTOL, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("flintlock_pistol")),
                    StoneItems.katana = new ItemCustomKatana(ToolMaterial.Stone, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("stone_katana")),
                    WoodenItem.katana = new ItemCustomKatana(ToolMaterial.Wood, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("wood_katana")),
                    // Armor 
                    ObsidianArmor.helmet = new ArmorItem(ArmorMaterial.obsidian, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("obsidian_helmet")),
                    ObsidianArmor.chestplate = new ArmorItem(ArmorMaterial.obsidian, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("obsidian_chest")),
                    ObsidianArmor.leggings = new ArmorItem(ArmorMaterial.obsidian, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("obsidian_leggings")),
                    ObsidianArmor.boots = new ArmorItem(ArmorMaterial.obsidian, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("obsidian_boots")),
                    SapphireArmor.helmet = new ArmorItem(ArmorMaterial.Sapphire, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("sapphire_helmet")),
                    SapphireArmor.chestplate = new ArmorItem(ArmorMaterial.Sapphire, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("sapphire_chest")),
                    SapphireArmor.leggings = new ArmorItem(ArmorMaterial.Sapphire, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("sapphire_leggings")),
                    SapphireArmor.boots = new ArmorItem(ArmorMaterial.Sapphire, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("sapphire_boots")),
                    KelpArmor.helmet = new ArmorItem(ArmorMaterial.kelp, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("kelp_helmet")),
                    KelpArmor.chestplate = new ArmorItem(ArmorMaterial.kelp, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("kelp_chest")),
                    KelpArmor.leggings = new ArmorItem(ArmorMaterial.kelp, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("kelp_leggings")),
                    KelpArmor.boots = new ArmorItem(ArmorMaterial.kelp, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("kelp_boots")),
                    WoodenArmor.helmet = new ArmorItem(ArmorMaterial.wood, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("wood_helmet")),
                    WoodenArmor.chestplate = new ArmorItem(ArmorMaterial.wood, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("wood_chest")),
                    WoodenArmor.leggings = new ArmorItem(ArmorMaterial.wood, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("wood_leggings")),
                    WoodenArmor.boots = new ArmorItem(ArmorMaterial.wood, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("wood_boots")),
                    BlueGlassArmor.helmet = new ArmorItem(ArmorMaterial.blue_glass, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("blue_glass_helmet")),
                    BlueGlassArmor.chestplate = new ArmorItem(ArmorMaterial.blue_glass, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("blue_glass_chest")),
                    BlueGlassArmor.leggings = new ArmorItem(ArmorMaterial.blue_glass, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("blue_glass_leggings")),
                    BlueGlassArmor.boots = new ArmorItem(ArmorMaterial.blue_glass, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("blue_glass_boots")),
                    // Tools 
                    ObsidianItems.axe = new ItemCustomAxe(ToolMaterial.Obsidian, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("obsidian_axe")),
                    ObsidianItems.hoe = new ItemCustomHoe(ToolMaterial.Obsidian, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("obsidian_hoe")),
                    ObsidianItems.pickaxe = new ItemCustomPickAxe(ToolMaterial.Obsidian, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("obsidian_pickaxe")),
                    ObsidianItems.spade = new ItemCustomShovel(ToolMaterial.Obsidian, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("obsidian_spade")),
                    EmaraldItems.axe = new ItemCustomAxe(ToolMaterial.Emarald, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("emarald_axe")),
                    EmaraldItems.hoe = new ItemCustomHoe(ToolMaterial.Emarald, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("emarald_hoe")),
                    EmaraldItems.pickaxe = new ItemCustomPickAxe(ToolMaterial.Emarald, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("emarald_pickaxe")),
                    EmaraldItems.spade = new ItemCustomShovel(ToolMaterial.Emarald, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(getLocation("emarald_spade")),
                    // Misc Items 
                    MiscItems.flintlockAmmo = new Item(new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("flintlock_ammo")),
                    MiscItems.blunderBarrelAssembly = new Item(new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("flintlock_blunderbuss_barrelassembly")),
                    MiscItems.musketBarrelAssembly = new Item(new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("flintlock_musket_barrelassembly")),
                    MiscItems.gunstock = new Item(new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(getLocation("gunstock")),
                    // Coins
                    CoinItems.wooden_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("wooden_coin")),
                    CoinItems.iron_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("iron_coin")),
                    CoinItems.gold_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("gold_coin")),
                    //CoinItems.golden_diamond_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("golden_diamond_coin")),
                    CoinItems.diamond_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("diamond_coin")),
                    CoinItems.emarald_coin = new Item(new Item.Properties().group(OURCRAFT_GROUP)).setRegistryName(getLocation("emarald_coin"))
            );
            LOGGER.info("All Items Registered.");
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            // register a new item here
            event.getRegistry().registerAll(
                    Blocks.UraniumOre = new Block(Block.Properties.create(Material.GOURD).hardnessAndResistance(0.0f, 0.0f).harvestLevel(2).sound(SoundType.STONE)).setRegistryName(getLocation("uran_ore")),
                    Blocks.LeadOre = new Block(Block.Properties.create(Material.GOURD).hardnessAndResistance(0.0f, 0.0f).harvestLevel(0).sound(SoundType.STONE)).setRegistryName(getLocation("lead_ore")),
                    Blocks.ThoriumOre = new Block(Block.Properties.create(Material.GOURD).hardnessAndResistance(0.0f, 0.0f).harvestLevel(0).sound(SoundType.STONE)).setRegistryName(getLocation("thorium_ore")),
                    Blocks.ObsidianIngotBlock = new Block(Block.Properties.create(Material.GOURD).hardnessAndResistance(0.0f, 0.0f).harvestLevel(0).sound(SoundType.STONE)).setRegistryName(getLocation("obsidian_ingot_block"))
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
