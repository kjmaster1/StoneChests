package com.kjmaster.stonechests.common.blocks.item;

import com.kjmaster.stonechests.common.blocks.StoneChestType;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.Locale;

public class ItemStoneChest extends ItemBlock {

    public ItemStoneChest(Block block) {
        super(block);

        this.setRegistryName(block.getRegistryName());
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.stonechests.chest." + StoneChestType.values()[stack.getMetadata()].name().toLowerCase(Locale.US);
    }
}
