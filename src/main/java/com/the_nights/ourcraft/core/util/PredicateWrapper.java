package com.the_nights.ourcraft.core.util;

import java.util.function.Predicate;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class PredicateWrapper {

    public static Predicate<ItemStack> createPredicate(String name) {
        return ((stack) -> {
            return stack.getItem().isIn(makeWrapperTag(name));
        });
    }

    private static Tag<Item> makeWrapperTag(String name) {
        return new ItemTags.Wrapper(new ResourceLocation(name));
    }
}