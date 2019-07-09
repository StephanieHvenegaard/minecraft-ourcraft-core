///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.the_nights.ourcraftmod.core.items.components;
//
//import com.the_nights.ourcraftmod.core.items.ItemCustomShooter;
//import com.the_nights.ourcraftmod.core.items.components.weapons.RangedCompMusket;
//import com.the_nights.ourcraftmod.core.lists.items.IronItems;
//import com.the_nights.ourcraftmod.core.utils.helpers.PhysHelper;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//
///**
// *
// * @author Stephanie
// */
//public class ItemMusket extends ItemCustomShooter {
//
//    protected Item bayonetItem;
//    private int bayonetDurability;
//
//    public ItemMusket(String id, MeleeComponent meleecomponent, Item bayonetitem) {
//        super(id, new RangedCompMusket(), meleecomponent);
//        bayonetItem = bayonetitem;
//        if (meleecomponent.meleeSpecs != MeleeSpecs.NONE && meleecomponent.weaponMaterial != null) {
//            bayonetDurability = meleecomponent.meleeSpecs.durabilityBase + (int) (meleecomponent.weaponMaterial.getMaxUses() * meleecomponent.meleeSpecs.durabilityMult);
//        }
//    }
//
//    public boolean hasBayonet() {
//        return bayonetItem != null;
//    }
//
//    @Override
//    public boolean hitEntity(ItemStack itemstack, LivingEntity entityliving, LivingEntity attacker) {
//        if (entityliving.hurtResistantTime == entityliving.maxHurtResistantTime) {
//            float kb = meleeComponent.getKnockBack(itemstack, entityliving, attacker);
//            //if (entityliving.onGround)
//            {
//                PhysHelper.knockBack(entityliving, attacker, kb);
//            }
//            entityliving.hurtResistantTime += meleeComponent.meleeSpecs.attackDelay;
//        }
//
//        if (attacker instanceof PlayerEntity && !((PlayerEntity) attacker).capabilities.isCreativeMode) {
//            PlayerEntity entityplayer = (PlayerEntity) attacker;
//            if (itemstack.stackTagCompound == null) {
//                itemstack.stackTagCompound = new NBTTagCompound();
//            }
//            int bayonetdamage = itemstack.stackTagCompound.getShort("bayonetDamage") + 1;
//            if (bayonetdamage > bayonetDurability) {
//                /*
//				if (deltadamage > 0)
//				{
//					int m = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, itemstack);
//					int k = 0;
//					
//					for (int l = 0; j > 0 && l < deltadamage; ++l)
//					{
//						if (EnchantmentDurability.negateDamage(itemstack, m, entityplayer.getRNG()))
//						{
//							++k;
//						}
//					}
//					
//					deltadamage -= k;
//					
//					if (deltadamage <= 0)
//					{
//						deltadamage = 0;
//					}
//				}
//                 */
//                entityplayer.renderBrokenItemStack(itemstack);
//                int id = Item.getIdFromItem(this);
//                if (id != 0) {
////                    BalkonsWeaponMod.modLog.debug("Musket Item (" + toString() + ") ID = " + id);
////                    entityplayer.addStat(StatList.objectBreakStats[id], 1);
//                }
//                bayonetdamage = 0;
//                itemstack.func_150996_a(IronItems.musket);
//            }
//            itemstack.stackTagCompound.setShort("bayonetDamage", (short) bayonetdamage);
//        }
//        return true;
//    }
//}
