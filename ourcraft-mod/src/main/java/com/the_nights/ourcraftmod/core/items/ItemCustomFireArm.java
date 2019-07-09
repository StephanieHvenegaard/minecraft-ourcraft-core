/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraftmod.core.items;

import afu.org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Lists;
import com.the_nights.ourcraftmod.core.items.components.RangedSpecs;
import com.the_nights.ourcraftmod.core.lists.items.IronItems;
import java.util.List;
import java.util.Random;

import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
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
      return stack.getItem().isIn(ItemTags.ARROWS);
   };

   private boolean isLoaded = false;
   private RangedSpecs specs;

   public ItemCustomFireArm(RangedSpecs rangedspecs, Properties props) {
      super(props);
      this.specs = rangedspecs;

      this.addPropertyOverride(new ResourceLocation("pull"), (stack, world, player) -> {
         if (player == null) {
            return 0.0F;
         } else {
            return !(player.getActiveItemStack().getItem() instanceof ItemCustomFireArm) ? 0.0F : (float)(stack.getUseDuration() - player.getItemInUseCount()) / 20.0F;
         }
      });
      this.addPropertyOverride(new ResourceLocation("pulling"), (itemstack, world, player) -> {
         return player != null && player.isHandActive() && player.getActiveItemStack() == itemstack ? 1.0F : 0.0F;
      });
      
   }
      
   /**
    * How long it takes to use or consume an item
    */
    @Override
    public int getUseDuration(ItemStack stack) {
      return this.specs.reloadTime;
   }
}
