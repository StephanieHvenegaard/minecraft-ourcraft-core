///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.the_nights.ourcraftmod.core.items.components;
//
//import com.the_nights.ourcraftmod.core.Materials.ToolMaterial;
//
///**
// *
// * @author Stephanie
// */
//public enum MeleeSpecs {
//    //NAME db, dm, edb, edm, bd, kb, dfe, dfb, mss, ad
//    SPEAR(0, 1F, 3, 1F, 1F, 0.2F, 1, 2, 1, 0),
//    HALBERD(0, 1F, 4, 1F, 1.5F, 0.6F, 1, 2, 1, 8),
//    BATTLEAXE(0, 1F, 3, 1F, 1.5F, 0.5F, 1, 2, 1, 5),
//    WARHAMMER(0, 1F, 4, 1F, 1F, 0.7F, 1, 2, 1, 5),
//    KNIFE(0, 0.5F, 3, 1F, 1.5F, 0.2F, 1, 2, 1, 0),
//    KATANA(0, 1F, 1, 1F, 1F, 0F, 1, 2, 1, -6),
//    FIREROD(1, 0F, 1, 0F, 1F, 0.4F, 2, 0, 1, 0),
//    BOOMERANG(0, 0.5F, 2, 1F, 1F, 0.4F, 1, 1, 1, 0),
//    NONE(0, 0F, 1, 0F, 1F, 0.4F, 0, 0, 1, 0);
//
//    private MeleeSpecs(int durbase, float durmult, float dmgbase, float dmgmult, float blockdmg, float knockback, int dmgfromentity, int dmgfromblock, int stacksize, int attackdelay) {
//        durabilityBase = durbase;
//        durabilityMult = durmult;
//
//        damageBase = dmgbase;
//        damageMult = dmgmult;
//        blockDamage = blockdmg;
//        this.knockback = knockback;
//
//        dmgFromEntity = dmgfromentity;
//        dmgFromBlock = dmgfromblock;
//
//        stackSize = stacksize;
//        attackDelay = attackdelay;
//    }
//
//    public float getKnockBack(ToolMaterial material) {
//        return material == ToolMaterial.Golden ? knockback * 1.5F : knockback;
//    }
//
//    public final int durabilityBase;
//    public final float durabilityMult;
//
//    public final float damageBase, damageMult, blockDamage, knockback;
//
//    public final int dmgFromEntity, dmgFromBlock;
//
//    public final int stackSize, attackDelay;
//}
