package com.kjmaster.stonechests.common.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;

public class MissingMappingsHandler {

    @SubscribeEvent
    public void missingBlockMappings(RegistryEvent.MissingMappings<Block> event) {

        for (RegistryEvent.MissingMappings.Mapping<Block> entry : event.getAllMappings()) {

            @Nonnull
            String path = entry.key.getResourcePath();

            replaceOldChestBlock(path, entry);
        }
    }

    @SubscribeEvent
    public void missingItemMappings(RegistryEvent.MissingMappings<Item> event) {

        for (RegistryEvent.MissingMappings.Mapping<Item> entry : event.getAllMappings()) {

            @Nonnull
            String path = entry.key.getResourcePath();

            replaceOldChestItem(path, entry);
        }
    }

    private static void replaceOldChestBlock(String path, RegistryEvent.MissingMappings.Mapping<Block> mapping) {

        if (path.endsWith("blockstonechest")) {
            path = path.replace("blockstonechest", "stone_chest");
            ResourceLocation newRes = new ResourceLocation(mapping.key.getResourceDomain(), path);
            Block block = ForgeRegistries.BLOCKS.getValue(newRes);

            if (block != null) {
                mapping.remap(block);
            }
        }
    }

    private static void replaceOldChestItem(String path, RegistryEvent.MissingMappings.Mapping<Item> mapping) {

        if (path.endsWith("blockstonechest")) {

            path = path.replace("blockstonechest", "stone_chest");
            ResourceLocation newRes = new ResourceLocation(mapping.key.getResourceDomain(), path);
            Item item = ForgeRegistries.ITEMS.getValue(newRes);

            if (item != null) {
                mapping.remap(item);
            }
        }
    }
}
