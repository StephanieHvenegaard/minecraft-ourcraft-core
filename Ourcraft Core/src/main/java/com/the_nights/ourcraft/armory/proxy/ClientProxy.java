package com.the_nights.ourcraft.armory.proxy;

import com.the_nights.ourcraft.armory.overlay.OverlayEventHandler;
import com.the_nights.ourcraft.core.OurcraftCore;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static net.minecraftforge.api.distmarker.Dist.CLIENT;

@Mod.EventBusSubscriber(CLIENT)
public class ClientProxy extends CommonProxy
{
  public static OverlayEventHandler handler = new OverlayEventHandler();

    @Override
    public void postInit(FMLCommonSetupEvent event)
    {
        super.postInit(event);

        //Register Armor Renderer for events

        MinecraftForge.EVENT_BUS.register(handler);

        //Register event for configuration change
        OurcraftCore.ConfigChange eventConfigChanged = new OurcraftCore.ConfigChange();
        MinecraftForge.EVENT_BUS.register(eventConfigChanged);
    }

  @Override
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        new ConfigChangedEvent.OnConfigChangedEvent(OurcraftCore.MODID,"stuff", false, false);
//        armorBarRenderer.forceUpdate();
    }


}