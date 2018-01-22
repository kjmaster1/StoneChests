package com.kjmaster.stonechests.common.blocks;

import com.kjmaster.stonechests.StoneChests;
import com.kjmaster.stonechests.common.blocks.tile.*;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public enum StoneChestType implements IStringSerializable {

    COBBLE("cobble", "cobble_chest.png", 0, TileCobbleChest.class),
    STONE("stone", "stone_chest.png", 1, TileStoneChest.class),
    GRANITE("granite", "granite_chest.png", 2, TileGraniteChest.class),
    GRANITE_SMOOTH("granitesmooth", "smooth_granite_chest.png", 3, TileGraniteSmoothChest.class),
    DIORITE("diorite", "diorite_chest.png", 4, TileDioriteChest.class),
    DIORITE_SMOOTH("dioritesmooth", "smooth_diorite_chest.png", 5, TileDioriteSmoothChest.class),
    ANDESITE("andesite", "andesite_chest.png", 6, TileAndesiteChest.class),
    ANDESITE_SMOOTH("andesitesmooth", "smooth_andesite_chest.png", 7, TileAndesiteSmoothChest.class);

    private final String name;
    private final ResourceLocation modelTexture;
    private final int id;
    public final Class<? extends TileStoneChest> clazz;

    StoneChestType(String name, String modelName, int id, Class<? extends TileStoneChest> clazz) {
        this.name = name;
        this.modelTexture = new ResourceLocation(StoneChests.MODID, "textures/model/chest/" + modelName);
        this.id = id;
        this.clazz = clazz;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ResourceLocation getModelTexture() {
        return modelTexture;
    }

    public static int validateMeta(int i) {
        if (i < values().length) {
            return i;
        }
        else {
            return 0;
        }
    }

    public TileStoneChest makeEntity() {
        switch (this) {
            case COBBLE:
                return new TileCobbleChest();
            case STONE:
                return new TileStoneChest();
            case DIORITE:
                return new TileDioriteChest();
            case GRANITE:
                return new TileGraniteChest();
            case ANDESITE:
                return new TileAndesiteChest();
            case DIORITE_SMOOTH:
                return new TileDioriteSmoothChest();
            case GRANITE_SMOOTH:
                return new TileGraniteSmoothChest();
            case ANDESITE_SMOOTH:
                return new TileAndesiteSmoothChest();
            default:
                return null;
        }
    }

    public Slot makeSlot(IInventory chestInv, int index, int x, int y) {
        return new Slot(chestInv, index, x, y);
    }
}
