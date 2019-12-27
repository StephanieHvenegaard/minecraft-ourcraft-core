package com.the_nights.ourcraft_core.core;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class CoreConfig {
/*
        protected static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

        protected static final ForgeConfigSpec SPEC = BUILDER.build();

        public static final Logs logs = new Logs();
        public static final Leaves leaves = new Leaves();
        public static final Axes axes = new Axes();
        public static final Options options = new Options();


        public static class Logs
        {
            public final ForgeConfigSpec.ConfigValue<List<? extends String>> logBlocks;
            public final ForgeConfigSpec.ConfigValue<Boolean> logTag;
            private ArrayList<String> logsDef = Lists.newArrayList();


            Logs() {
                CoreConfig.BUILDER.push("logs");
                this.logTag = (ForgeConfigSpec.ConfigValue<Boolean>) CoreConfig.BUILDER.comment("Add the contents of the Tag minecraft:logs as valid Logs").define("Use Logs Tag", true);
                this.logBlocks = CoreConfig.BUILDER.comment("Add the registry name of blocks here that should count as Logs").defineList("Log Blocks", this.logsDef, entry -> entry instanceof String);
                CoreConfig.BUILDER.pop();
            }
        }


        public static class Leaves
        {
            public final ForgeConfigSpec.ConfigValue<List<? extends String>> leaves;
            public final ForgeConfigSpec.ConfigValue<Boolean> leavesTag;
            private ArrayList<String> leavesDef = Lists.newArrayList();


            Leaves() {
                CoreConfig.BUILDER.push("leaves");
                this.leavesTag = (ForgeConfigSpec.ConfigValue<Boolean>) CoreConfig.BUILDER.comment("Add the content of the Tag minecraft:leaves as valid Leaves").define("Use Leaves Tag", true);
                this.leaves = CoreConfig.BUILDER.comment("Add the registry name of blocks here that should count as Leaves").defineList("Leaves Blocks", this.leavesDef, entry -> entry instanceof String);
                CoreConfig.BUILDER.pop();
            }
        }


        public static class Axes
        {
            public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistAxe;
            private ArrayList<String> blacklistAxeDef = Lists.newArrayList();


            Axes() {
                CoreConfig.BUILDER.push("axes");
                this.blacklistAxe = CoreConfig.BUILDER.comment(new String[] { "Any Axe added to this list will not work with Tree Choppin", "One Entry Per line, no Commas" }).defineList("Axe Blacklist", this.blacklistAxeDef, entry -> entry instanceof String);
                CoreConfig.BUILDER.pop();
            }
        }


        public static class Options
        {
            public final ForgeConfigSpec.BooleanValue disableShift;
            public final ForgeConfigSpec.BooleanValue reverseShift;
            public final ForgeConfigSpec.BooleanValue decayLeaves;

            Options() {
                CoreConfig.BUILDER.push("options");
                this.disableShift = CoreConfig.BUILDER.comment("Ignore Sneaking when chopping trees").define("disableShift", false);
                this.reverseShift = CoreConfig.BUILDER.comment("Only chop down trees when sneaking").define("reverseShift", true);
                this.decayLeaves = CoreConfig.BUILDER.comment("Cut down leaves and logs").define("decayLeaves", true);
                CoreConfig.BUILDER.pop();
            }
        }

 */


}
