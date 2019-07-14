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
import net.minecraft.item.ShootableItem;
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
public class ItemCustomFireArm extends ShootableItem {

   public static final Predicate<ItemStack> AMMUNITION_MUSKET = (stack) -> {
      return stack.getItem().isIn(makeWrapperTag("flintlock_ammo"));
   };
   private static String isLoadedTag = "charged";
   private RangedMaterial specs;

   private boolean field_220034_c = false;
   private boolean field_220035_d = false;

   public ItemCustomFireArm(RangedMaterial rangedspecs, Properties props) {
      super(props);
      this.specs = rangedspecs;
      this.addPropertyOverride(new ResourceLocation("pull"), (p_220022_1_, p_220022_2_, p_220022_3_) -> {
         if (p_220022_3_ != null && p_220022_1_.getItem() == this) {
            return isLoaded(p_220022_1_) ? 0.0F : (float)(p_220022_1_.getUseDuration() - p_220022_3_.getItemInUseCount()) / (float)25;
         } else {
            return 0.0F;
         }
      });
      this.addPropertyOverride(new ResourceLocation("pulling"), (p_220033_0_, p_220033_1_, p_220033_2_) -> {
         return p_220033_2_ != null && p_220033_2_.isHandActive() && p_220033_2_.getActiveItemStack() == p_220033_0_ && !isLoaded(p_220033_0_) ? 1.0F : 0.0F;
      });
      this.addPropertyOverride(new ResourceLocation("charged"), (p_220030_0_, p_220030_1_, p_220030_2_) -> {
         return p_220030_2_ != null && isLoaded(p_220030_0_) ? 1.0F : 0.0F;
      });
   }

   /**
    * Called to trigger the item's "innate" right click behavior. To handle when
    * this item is used on a Block, see {@link #onItemUse}.
    */
   public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
      ItemStack itemstack = playerIn.getHeldItem(handIn);

