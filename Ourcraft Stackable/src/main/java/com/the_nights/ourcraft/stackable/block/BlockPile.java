/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraft.stackable.block;

import com.the_nights.ourcraft.stackable.tile.TilePile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;

/**
 *
 * @author Stephanie
 */
public class BlockPile extends Block {

    public BlockPile(Properties prprts) {
        super(prprts.hardnessAndResistance(6.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @OnlyIn(value = Dist.CLIENT)
    public static boolean shouldSideBeRendered(BlockState bs, IBlockReader reader, BlockPos bp, Direction drctn) {
        return true;
    }

    public static boolean isOpaque(VoxelShape vs) {
        return true;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        TileEntity t = world.getTileEntity(pos);
        if (t instanceof TilePile) {
            ItemStack stack = ((TilePile) t).lookingStack(player);
            if (!stack.isEmpty()) {
                return ItemHandlerHelper.copyStackWithSize(stack, 1);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        TileEntity t = worldIn.getTileEntity(pos);
        if (worldIn.isAirBlock(pos.down()) && t instanceof TilePile && !((TilePile) t).isMaster) {
            worldIn.setBlockToAir(pos);
        }
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return true;
        } else {
            TileEntity t = worldIn.getTileEntity(pos);
            if (t instanceof TilePile && handIn == Hand.MAIN_HAND) {
                TilePile tile = (TilePile) t;
                ItemStack stack = player.getHeldItem(hand);
                if (stack.getItem() == Stackable.changer) {
                    Stackable.changer.getMode(stack).action(tile, player);
                } else if (tile.validItem(stack)) {
                    boolean ctrl = ctrlSet.contains(player.getUniqueID());
                    ItemStack toInsert = ctrl ? ItemHandlerHelper.copyStackWithSize(stack, 1) : stack;
                    ItemStack rest = tile.getMaster().inv.insertItem(toInsert, false);
                    int inserted = rest.isEmpty() ? toInsert.getCount() : toInsert.getCount() - rest.getCount();
                    worldIn.playSound(null, pos, tile.placeSound(stack), SoundCategory.BLOCKS, .3f, worldIn.rand.nextFloat() / 2f + .5f);
                    if (!player.capabilities.isCreativeMode) {
                        stack.shrink(inserted);
                    }
                    return true;
                } else if (player.getHeldItemMainhand().isEmpty()) {
                    ItemStack looking = tile.lookingStack(player);
                    if (looking.isEmpty()) {
                        return true;
                    }
                    PlayerMainInvWrapper playerInv = new PlayerMainInvWrapper(playerIn.inventory);
                    for (int i = 0; i < playerInv.getSlots(); i++) {
                        ItemStack s = playerInv.getStackInSlot(i);
                        if (!s.isEmpty() && s.isItemEqual(looking)) {
                            ItemStack rest = tile.getMaster().inv.insertItem(playerInv.extractItem(i, 64, false), false);
                            worldIn.playSound(null, pos, tile.placeSound(stack), SoundCategory.BLOCKS, .3f, worldIn.rand.nextFloat() / 2f + .5f);
                            if (!rest.isEmpty()) {
                                playerInv.insertItem(i, rest, false);
                            }
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity pe, BlockPos pos, BlockState state, TileEntity te, ItemStack is) {
        TileEntity t = worldIn.getTileEntity(pos);
		if (t instanceof TilePile) {
			TilePile tile = (TilePile) t;
			if (tile.isMaster) {
				tile.inv.items = null;
				IntStream.range(0, tile.inv.getSlots()).forEach(i -> spawnAsEntity(worldIn, pos, tile.inv.getStackInSlot(i)));
				worldIn.removeTileEntity(pos);
			} else {
				if (tile.getMaster() != null) {
					List<TilePile> ts = tile.getAllPileBlocks();
					for (int i = ts.size() - 1; i >= 1; i--) {
						TilePile t2 = ts.get(i);
						for (ItemStack s : t2.itemList()) {
							spawnAsEntity(worldIn, pos, t2.getMaster().inv.extractItem(s, s.getCount(), false));
						}
						if (t2 == tile)
							break;
					}
				}
				worldIn.removeTileEntity(pos);
			}
		} else if (t != null)
			worldIn.removeTileEntity(pos);
    }
    
   
}
