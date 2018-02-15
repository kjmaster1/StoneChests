package com.kjmaster.stonechests.common;

import com.kjmaster.stonechests.client.GuiStoneChest;
import com.kjmaster.stonechests.common.blocks.tile.TileStoneChest;
import com.kjmaster.stonechests.common.blocks.tile.container.ContainerStoneChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class ModGuiHandler implements IGuiHandler {

    public static final int chestID = 0;

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

        if (te != null && te instanceof TileStoneChest) {
            TileStoneChest tileStoneChest = (TileStoneChest) te;
            return new GuiStoneChest(tileStoneChest.getType(), player.inventory, tileStoneChest);
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

        if (te != null && te instanceof TileStoneChest) {
            TileStoneChest tileStoneChest = (TileStoneChest) te;

            return new ContainerStoneChest(player.inventory, tileStoneChest, tileStoneChest.getType());
        } else {
            return null;
        }
    }
}
