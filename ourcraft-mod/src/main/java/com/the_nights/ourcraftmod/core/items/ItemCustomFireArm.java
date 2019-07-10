/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.items;

import afu.org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Lists;
import com.the_nights.ourcraftmod.core.items.materials.RangedMaterial;
import com.the_nights.ourcraftmod.core.Main;
import com.the_nights.ourcraftmod.core.lists.items.IronItems;
import com.the_nights.ourcraftmod.core.lists.items.MiscItems;

import java.util.List;
import java.util.Random;

import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import java.util.function.Predicate;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ICrossbowUser;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FireworkRocketEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 *
 * @author Stephanie
 */
public class ItemCustomFireArm extends BowItem {

   public static final Predicate<ItemStack> AMMUNITION_MUSKET = (stack) -> {
      return stack.getItem().isIn(makeWrapperTag("flintlock_ammo"));
   };
   private static String isLoadedTag = "Loaded";
   private RangedMaterial specs;

   public ItemCustomFireArm(RangedMaterial rangedspecs, Properties props) {
      super(props);
      this.specs = rangedspecs;

      this.addPropertyOverride(new ResourceLocation("pull"), (stack, world, player) -> {
         if (player == null) {
            return 0.0F;
         } else {
            return !(player.getActiveItemStack().getItem() instanceof ItemCustomFireArm) ? 0.0F
                  : (float) (stack.getUseDuration() - player.getItemInUseCount()) / 20.0F;
         }
      });
      this.addPropertyOverride(new ResourceLocation("pulling"), (itemstack, world, player) -> {
         return player != null && player.isHandActive() && player.getActiveItemStack() == itemstack ? 1.0F : 0.0F;
      });
   }
   public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
      if (entityLiving instanceof PlayerEntity) {
         PlayerEntity playerentity = (PlayerEntity)entityLiving;
         boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
         ItemStack itemstack = playerentity.findAmmo(stack);

         int i = this.getUseDuration(stack) - timeLeft;
         i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
         if (i < 0) return;

         if (!itemstack.isEmpty() || flag) {
            if (itemstack.isEmpty()) {
               itemstack = new ItemStack(Items.ARROW);
            }

            float f = getArrowVelocity(i);
            if (!((double)f < 0.1D)) {
               boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
               if (!worldIn.isRemote) {
                  ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                  AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                  abstractarrowentity = customeArrow(abstractarrowentity);
                  abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                  if (f == 1.0F) {
                     abstractarrowentity.setIsCritical(true);
                  }

                  int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                  if (j > 0) {
                     abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double)j * 0.5D + 0.5D);
                  }

                  int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                  if (k > 0) {
                     abstractarrowentity.setKnockbackStrength(k);
                  }

                  if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                     abstractarrowentity.setFire(100);
                  }

                  stack.damageItem(1, playerentity, (p_220009_1_) -> {
                     p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                  });
                  if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                     abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                  }

                  worldIn.addEntity(abstractarrowentity);
               }

               worldIn.playSound((PlayerEntity)null, playerentity.posX, playerentity.posY, playerentity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
               if (!flag1 && !playerentity.abilities.isCreativeMode) {
                  itemstack.shrink(1);
                  if (itemstack.isEmpty()) {
                     playerentity.inventory.deleteStack(itemstack);
                  }
               }

               playerentity.addStat(Stats.ITEM_USED.get(this));
            }
         }
      }
   }




   /**
    * Get the predicate to match ammunition when searching the player's inventory,
    * not their main/offhand
    * THIS PART WORKS AS INTENTION.
    */
   @Override
   public Predicate<ItemStack> getInventoryAmmoPredicate() {
      //Main.LOGGER.info("Ammo type: " +specs.ammoType);
      switch (specs.ammoType) {
      case FLINT_LOCK_AMMO:
         Main.LOGGER.info("found ammo");
         return AMMUNITION_MUSKET;
      default:
         Main.LOGGER.info("default ammo");
         return ARROWS;
      }
   }
   /** 
    * get weapons ammo.
    */
   @Override
   public Predicate<ItemStack> getAmmoPredicate() {
      return getInventoryAmmoPredicate();
   }

   /**
    * Called to trigger the item's "innate" right click behavior. To handle when
    * this item is used on a Block, see {@link #onItemUse}.
    */
   public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
      ItemStack itemstack = playerIn.getHeldItem(handIn);

      boolean flag = !playerIn.findAmmo(itemstack).isEmpty();
      Main.LOGGER.info("flag : "+ flag);
      ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn,
            handIn, flag);
      if (ret != null)
         return ret;

      if (!playerIn.abilities.isCreativeMode && !flag) {
         return flag ? new ActionResult<>(ActionResultType.PASS, itemstack)
               : new ActionResult<>(ActionResultType.FAIL, itemstack);
      } else {
         playerIn.setActiveHand(handIn);
         return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
      }
   }

   /**
    * How long it takes to use or consume an item
    */
   @Override
   public int getUseDuration(ItemStack stack) {
      return this.specs.reloadTime;
   }

   /**
    * returns the action that specifies what animation to play when the items is
    * being used
    */
   @Override
   public UseAction getUseAction(ItemStack stack) {
      return UseAction.BOW;
   }
   public static boolean isLoaded(ItemStack weapon) {
      CompoundNBT compoundnbt = weapon.getTag();
      return compoundnbt != null && compoundnbt.getBoolean(isLoadedTag);
   }

   public static void setLoaded(ItemStack weapon, boolean state) {
      CompoundNBT compoundnbt = weapon.getOrCreateTag();
      compoundnbt.putBoolean(isLoadedTag, state);
   }

   private static Tag<Item> makeWrapperTag(String name) {
      return new ItemTags.Wrapper(new ResourceLocation(name));
   }
}
