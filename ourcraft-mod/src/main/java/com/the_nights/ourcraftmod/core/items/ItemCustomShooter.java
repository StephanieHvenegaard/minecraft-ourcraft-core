/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.items;

import com.the_nights.ourcraftmod.core.items.components.IItemWeapon;
import com.the_nights.ourcraftmod.core.items.components.MeleeComponent;
import com.the_nights.ourcraftmod.core.items.components.RangedComponent;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Stephanie
 */
public class ItemCustomShooter extends BowItem implements IItemWeapon {

    protected static final int MAX_DELAY = 72000;

    public RangedComponent rangedComponent;
    public MeleeComponent meleeComponent;

    public ItemCustomShooter(RangedComponent rangedcomponent, MeleeComponent meleecomponent, Properties props) {
        
        super(props);

        this.rangedComponent = rangedcomponent;
        this.meleeComponent = meleecomponent;

        //rangedcomponent.setItem(this);
        //meleecomponent.setItem(this);

        //rangedcomponent.setThisItemProperties();
    }
    @Override
    public boolean onLeftClickEntity(ItemStack itemstack, PlayerEntity player, Entity entity) {
        return meleeComponent.onLeftClickEntity(itemstack, player, entity) && rangedComponent.onLeftClickEntity(itemstack, player, entity);
    }

    @Override
    public final Random getItemRand() {
        return random;
    }

    @Override
    public MeleeComponent getMeleeComponent() {
        return meleeComponent;
    }

    @Override
    public RangedComponent getRangedComponent() {
        return rangedComponent;
    }
}

  ///////
//    @Override
//    public EnumAction getItemUseAction(ItemStack itemstack) {
//        return rangedComponent.getItemUseAction(itemstack);
//    }
//
//    @Override
//    public int getMaxItemUseDuration(ItemStack itemstack) {
//        return rangedComponent.getMaxItemUseDuration(itemstack);
//    }
//
//    @Override
//    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
//        return rangedComponent.onItemRightClick(itemstack, world, entityplayer);
//    }
//
//    @Override
//    public void onUsingTick(ItemStack itemstack, EntityPlayer entityplayer, int count) {
//        rangedComponent.onUsingTick(itemstack, entityplayer, count);
//    }
//
//    @Override
//    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i) {
//        rangedComponent.onPlayerStoppedUsing(itemstack, world, entityplayer, i);
//    }
//
//    @Override
//    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
//        meleeComponent.onUpdate(itemstack, world, entity, i, flag);
//        rangedComponent.onUpdate(itemstack, world, entity, i, flag);
//    }
//
//    @Override
//    public boolean shouldRotateAroundWhenRendering() {
//        return rangedComponent.shouldRotateAroundWhenRendering();
//    }
