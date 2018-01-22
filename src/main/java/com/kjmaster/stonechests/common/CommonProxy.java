package com.kjmaster.stonechests.common;

import com.kjmaster.stonechests.common.blocks.tile.TileStoneChest;
import net.minecraft.world.World;

public class CommonProxy {

    public <T extends TileStoneChest> void registerTileEntitySpecialRenderer(Class<T> type) {

    }

    public void preInit() {}

    public World getClientWorld() {
        return null;
    }
}
