/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.items.goldendiamond;
import com.sn.core.UMCMain;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
/**
 *
 * @author Stephanie
 */
public class GDArmor extends ItemArmor{

    
    public GDArmor(ArmorMaterial armorMaterial, int renderIndex, int armorType) {
        super(armorMaterial, renderIndex, armorType);
        
    }
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity,int slot, String type){
        int layer = 0;
        if(this.armorType == 2)
            layer = 2;
        else
            layer = 1;
        return UMCMain.MODID+":textures/armor/"+GoldenDiamondItems.matType+"_layer_"+layer+".png";        
    }
    
}
