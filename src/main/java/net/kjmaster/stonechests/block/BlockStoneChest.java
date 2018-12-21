package net.kjmaster.stonechests.block;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.kjmaster.stonechests.StoneChests;
import net.kjmaster.stonechests.block.entity.TileStoneChest;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class BlockStoneChest extends ChestBlock {

    private String name;

    public BlockStoneChest(String name) {
        super(FabricBlockSettings.of(Material.STONE).hardness(3.0f).resistance(30.0f).breakByTool(ItemTags.STONE_BRICKS, 1).build());
        this.name = name;
        Registry.BLOCK.register(new Identifier(StoneChests.MODID, name), this);
        Registry.ITEM.register(new Identifier(StoneChests.MODID, name), new BlockItem(this, new Item.Settings().stackSize(64).itemGroup(ItemGroup.DECORATIONS)));
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new TileStoneChest(StoneChestType.getFromName(name));
    }
}
