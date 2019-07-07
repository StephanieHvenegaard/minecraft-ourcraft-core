/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.items.components;

import com.google.common.collect.Multimap;
import com.the_nights.ourcraftmod.core.Materials.ToolMaterial;
import com.the_nights.ourcraftmod.core.utils.helpers.PhysHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJModel.Material;

/**
 *
 * @author Stephanie
 */
public class MeleeComponent extends AbstractWeaponComponent {

    public final MeleeSpecs meleeSpecs;
    public final IItemTier weaponMaterial;

    public MeleeComponent(MeleeSpecs meleespecs, IItemTier toolmaterial) {
        meleeSpecs = meleespecs;
        weaponMaterial = toolmaterial;
    }

    @Override
    protected void onSetItem() {
    }

//    @Override
//    public void setThisItemProperties() {
//        if (weaponMaterial == null) {
//            item.setMaxDamage(meleeSpecs.durabilityBase);
//        } else {
//            item.setMaxDamage((int) (meleeSpecs.durabilityBase + weaponMaterial.getMaxUses() * meleeSpecs.damageMult));
//        }
//        item.setMaxStackSize(meleeSpecs.stackSize);
//    }

    @Override
    public float getEntityDamageMaterialPart() {
//        if (weaponMaterial == null) {
//            return 0F;
//        }
//        return weaponMaterial.getDamageVsEntity() * meleeSpecs.damageMult;
        return 0F;
    }
    @Override
    public float getEntityDamage() {
        return meleeSpecs.damageBase + getEntityDamageMaterialPart();
    }

//	@Override
//	public float getBlockDamage(ItemStack itemstack, Block block)
//	{
//		if (canHarvestBlock(block))
//		{
//			return meleeSpecs.blockDamage * 10F;
//		}
//		Material material = block.getMaterial();
//		return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : meleeSpecs.blockDamage;
//	}
    @Override
    public boolean canHarvestBlock(Block block) {
        return block == Blocks.COBWEB;
    }

//    @Override
//    public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int j, int k, int l, LivingEntity entityliving) {
//        if ((meleeSpecs.blockDamage > 1F || canHarvestBlock(block)) && block.getBlockHardness(world, j, k, l) != 0F) {
//            itemstack.damageItem(meleeSpecs.dmgFromBlock, entityliving);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean hitEntity(ItemStack itemstack, LivingEntity entityliving, LivingEntity attacker) {
//        if (entityliving.hurtResistantTime == entityliving.maxHurtResistantTime) {
//            float kb = getKnockBack(itemstack, entityliving, attacker);
//            //if (entityliving.onGround)
//            {
//                PhysHelper.knockBack(entityliving, attacker, kb);
//            }
//            entityliving.hurtResistantTime += getAttackDelay(itemstack, entityliving, attacker);
//        }
//        itemstack.damageItem(meleeSpecs.dmgFromEntity, attacker);
//        return true;
//    }
//
//    @Override
//    public int getAttackDelay(ItemStack itemstack, LivingEntity entityliving, LivingEntity attacker) {
//        return meleeSpecs.attackDelay;
//    }
//
//    @Override
//    public float getKnockBack(ItemStack itemstack, LivingEntity entityliving, LivingEntity attacker) {
//        return meleeSpecs.getKnockBack(weaponMaterial);
//    }

    @Override
    public int getItemEnchantability() {
        return weaponMaterial == null ? 1 : weaponMaterial.getEnchantability();
    }

    @Override
    public void addItemAttributeModifiers(Multimap<String, AttributeModifier> multimap) {
//        float dmg = getEntityDamage();
//        if (dmg > 0F || meleeSpecs.damageMult > 0F) {
//            multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(weapon.getUUID(), "Weapon modifier", dmg, 0));
//        }
//        multimap.put(WeaponModAttributes.WEAPON_KNOCKBACK.getAttributeUnlocalizedName(), new AttributeModifier(weapon.getUUID(), "Weapon knockback modifier", meleeSpecs.getKnockBack(weaponMaterial) - 0.4F, 0));
//        multimap.put(WeaponModAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(weapon.getUUID(), "Weapon attack speed modifier", -meleeSpecs.attackDelay, 0));
//        if (this instanceof IExtendedReachItem) {
//            try {
//                multimap.put(WeaponModAttributes.WEAPON_REACH.getAttributeUnlocalizedName(), new AttributeModifier(weapon.getUUID(), "Weapon reach modifier", ((IExtendedReachItem) this).getExtendedReach(null, null, null) - 3F, 0));
//            } catch (NullPointerException e) {
//            }
//        }
    }
//
//    @Override
//    public boolean onLeftClickEntity(ItemStack itemstack, LivingEntity player, Entity entity) {
//        if (entity instanceof EntityLivingBase) {
//            PhysHelper.prepareKnockbackOnEntity(player, (EntityLivingBase) entity);
//        }
//        return false;
//    }
//
//    @Override
//    public EnumAction getItemUseAction(ItemStack itemstack) {
//        return EnumAction.block;
//    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 72000;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, PlayerEntity entityplayer) {
        //entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    @Override
    public void onUsingTick(ItemStack itemstack, PlayerEntity entityplayer, int count) {
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, PlayerEntity entityplayer, int i) {
    }

    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
    }

    //@SideOnly(Side.CLIENT)
    @Override
    public boolean shouldRotateAroundWhenRendering() {
        return false;
    }

    @Override
    public float getBlockDamage(ItemStack itemstack, Block block) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int j, int k, int l, Entity entityliving) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hitEntity(ItemStack itemstack, LivingEntity entityliving, Entity attacker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAttackDelay(ItemStack itemstack, LivingEntity entityliving, Entity attacker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getKnockBack(ItemStack itemstack, LivingEntity entityliving, Entity attacker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onLeftClickEntity(ItemStack itemstack, PlayerEntity player, Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
