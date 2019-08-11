/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraft.core.items;

import com.google.common.collect.Lists;
import com.the_nights.ourcraft.core.items.materials.RangedMaterial;
import com.the_nights.ourcraft.core.OurcraftCore;
import com.the_nights.ourcraft.core.items.parts.FirearmPart;
import com.the_nights.ourcraft.core.util.PredicateWrapper;

import it.unimi.dsi.fastutil.Stack;

import java.util.List;
import java.util.Random;

import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.NBTTextComponent;
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
public class ItemCustomFireArm extends CrossbowItem {

   public static final Predicate<ItemStack> AMMUNITION_MUSKET = PredicateWrapper.createPredicate("flintlock_ammo");

//   private FirearmPart firearmPart;
//
//   private static String isLoadedTag = "charged";
   // private RangedMaterial specs;

   public ItemCustomFireArm(RangedMaterial rangedspecs, Properties props) {
      super(props.maxStackSize(1));
/*       firearmPart = new FirearmPart(rangedspecs);
      this.addPropertyOverride(new ResourceLocation("pull"), (p_220022_1_, p_220022_2_, p_220022_3_) -> {
         if (p_220022_3_ != null && p_220022_1_.getItem() == this) {
            return isLoaded(p_220022_1_) ? 0.0F
                  : (float) (p_220022_1_.getUseDuration() - p_220022_3_.getItemInUseCount())
                        / (float) getChargeTime(p_220022_1_);
         } else {
            return 0.0F;
         }
      });
      this.addPropertyOverride(new ResourceLocation("pulling"), (p_220033_0_, p_220033_1_, p_220033_2_) -> {
         return p_220033_2_ != null && p_220033_2_.isHandActive() && p_220033_2_.getActiveItemStack() == p_220033_0_
               && !isLoaded(p_220033_0_) ? 1.0F : 0.0F;
      });
      this.addPropertyOverride(new ResourceLocation("charged"), (p_220030_0_, p_220030_1_, p_220030_2_) -> {
         return p_220030_2_ != null && isLoaded(p_220030_0_) ? 1.0F : 0.0F;
      }); */
   }
/* 
   @OnlyIn(Dist.CLIENT)
   @Override
   public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
         ITooltipFlag flagIn) {
      List<ITextComponent> list1 = Lists.newArrayList();
      RangedMaterial specs = firearmPart.getSpecs();
      list1.add(new StringTextComponent(""));
      list1.add(new StringTextComponent("When in hands:").applyTextStyle(TextFormatting.GRAY));
      if (isLoaded(stack)) {
         list1.add(new StringTextComponent(" Loaded state : Loaded").applyTextStyle(TextFormatting.DARK_GREEN));
      } else {
         list1.add(new StringTextComponent(" Loaded state : Unloaded").applyTextStyle(TextFormatting.DARK_GREEN));
      }
      list1.add(new StringTextComponent(" " + specs.magazinCapasity + "  Magazin Capasity")
            .applyTextStyle(TextFormatting.DARK_GREEN));
      list1.add(new StringTextComponent(" " + specs.reloadTime + "  ReloadSpeed")
            .applyTextStyle(TextFormatting.DARK_GREEN));
      list1.add(new StringTextComponent(
            " " + specs.ammoType.projectilesPerBullet + " X " + specs.ammoType.dmg + "  Attack Damage")
                  .applyTextStyle(TextFormatting.DARK_GREEN));

      tooltip.addAll(list1);
   } */
}

