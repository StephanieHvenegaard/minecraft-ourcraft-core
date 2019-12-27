/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.the_nights.ourcraft_core.lists.items.tags;

import com.the_nights.ourcraft_core.core.OurcraftCore;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

/**
 * @author Stephanie
 */
public class TagBase {

    private static Tag<Item> makeWrapperTag(String stack) {
        return new net.minecraft.tags.ItemTags.Wrapper(new ResourceLocation(OurcraftCore.MODID, stack));
    }
}
