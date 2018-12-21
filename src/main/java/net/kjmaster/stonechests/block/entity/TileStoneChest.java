package net.kjmaster.stonechests.block.entity;

import net.kjmaster.stonechests.ModBlocks;
import net.kjmaster.stonechests.block.StoneChestType;
import net.minecraft.block.entity.ChestBlockEntity;

public class TileStoneChest extends ChestBlockEntity {

    private final StoneChestType stoneChestType;

    public TileStoneChest()
    {
        this(StoneChestType.STONE);
    }

    public TileStoneChest(StoneChestType type)
    {
        super(ModBlocks.TILE_STONE_CHEST);
        this.stoneChestType = type;
    }

    public StoneChestType getStoneChestType() {
        return stoneChestType;
    }
}
