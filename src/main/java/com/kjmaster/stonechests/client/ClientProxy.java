package com.kjmaster.stonechests.client;

import com.kjmaster.stonechests.common.CommonProxy;
import com.kjmaster.stonechests.common.blocks.StoneChestType;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        for (StoneChestType type : StoneChestType.values()) {
            ClientRegistry.bindTileEntitySpecialRenderer(type.clazz, new TileEntityStoneChestRenderer());
        }
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getMinecraft().world;
    }
}