      if (isLoaded(itemstack)) {
         
         fireProjectiles(worldIn, playerIn, handIn, itemstack, 1.6F, 1.0F);
         setLoaded(itemstack, false);
         return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
      } else if (!playerIn.findAmmo(itemstack).isEmpty()) {
         if (!isLoaded(itemstack)) {
            this.field_220034_c = false;
            this.field_220035_d = false;
            playerIn.setActiveHand(handIn);
         }

         return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
      } else {
         return new ActionResult<>(ActionResultType.FAIL, itemstack);
      }
   }

   public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
      int i = this.getUseDuration(stack) - timeLeft;
      Main.LOGGER.info("i is : " + i);
      float f = func_220031_a(i, stack);
      if (f >= 1.0F) {// && hasAmmo(entityLiving, stack)) {
         Main.LOGGER.info("starting loading.");
         if (!isLoaded(stack)) {
            Main.LOGGER.info("loading weapon.");
            setLoaded(stack, true);
            SoundCategory soundcategory = entityLiving instanceof PlayerEntity ? SoundCategory.PLAYERS
                  : SoundCategory.HOSTILE;
            worldIn.playSound((PlayerEntity) null, entityLiving.posX, entityLiving.posY, entityLiving.posZ,
                  SoundEvents.ITEM_CROSSBOW_LOADING_END, soundcategory, 1.0F,
                  1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
         }
      }
   }

   private static float func_220031_a(int p_220031_0_, ItemStack p_220031_1_) {
      float f = (float) p_220031_0_ / (float) 25; // enhancement level.
      if (f > 1.0F) {
         f = 1.0F;
      }

      return f;
   }

   /**
    * Get the predicate to match ammunition when searching the player's inventory,
    * not their main/offhand THIS PART WORKS AS INTENTION.
    */
   @Override
   public Predicate<ItemStack> getInventoryAmmoPredicate() {
      // Main.LOGGER.info("Ammo type: " +specs.ammoType);
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
      return UseAction.CROSSBOW;
   }

   public static boolean isLoaded(ItemStack weapon) {
      CompoundNBT compoundnbt = weapon.getTag();
      Main.LOGGER.info("Checking loaded state");
      if(compoundnbt == null )
      {
         Main.LOGGER.info("No state defined.");
         return false;
      }
      
      Main.LOGGER.info("state : "+compoundnbt.getBoolean(isLoadedTag));
      return compoundnbt.getBoolean(isLoadedTag);
   }

   public static void setLoaded(ItemStack weapon, boolean state) {
      Main.LOGGER.info("setting loaded state :"+state);
      CompoundNBT compoundnbt = weapon.getOrCreateTag();
      compoundnbt.putBoolean(isLoadedTag, state);
   }

   public static void fireProjectiles(World p_220014_0_, LivingEntity p_220014_1_, Hand p_220014_2_,
         ItemStack p_220014_3_, float p_220014_4_, float p_220014_5_) {
            Main.LOGGER.info("Fire Projectile");
      List<ItemStack> list = Lists.newArrayList();
      list.add(ItemStack.EMPTY);
      float[] afloat = func_220028_a(p_220014_1_.getRNG());

      for (int i = 0; i < list.size(); ++i) {
         ItemStack itemstack = list.get(i);
         boolean flag = p_220014_1_ instanceof PlayerEntity && ((PlayerEntity) p_220014_1_).abilities.isCreativeMode;
         if (!itemstack.isEmpty()) {
            if (i == 0) {
               shoot(p_220014_0_, p_220014_1_, p_220014_2_, p_220014_3_, itemstack, afloat[i], flag, p_220014_4_,
                     p_220014_5_, 0.0F);
            } else if (i == 1) {
               shoot(p_220014_0_, p_220014_1_, p_220014_2_, p_220014_3_, itemstack, afloat[i], flag, p_220014_4_,
                     p_220014_5_, -10.0F);
            } else if (i == 2) {
               shoot(p_220014_0_, p_220014_1_, p_220014_2_, p_220014_3_, itemstack, afloat[i], flag, p_220014_4_,
                     p_220014_5_, 10.0F);
            }
         }
      }

      // func_220015_a(p_220014_0_, p_220014_1_, p_220014_3_);
   }

   private static float[] func_220028_a(Random p_220028_0_) {
      boolean flag = p_220028_0_.nextBoolean();
      return new float[] { 1.0F, func_220032_a(flag), func_220032_a(!flag) };
   }

   private static float func_220032_a(boolean p_220032_0_) {
      float f = p_220032_0_ ? 0.63F : 0.43F;
      return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
   }

   private static void shoot(World p_220016_0_, LivingEntity p_220016_1_, Hand p_220016_2_, ItemStack p_220016_3_,
         ItemStack p_220016_4_, float p_220016_5_, boolean p_220016_6_, float p_220016_7_, float p_220016_8_,
         float p_220016_9_) {
      if (!p_220016_0_.isRemote) {
         boolean flag = p_220016_4_.getItem() == Items.FIREWORK_ROCKET;
         IProjectile iprojectile;
         if (flag) {
            iprojectile = new FireworkRocketEntity(p_220016_0_, p_220016_4_, p_220016_1_.posX,
                  p_220016_1_.posY + (double) p_220016_1_.getEyeHeight() - (double) 0.15F, p_220016_1_.posZ, true);
         } else {
            iprojectile = createArrow(p_220016_0_, p_220016_1_, p_220016_3_, p_220016_4_);
            if (p_220016_6_ || p_220016_9_ != 0.0F) {
               ((AbstractArrowEntity) iprojectile).pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
            }
         }

         if (p_220016_1_ instanceof ICrossbowUser) {
            ICrossbowUser icrossbowuser = (ICrossbowUser) p_220016_1_;
            icrossbowuser.shoot(icrossbowuser.getAttackTarget(), p_220016_3_, iprojectile, p_220016_9_);
         } else {
            Vec3d vec3d1 = p_220016_1_.func_213286_i(1.0F);
            Quaternion quaternion = new Quaternion(new Vector3f(vec3d1), p_220016_9_, true);
            Vec3d vec3d = p_220016_1_.getLook(1.0F);
            Vector3f vector3f = new Vector3f(vec3d);
            vector3f.func_214905_a(quaternion);
            iprojectile.shoot((double) vector3f.getX(), (double) vector3f.getY(), (double) vector3f.getZ(), p_220016_7_,
                  p_220016_8_);
         }

         p_220016_3_.damageItem(flag ? 3 : 1, p_220016_1_, (p_220017_1_) -> {
            p_220017_1_.sendBreakAnimation(p_220016_2_);
         });
         p_220016_0_.addEntity((Entity) iprojectile);
         p_220016_0_.playSound((PlayerEntity) null, p_220016_1_.posX, p_220016_1_.posY, p_220016_1_.posZ,
               SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, p_220016_5_);
      }
   }

   private static AbstractArrowEntity createArrow(World p_220024_0_, LivingEntity p_220024_1_, ItemStack p_220024_2_,
         ItemStack p_220024_3_) {
      ArrowItem arrowitem = (ArrowItem) (p_220024_3_.getItem() instanceof ArrowItem ? p_220024_3_.getItem()
            : Items.ARROW);
      AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(p_220024_0_, p_220024_3_, p_220024_1_);
      if (p_220024_1_ instanceof PlayerEntity) {
         abstractarrowentity.setIsCritical(true);
      }

      abstractarrowentity.setHitSound(SoundEvents.ITEM_CROSSBOW_HIT);
      abstractarrowentity.func_213865_o(true);
      int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.PIERCING, p_220024_2_);
      if (i > 0) {
         abstractarrowentity.func_213872_b((byte) i);
      }

      return abstractarrowentity;
   }

   private static Tag<Item> makeWrapperTag(String name) {
      return new ItemTags.Wrapper(new ResourceLocation(name));
   }
}
