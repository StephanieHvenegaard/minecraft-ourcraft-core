/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.items.components;

import com.google.common.collect.Multimap;
import com.the_nights.ourcraftmod.core.utils.helpers.ReloadHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 *
 * @author Stephanie
 */
public abstract class RangedComponent extends AbstractWeaponComponent {

    protected static final int MAX_DELAY = 72000;

    public static boolean isReloaded(ItemStack itemstack) {
        return ReloadHelper.getReloadState(itemstack) >= ReloadHelper.STATE_RELOADED;
    }

    public static boolean isReadyToFire(ItemStack itemstack) {
        return ReloadHelper.getReloadState(itemstack) == ReloadHelper.STATE_READY;
    }

    public static void setReloadState(ItemStack itemstack, int state) {
        ReloadHelper.setReloadState(itemstack, state);
    }

    public final RangedSpecs rangedSpecs;

    public RangedComponent(RangedSpecs rangedspecs) {
        rangedSpecs = rangedspecs;
    }

    @Override
    protected void onSetItem() {
    }

    @Override
    public float getEntityDamageMaterialPart() {
        return 0;
    }

    @Override
    public float getEntityDamage() {
        return 0;
    }

    @Override
    public float getBlockDamage(ItemStack itemstack, Block block) {
        return 0;
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return false;
    }


    @Override
    public int getItemEnchantability() {
        return 1;
    }

    @Override
    public void addItemAttributeModifiers(Multimap<String, AttributeModifier> multimap) {
        //multimap.put(WeaponModAttributes.RELOAD_TIME.getAttributeUnlocalizedName(), new AttributeModifier(weapon.getUUID(), "Weapon reloadtime modifier", rangedSpecs.getReloadTime(), 0));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack itemstack, PlayerEntity player, Entity entity) {
        return false;
    }

//    @Override
//    public EnumAction getItemUseAction(ItemStack itemstack) {
//        int state = ReloadHelper.getReloadState(itemstack);
//        if (state == ReloadHelper.STATE_NONE) {
//            return EnumAction.block;
//        } else if (state == ReloadHelper.STATE_READY) {
//            return EnumAction.bow;
//        }
//        return EnumAction.none;
//    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemstack) {
        return MAX_DELAY;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, PlayerEntity entityplayer) {
        if (!itemstack.isEmpty()) {
            return itemstack;
        }
        if (hasAmmo(itemstack, world, entityplayer)) //Check can reload
        {
            if (isReadyToFire(itemstack)) {
                //Start aiming weapon to fire
                soundCharge(itemstack, world, entityplayer);
                //entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
            } else {
                //Begin reloading
                //entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
            }
        } else {
            //Can't reload; no ammo
            soundEmpty(itemstack, world, entityplayer);
            setReloadState(itemstack, ReloadHelper.STATE_NONE);
        }

        return itemstack;
    }

    @Override
    public void onUsingTick(ItemStack itemstack, PlayerEntity entityplayer, int count) {
        if (ReloadHelper.getReloadState(itemstack) == ReloadHelper.STATE_NONE && getMaxItemUseDuration(itemstack) - count >= getReloadDuration(itemstack)) {
            effectReloadDone(itemstack, entityplayer.getEntityWorld(), entityplayer);
            //entityplayer.clearItemInUse();
            setReloadState(itemstack, ReloadHelper.STATE_RELOADED);
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, PlayerEntity entityplayer, int i) {
        if (!isReloaded(itemstack)) {
            return;
        }
        if (isReadyToFire(itemstack)) {
            if (hasAmmoAndConsume(itemstack, world, entityplayer)) {
                fire(itemstack, world, entityplayer, i);
            }
            setReloadState(itemstack, ReloadHelper.STATE_NONE);
        } else {
            setReloadState(itemstack, ReloadHelper.STATE_READY);
        }
    }

    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
    }

    //@SideOnly(Side.CLIENT)
    @Override
    public boolean shouldRotateAroundWhenRendering() {
        return false;
    }

    public void soundEmpty(ItemStack itemstack, World world, PlayerEntity entityplayer) {
        //world.playSound(entityplayer,, "random.click", 1.0F, 1.0F / 0.8F);
    }

    public void soundCharge(ItemStack itemstack, World world, PlayerEntity entityplayer) {
    }

    public final void postShootingEffects(ItemStack itemstack, PlayerEntity entityplayer, World world) {
        effectPlayer(itemstack, entityplayer, world);
        effectShoot(world, entityplayer.posX, entityplayer.posY, entityplayer.posZ, entityplayer.rotationYaw, entityplayer.rotationPitch);
    }

    public abstract void effectReloadDone(ItemStack itemstack, World world, Entity entityplayer);

    public abstract void fire(ItemStack itemstack, World world, PlayerEntity entityplayer, int i);

    public abstract void effectPlayer(ItemStack itemstack, PlayerEntity entityplayer, World world);

    public abstract void effectShoot(World world, double x, double y, double z, float yaw, float pitch);

    public int getReloadDuration(ItemStack itemstack) {
        return rangedSpecs.getReloadTime();
    }

    public Item getAmmoItem() {
        return rangedSpecs.getAmmoItem();
    }

    public boolean hasAmmoAndConsume(ItemStack itemstack, World world, PlayerEntity entityplayer) {
        return true;  // entityplayer.world.isCreativeMode;// || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemstack) > 0 || entityplayer.inventory.consumeInventoryItem(getAmmoItem());
    }

    public boolean hasAmmo(ItemStack itemstack, World world, PlayerEntity entityplayer) {
        return true ;//entityplayer.capabilities.isCreativeMode; //|| EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemstack) > 0 || entityplayer.inventory.hasItem(getAmmoItem());
    }

    public float getFOVMultiplier(int ticksinuse) {
        float f1 = ticksinuse / getMaxAimTimeTicks();

        if (f1 > 1.0F) {
            f1 = 1.0F;
        } else {
            f1 *= f1;
        }

        return 1.0F - f1 * getMaxZoom();
    }

    protected float getMaxAimTimeTicks() {
        return 20.0f;
    }

    protected float getMaxZoom() {
        return 0.15f;
    }
}
