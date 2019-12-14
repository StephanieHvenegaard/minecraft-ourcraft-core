package com.the_nights.ourcraft.armory;


import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class Configs {


    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    public static class ClientConfig {

        public static ForgeConfigSpec.BooleanValue alwaysShowArmorBar;
        public static ForgeConfigSpec.BooleanValue showEmptyArmorIcons;
        public static ForgeConfigSpec.BooleanValue offset;
        public static ForgeConfigSpec.ConfigValue<List<? extends String>> colorValues;

        ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.push("general");
            colorValues = builder
                     .comment("Colors must be specified in #RRGGBB format")
                    .translation("text.overpoweredarmorbar.config.colorvalue")
                   .defineList("color values", Lists.newArrayList("#FFFFFF", "#FF5500", "#FFC747", "#27FFE3", "#00FF00", "#7F00FF"), o -> o instanceof String);
            alwaysShowArmorBar = builder
                    .comment("Always show armor bar even if empty?")
                    .translation("text.overpoweredarmorbar.config.alwaysshowarmorbar")
                    .define("Always show bar", false);
            showEmptyArmorIcons = builder
                    .comment("Show empty armor icons?")
                    .translation("text.overpoweredarmorbar.config.showemptyarmoricons")
                    .define("Show empty icons", false);
            offset = builder
                    .comment("offset armor?")
                    .translation("text.overpoweredarmorbar.config.offset")
                    .define("offset", false);
            builder.pop();
        }
    }
    public static boolean alwaysShowArmorBar = false;
    public static boolean showEmptyArmorIcons = false;
    public static List<? extends String> colorValues;
    public static boolean offset = false;
    public static void bake(){
        alwaysShowArmorBar = ClientConfig.alwaysShowArmorBar.get();
        showEmptyArmorIcons = ClientConfig.showEmptyArmorIcons.get();
        colorValues = ClientConfig.colorValues.get();
        offset = ClientConfig.offset.get();
    }
}