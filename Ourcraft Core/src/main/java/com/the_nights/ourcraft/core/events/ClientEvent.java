package com.the_nights.ourcraft.core.events;

import com.the_nights.ourcraft.core.OurcraftCore;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.InputEvent.MouseInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber
public class ClientEvent {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(receiveCanceled = true)
    public static void onMouseEvent(MouseInputEvent ev) {
        OurcraftCore.LOGGER.info("GOOOT EVENT !!!! ");
    }
}
