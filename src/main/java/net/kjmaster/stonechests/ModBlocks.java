package net.kjmaster.stonechests;

import net.kjmaster.stonechests.block.BlockStoneChest;
import net.kjmaster.stonechests.block.entity.TileStoneChest;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static BlockStoneChest stoneChestBlock;
    public static BlockStoneChest cobbleChestBlock;
    public static BlockStoneChest graniteChestBlock;
    public static BlockStoneChest graniteSmoothChestBlock;
    public static BlockStoneChest dioriteChestBlock;
    public static BlockStoneChest dioriteSmoothChestBlock;
    public static BlockStoneChest andesiteChestBlock;
    public static BlockStoneChest andesiteSmoothChestBlock;

    public static BlockEntityType<TileStoneChest> TILE_STONE_CHEST;

    public static BlockStoneChest register(BlockStoneChest chest) {
        Registry.register(Registry.BLOCK, new Identifier(StoneChests.MODID, chest.getName()), chest);
        Registry.register(Registry.ITEM, new Identifier(StoneChests.MODID, chest.getName()), new BlockItem(chest, new Item.Settings().group(ItemGroup.DECORATIONS)));
        return chest;
    }

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder) {
        BlockEntityType<T> blockEntityType = builder.build(null);
        Registry.register(Registry.BLOCK_ENTITY, StoneChests.MODID + ":" + name, blockEntityType);
        return blockEntityType;
    }

    public static void init() {
        stoneChestBlock = register(new BlockStoneChest("stone_chest"));
        cobbleChestBlock = register(new BlockStoneChest("cobble_chest"));
        graniteChestBlock = register(new BlockStoneChest("granite_chest"));
        graniteSmoothChestBlock = register(new BlockStoneChest("granite_smooth_chest"));
        dioriteChestBlock = register(new BlockStoneChest("diorite_chest"));
        dioriteSmoothChestBlock = register(new BlockStoneChest("diorite_smooth_chest"));
        andesiteChestBlock = register(new BlockStoneChest("andesite_chest"));
        andesiteSmoothChestBlock = new BlockStoneChest("andesite_smooth_chest");
        TILE_STONE_CHEST = register("tile_stone_chest", BlockEntityType.Builder.create(TileStoneChest::new,
                stoneChestBlock, cobbleChestBlock, graniteChestBlock, graniteSmoothChestBlock,
                dioriteChestBlock, dioriteSmoothChestBlock, andesiteChestBlock, andesiteSmoothChestBlock));
    }
}
