package com.the_nights.ourcraft.core.item.weapons;

import net.minecraft.item.TridentItem;
import net.minecraft.util.ResourceLocation;

public class ItemSpear extends TridentItem {
    public ItemSpear(Properties p_i48788_1_) {
        super(p_i48788_1_);
        this.addPropertyOverride(new ResourceLocation("throwing"), (p_210315_0_, p_210315_1_, p_210315_2_) -> {
            return p_210315_2_ != null && p_210315_2_.isHandActive() && p_210315_2_.getActiveItemStack() == p_210315_0_ ? 1.0F : 0.0F;
        });
    }
}
