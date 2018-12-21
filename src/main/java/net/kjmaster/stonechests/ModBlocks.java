package net.kjmaster.stonechests;

import net.kjmaster.stonechests.block.BlockStoneChest;
import net.kjmaster.stonechests.block.entity.TileStoneChest;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
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

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder) {
        BlockEntityType<T> blockEntityType = builder.method_11034(null);
        Registry.register(Registry.BLOCK_ENTITY, StoneChests.MODID + ":" + name, blockEntityType);
        return blockEntityType;
    }

    public static void init() {
        stoneChestBlock = new BlockStoneChest("stone_chest");
        cobbleChestBlock = new BlockStoneChest("cobble_chest");
        graniteChestBlock = new BlockStoneChest("granite_chest");
        graniteSmoothChestBlock = new BlockStoneChest("granite_smooth_chest");
        dioriteChestBlock = new BlockStoneChest("diorite_chest");
        dioriteSmoothChestBlock = new BlockStoneChest("diorite_smooth_chest");
        andesiteChestBlock = new BlockStoneChest("andesite_chest");
        andesiteSmoothChestBlock = new BlockStoneChest("andesite_smooth_chest");
        TILE_STONE_CHEST = register("tile_stone_chest", BlockEntityType.Builder.create(TileStoneChest::new));
    }
}