//
// /**
// * Called to trigger the item's "innate" right click behavior. To handle
// * when this item is used on a Block, see {@link #onItemUse}.
// */
// @Override
// public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity
// playerIn, Hand handIn) {
// ItemStack itemstack = playerIn.getHeldItem(handIn);
// OurcraftCore.LOGGER.info("RigthClicking.");
// if (isLoaded(itemstack)) {
// fireProjectiles(worldIn, playerIn, handIn, itemstack, 1.6F, 1.0F);
// setLoaded(itemstack, false);
// return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
// } else if (!playerIn.findAmmo(itemstack).isEmpty()) {
// if (!isLoaded(itemstack)) {
// playerIn.setActiveHand(handIn);
// }
// return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
// } else {
// return new ActionResult<>(ActionResultType.FAIL, itemstack);
// }
// }
//
// /**
// * event fires then rigthclick is released.
// */
// @Override
// public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity
// entityLiving, int timeLeft) {
// int i = this.getUseDuration(stack) - timeLeft;
// OurcraftCore.LOGGER.info("Time left is : " + timeLeft);
// float f = getCharge(i, stack);
//
// boolean hasAmmo =hasAmmo(entityLiving, stack);
// OurcraftCore.LOGGER.info("F Is : " + f+ " has ammo ? "+ hasAmmo);
// if (f >= 1.0F && hasAmmo ) {
// OurcraftCore.LOGGER.info("starting loading.");
// if (!isLoaded(stack)) {
// OurcraftCore.LOGGER.info("loading weapon.");
// setLoaded(stack, true);
// SoundCategory soundcategory = entityLiving instanceof PlayerEntity ?
// SoundCategory.PLAYERS
// : SoundCategory.HOSTILE;
// worldIn.playSound((PlayerEntity) null, entityLiving.posX, entityLiving.posY,
// entityLiving.posZ,
// SoundEvents.ITEM_CROSSBOW_LOADING_END, soundcategory, 1.0F,
// 1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
// }
// }
// }
//
// private static boolean hasAmmo(LivingEntity entityIn, ItemStack stack) {
// int i = 0;// EnchantmentHelper.getEnchantmentLevel(Enchantments.MULTISHOT,
// stack);
// int j = i == 0 ? 1 : 3;
// boolean flagIsCreative = entityIn instanceof PlayerEntity && ((PlayerEntity)
// entityIn).abilities.isCreativeMode;
// ItemStack itemstack = entityIn.findAmmo(stack);
// ItemStack itemstack1 = itemstack.copy();
//
// for (int k = 0; k < j; ++k) {
// if (k > 0) {
// itemstack = itemstack1.copy();
// }
//
// if (itemstack.isEmpty() && flagIsCreative) {
// itemstack = new ItemStack(Items.ARROW);
// itemstack1 = itemstack.copy();
// }
//
// if (!func_220023_a(entityIn, stack, itemstack, k > 0, flagIsCreative)) {
// return false;
// }
// }
//
// return true;
// }
//
// private static boolean func_220023_a(LivingEntity p_220023_0_, ItemStack
// p_220023_1_, ItemStack p_220023_2_, boolean p_220023_3_, boolean p_220023_4_)
// {
// if (p_220023_2_.isEmpty()) {
// return false;
// } else {
// boolean flag = p_220023_4_ && p_220023_2_.getItem() instanceof ArrowItem;
// ItemStack itemstack;
// if (!flag && !p_220023_4_ && !p_220023_3_) {
// itemstack = p_220023_2_.split(1);
// if (p_220023_2_.isEmpty() && p_220023_0_ instanceof PlayerEntity) {
// ((PlayerEntity) p_220023_0_).inventory.deleteStack(p_220023_2_);
// }
// } else {
// itemstack = p_220023_2_.copy();
// }
//
// addChargedProjectile(p_220023_1_, itemstack);
// return true;
// }
// }
//
// private static void addChargedProjectile(ItemStack crossbow, ItemStack
// projectile) {
// CompoundNBT compoundnbt = crossbow.getOrCreateTag();
// ListNBT listnbt;
// if (compoundnbt.contains("ChargedProjectiles", 9)) {
// listnbt = compoundnbt.getList("ChargedProjectiles", 10);
// } else {
// listnbt = new ListNBT();
// }
//
// CompoundNBT compoundnbt1 = new CompoundNBT();
// projectile.write(compoundnbt1);
// listnbt.add(compoundnbt1);
// compoundnbt.put("ChargedProjectiles", listnbt);
// }
//
// private static float getCharge(int useTime, ItemStack stack) {
// float f = (float) useTime / (float) getChargeTime(stack);
// if (f > 1.0F) {
// f = 1.0F;
// }
//
// return f;
// }
//
// /**
// * The time the crossbow must be used to reload it
// */
// public static int getChargeTime(ItemStack stack) {
// //int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.QUICK_CHARGE,
// stack);
// int reloadtime = 25;
// if (stack.getItem() instanceof ItemCustomFireArm) {
// ItemCustomFireArm firearm = (ItemCustomFireArm) stack.getItem();
// reloadtime = firearm.firearmPart.getSpecs().reloadTime;
// }
//
// return reloadtime;
// //return i == 0 ? realoadspeed : (realoadspeed - (5 * i));
// }
//
// /**
// * Get the predicate to match ammunition when searching the player's
// * inventory, not their main/offhand THIS PART WORKS AS INTENTION.
// */
// @Override
// public Predicate<ItemStack> getInventoryAmmoPredicate() {
//// OurcraftCore.LOGGER.info(firearmPart.getSpecs().ammoType);
//// switch (firearmPart.getSpecs().ammoType) {
//// case FLINT_LOCK_MUSKET_AMMO:
//// OurcraftCore.LOGGER.info("found ammo");
//// return AMMUNITION_MUSKET;
//// default:
// OurcraftCore.LOGGER.info("cant find ammo, using default ammo");
// return ARROWS;
//// }
// }
//
// /**
// * get weapons ammo.
// */
// @Override
// public Predicate<ItemStack> getAmmoPredicate() {
// return getInventoryAmmoPredicate();
// }
//
// /**
// * How long it takes to use or consume an item
// */
// @Override
// public int getUseDuration(ItemStack stack) {
// return this.firearmPart.getSpecs().reloadTime;
// }
//
// /**
// * returns the action that specifies what animation to play when the items
// * is being used
// */
// @Override
// public UseAction getUseAction(ItemStack stack) {
// return UseAction.CROSSBOW;
// }
//
// public static boolean isLoaded(ItemStack weapon) {
// CompoundNBT compoundnbt = weapon.getTag();
// //OurcraftCore.LOGGER.info("Checking loaded state");
// if (compoundnbt == null) {
// OurcraftCore.LOGGER.info("No state defined.");
// return false;
// }
// //OurcraftCore.LOGGER.info("state : " + compoundnbt.getBoolean(isLoadedTag));
// return compoundnbt.getBoolean(isLoadedTag);
// }
//
// public static void setLoaded(ItemStack weapon, boolean state) {
// OurcraftCore.LOGGER.info("setting loaded state :" + state);
// CompoundNBT compoundnbt = weapon.getOrCreateTag();
// compoundnbt.putBoolean(isLoadedTag, state);
// }
//
// public static void fireProjectiles(World world, LivingEntity livingentity,
// Hand hand, ItemStack weapon,
// float p_220014_4_, float p_220014_5_) {
// int projectiles = 1;
// float spread = 0.0f;
// if (weapon.getItem() instanceof ItemCustomFireArm) {
// ItemCustomFireArm firearm = (ItemCustomFireArm) weapon.getItem();
// projectiles = firearm.firearmPart.getSpecs().ammoType.projectilesPerBullet;
// spread = firearm.firearmPart.getSpecs().spread;
// }
// boolean flag = livingentity instanceof PlayerEntity && ((PlayerEntity)
// livingentity).abilities.isCreativeMode;
// for (int i = 0; i < projectiles; ++i) {
// ItemStack itemstack = new ItemStack(Items.ARROW);
// if (!itemstack.isEmpty()) {
// if (i == 0) {
// shoot(world, livingentity, hand, weapon, itemstack, flag, p_220014_4_,
// p_220014_5_, 0.0F);
// } else {
// spread = (0.1f * spread) + (random.nextFloat() * spread); // calculates
// Spread of shotgun type
// // weapons
// if (random.nextFloat() >= 0.5f) {
// spread = spread * -1.0f;
// }
// shoot(world, livingentity, hand, weapon, itemstack, flag, p_220014_4_,
// p_220014_5_, spread);
// }
// }
// }
// SoundCategory soundcategory = livingentity instanceof PlayerEntity ?
// SoundCategory.PLAYERS
// : SoundCategory.HOSTILE;
// world.playSound((PlayerEntity) null, livingentity.posX, livingentity.posY,
// livingentity.posZ,
// SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, soundcategory, 5.0F,
// 1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
// world.playSound((PlayerEntity) null, livingentity.posX, livingentity.posY,
// livingentity.posZ,
// SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, soundcategory, 5.0F,
// 1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
// }
//
// private static void shoot(World p_220016_0_, LivingEntity p_220016_1_, Hand
// p_220016_2_, ItemStack p_220016_3_,
// ItemStack p_220016_4_, boolean p_220016_6_, float Velocity, float
// p_220016_8_,
// float p_220016_9_) {
// if (!p_220016_0_.isRemote) {
//
// IProjectile iprojectile;
// iprojectile = createBullet(p_220016_0_, p_220016_1_, p_220016_3_,
// p_220016_4_);
// if (p_220016_6_ || p_220016_9_ != 0.0F) {
// ((AbstractArrowEntity) iprojectile).pickupStatus =
// AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
// }
// float velocityMod = 1.0f;
// if (p_220016_3_.getItem() instanceof ItemCustomFireArm) {
// ItemCustomFireArm firearm = (ItemCustomFireArm) p_220016_3_.getItem();
// velocityMod = firearm.firearmPart.getSpecs().projectileVelocity;
// }
// Vec3d vec3d1 = p_220016_1_.func_213286_i(1.0F);
// Quaternion quaternion = new Quaternion(new Vector3f(vec3d1), p_220016_9_,
// true);
// Vec3d vec3d = p_220016_1_.getLook(1.0F);
// Vector3f vector3f = new Vector3f(vec3d);
// vector3f.func_214905_a(quaternion);
// iprojectile.shoot((double) vector3f.getX(), (double) vector3f.getY(),
// (double) vector3f.getZ(), velocityMod,
// p_220016_8_);
// p_220016_3_.damageItem(1, p_220016_1_, (p_220017_1_) -> {
// p_220017_1_.sendBreakAnimation(p_220016_2_);
// });
// p_220016_0_.addEntity((Entity) iprojectile);
// }
// }
//
// private static AbstractArrowEntity createBullet(World p_220024_0_,
// LivingEntity p_220024_1_, ItemStack p_220024_2_,
// ItemStack p_220024_3_) {
// ArrowItem arrowitem = (ArrowItem) (p_220024_3_.getItem() instanceof ArrowItem
// ? p_220024_3_.getItem()
// : Items.ARROW);
// AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(p_220024_0_,
// p_220024_3_, p_220024_1_);
// if (p_220024_1_ instanceof PlayerEntity) {
// abstractarrowentity.setIsCritical(true);
// }
//
// abstractarrowentity.setHitSound(SoundEvents.ITEM_CROSSBOW_HIT);
// abstractarrowentity.func_213865_o(true);
// // int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.PIERCING,
// // p_220024_2_);
// // if (i > 0) {
// // abstractarrowentity.func_213872_b((byte) i);
// // }
//
// return abstractarrowentity;
// }
// }
