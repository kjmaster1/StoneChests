package com.kjmaster.stonechests;

import com.kjmaster.stonechests.common.CommonProxy;
import com.kjmaster.stonechests.common.ModGuiHandler;
import com.kjmaster.stonechests.common.util.MissingMappingsHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
@Mod(modid = StoneChests.MODID, version = StoneChests.VERSION, name = StoneChests.NAME, dependencies = "required-after:kjlib")
public class StoneChests
{
    @SidedProxy(clientSide = "com.kjmaster.stonechests.client.ClientProxy", serverSide = "com.kjmaster.stonechests.common.CommonProxy")
    public static CommonProxy proxy;
    public static final String MODID = "stonechests";
    public static final String NAME = "Stone Chests";
    public static final String VERSION = "1.0.0";

    @Mod.Instance
    public static StoneChests instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new ModGuiHandler());
        MinecraftForge.EVENT_BUS.register(new MissingMappingsHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
