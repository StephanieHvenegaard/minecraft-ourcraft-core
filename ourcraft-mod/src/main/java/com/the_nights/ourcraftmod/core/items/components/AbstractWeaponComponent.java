///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.the_nights.ourcraftmod.core.items.components;
//
//import com.google.common.collect.Multimap;
//import net.minecraft.block.Block;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.ai.attributes.AttributeModifier;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.world.World;
//
///**
// *
// * @author Stephanie
// */
//
//public abstract class AbstractWeaponComponent
//{
//	public Item	item;
//	IItemWeapon	weapon;
//	
//	public AbstractWeaponComponent()
//	{
//		item = null;
//		weapon = null;
//	}
//	
//	final void setItem(IItemWeapon itemweapon)
//	{
//		item = (Item) itemweapon;
//		weapon = itemweapon;
//		onSetItem();
//	}
//	
//	protected abstract void onSetItem();
//	
//	//public abstract void setThisItemProperties();
//	
//	public abstract float getEntityDamageMaterialPart();
//	
//	public abstract float getEntityDamage();
//	
//	public abstract float getBlockDamage(ItemStack itemstack, Block block);
//	
//	public abstract boolean canHarvestBlock(Block block);
//	
//	public abstract boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int j, int k, int l, Entity entityliving);
//	
//	public abstract boolean hitEntity(ItemStack itemstack, LivingEntity entityliving, Entity attacker);
//	
//	public abstract int getAttackDelay(ItemStack itemstack, LivingEntity entityliving, Entity attacker);
//	
//	public abstract float getKnockBack(ItemStack itemstack, LivingEntity entityliving, Entity attacker);
//	
//	public abstract int getItemEnchantability();
//	
//	public abstract void addItemAttributeModifiers(Multimap<String, AttributeModifier> multimap);
//	
//	//public abstract EnumAction getItemUseAction(ItemStack itemstack);
//	
//	public abstract int getMaxItemUseDuration(ItemStack itemstack);
//	
//	public abstract boolean onLeftClickEntity(ItemStack itemstack, PlayerEntity player, Entity entity);
//	
//	public abstract ItemStack onItemRightClick(ItemStack itemstack, World world, PlayerEntity entityplayer);
//	
//	public abstract void onUsingTick(ItemStack itemstack, PlayerEntity entityplayer, int count);
//	
//	public abstract void onPlayerStoppedUsing(ItemStack itemstack, World world, PlayerEntity entityplayer, int i);
//	
//	public abstract void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag);
//	
//	//@SideOnly(Side.CLIENT)
//	public abstract boolean shouldRotateAroundWhenRendering();
//}
