/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraft.stackable.client;

import com.the_nights.ourcraft.stackable.tile.TilePile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;

/**
 *
 * @author Stephanie
 */
public abstract class PileModel implements IBakedModel {

    private final Map<TilePile, List<BakedQuad>> cachedQuads = new WeakHashMap<>();
    private static List<BakedQuad> brokenQuads, fallBack;

    public static void init() {
        BlockState cobble = Blocks.COBBLESTONE.getDefaultState();
        IBakedModel m = Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(cobble);

        fallBack = new ArrayList<>();
        Block[] blocks = new Block[]{Blocks.PURPUR_BLOCK, Blocks.BEDROCK, Blocks.CLAY, Blocks.PACKED_ICE, Blocks.MOSSY_COBBLESTONE, Blocks.LAPIS_BLOCK};
        for (int i = 0; i < 6; i++) {
            BlockState ss = blocks[i].getDefaultState();
            IBakedModel mm = Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(ss);
            List<BakedQuad> qs = mm.getQuads(ss, Direction.values()[i], new Random());
            brokenQuads.addAll(m.getQuads(cobble, Direction.values()[i], new Random()));
            if (!qs.isEmpty()) {
                fallBack.add(qs.get(0));
            }
        }
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction side, Random random) {
        if (side != null) {
            return Collections.emptyList();
        }
        StackTraceElement ste = new Exception().getStackTrace()[3];
        if ("getDamageModel".equals(ste.getMethodName())) {
            return brokenQuads;
        }
        TilePile tile = (TilePile) ((IExtendedBlockState) state).getValue(BlockPile.TILE_PROP);
        List<BakedQuad> quads = new ArrayList<>();
        if (tile != null) {
            //cachedQuads.clear();
            if (!tile.changedClient && cachedQuads.containsKey(tile)) {
                return cachedQuads.get(tile);
            }
            addQuads(quads, tile);
            tile.changedClient = false;
            cachedQuads.put(tile, quads);
        } else {
            quads.addAll(fallBack);
        }
        return quads;
    }

    protected abstract void addQuads(List<BakedQuad> quads, TilePile tile);

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {	return ClientUtils.defaultTas;
    }

    
}
