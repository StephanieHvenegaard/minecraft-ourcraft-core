/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.utils.helpers;

import net.minecraft.item.ItemStack;

/**
 *
 * @author Stephanie
 */
public class ReloadHelper
{
	public static int	STATE_NONE	= 0, STATE_RELOADED = 1, STATE_READY = 2;
	
	private static void initTagCompound(ItemStack itemstack)
	{
//		if (!itemstack.hasTagCompound())
//		{
//			itemstack.setTagCompound(new NBTTagCompound());
//		}
	}
	
	public static int getReloadState(ItemStack itemstack)
	{
//		if (itemstack.hasTagCompound()) return itemstack.getTagCompound().getByte("rld");
		return 0;
	}
	
	public static void setReloadState(ItemStack itemstack, int state)
	{
//		initTagCompound(itemstack);
//		itemstack.getTagCompound().setByte("rld", (byte) state);
	}
}
