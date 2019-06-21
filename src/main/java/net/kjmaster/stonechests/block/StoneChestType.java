package net.kjmaster.stonechests.block;

import net.kjmaster.stonechests.StoneChests;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

public enum StoneChestType implements StringIdentifiable {

    COBBLE("cobble_chest", "cobble_chest.png", 0),
    STONE("stone_chest", "stone_chest.png", 1),
    GRANITE("granite_chest", "granite_chest.png", 2),
    GRANITE_SMOOTH("granite_smooth_chest", "granite_smooth_chest.png", 3),
    DIORITE("diorite_chest", "diorite_chest.png", 4),
    DIORITE_SMOOTH("diorite_smooth_chest", "diorite_smooth_chest.png", 5),
    ANDESITE("andesite_chest", "andesite_chest.png", 6),
    ANDESITE_SMOOTH("andesite_smooth_chest", "andesite_smooth_chest.png", 7);

    private final String name;
    private final Identifier modelTexture;
    private final Identifier doubleModelTexture;
    private final int id;

    StoneChestType(String name, String modelName, int id) {
        this.name = name;
        this.modelTexture = new Identifier(StoneChests.MODID, "textures/model/chest/" + modelName);
        this.doubleModelTexture = new Identifier(StoneChests.MODID, "textures/model/chest/" + name + "_double.png");
        this.id = id;
    }

    public static StoneChestType getFromName(String name) {

        switch (name) {

            case "cobble_chest":
                return COBBLE;

            case "stone_chest":
                return STONE;

            case "granite_chest":
                return GRANITE;

            case "granite_smooth_chest":
                return GRANITE_SMOOTH;

            case "diorite_chest":
                return DIORITE;

            case "diorite_smooth_chest":
                return DIORITE_SMOOTH;

            case "andesite_chest":
                return ANDESITE;

            case "andesite_smooth_chest":
                return ANDESITE_SMOOTH;
        }

        return STONE;
    }

    public int getId() {
        return id;
    }

    public Identifier getModelTexture() {
        return modelTexture;
    }

    public Identifier getDoubleModelTexture() {
        return doubleModelTexture;
    }

    @Override
    public String asString() {
        return name;
    }
}
