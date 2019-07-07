/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.items.components;

import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Stephanie
 */
public interface IItemWeapon 
{
	public boolean onLeftClickEntity(ItemStack itemstack, PlayerEntity player, Entity entity);
	
	//public UUID getUUID();
	
	public Random getItemRand();
	
	public MeleeComponent getMeleeComponent();
	
	public RangedComponent getRangedComponent();
}