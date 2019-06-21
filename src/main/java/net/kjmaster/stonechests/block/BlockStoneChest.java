package net.kjmaster.stonechests.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.kjmaster.stonechests.block.entity.TileStoneChest;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.tag.ItemTags;
import net.minecraft.world.BlockView;

public class BlockStoneChest extends ChestBlock {

    private String name;

    public BlockStoneChest(String name) {
        super(FabricBlockSettings.of(Material.STONE).hardness(3.0f).resistance(30.0f).breakByTool(ItemTags.STONE_BRICKS, 1).build());
        this.name = name;
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new TileStoneChest(StoneChestType.getFromName(name));
    }

    public String getName() {
        return name;
    }
}
