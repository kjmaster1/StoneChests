package net.kjmaster.stonechests.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.client.render.BlockEntityRendererRegistry;
import net.kjmaster.stonechests.block.entity.TileStoneChest;

public class ClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(TileStoneChest.class, new TileEntityStoneChestRenderer());
    }
}
