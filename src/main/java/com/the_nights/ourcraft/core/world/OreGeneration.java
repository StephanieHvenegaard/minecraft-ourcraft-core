/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraft.core.world;

import com.the_nights.ourcraft.core.lists.blocks.OurcraftBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

/**
 *
 * @author Stephanie
 */
public class OreGeneration {

    private static CountRangeConfig lead_ore_plancement = new CountRangeConfig(8, 3, 0, 150);
    static int lead_ore_chance = 8;
    private static CountRangeConfig uran_ore_plancement = new CountRangeConfig(8, 3, 0, 150);
    static int uran_ore_chance = 4;
    private static CountRangeConfig thorium_ore_plancement = new CountRangeConfig(3, 3, 0, 20);
    static int thorium_ore_chance =1;

    public static void setupOregeneration() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, OurcraftBlocks.LeadOre.getDefaultState(), lead_ore_chance),
                            Placement.COUNT_RANGE, lead_ore_plancement));

            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, OurcraftBlocks.ThoriumOre.getDefaultState(), uran_ore_chance),
                            Placement.COUNT_RANGE, thorium_ore_plancement));

            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, OurcraftBlocks.UraniumOre.getDefaultState(), thorium_ore_chance),
                            Placement.COUNT_RANGE, uran_ore_plancement));

        }
    }
}
