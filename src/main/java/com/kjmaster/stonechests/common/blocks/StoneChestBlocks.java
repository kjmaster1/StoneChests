package com.kjmaster.stonechests.common.blocks;

import com.kjmaster.stonechests.StoneChests;
import com.kjmaster.stonechests.common.blocks.item.ItemStoneChest;
import com.kjmaster.stonechests.common.util.BlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class StoneChestBlocks {

    @GameRegistry.ObjectHolder(BlockNames.STONE_CHEST)
    public static final BlockStoneChest stoneChestBlock = null;
    @GameRegistry.ObjectHolder(BlockNames.STONE_CHEST)
    public static final Item stoneChestItemBlock = null;

    @Mod.EventBusSubscriber(modid = StoneChests.MODID)
    public static class Registration {
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            IForgeRegistry<Block> blockRegistry = event.getRegistry();

            blockRegistry.register(new BlockStoneChest("stone_chest", Material.ROCK, CreativeTabs.DECORATIONS, 3.0F, 30.0F, "pickaxe", 1));

            for (StoneChestType type : StoneChestType.values()) {
                if (type.clazz != null) {
                    GameRegistry.registerTileEntity(type.clazz, "StoneChests." + type.name());
                }
            }
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            IForgeRegistry<Item> itemRegistry = event.getRegistry();

            itemRegistry.register(new ItemStoneChest(StoneChestBlocks.stoneChestBlock));
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {

            Item chestItem = Item.getItemFromBlock(StoneChestBlocks.stoneChestBlock);

            for (StoneChestType type : StoneChestType.values()) {
                ModelLoader.setCustomModelResourceLocation(chestItem, type.ordinal(),
                        new ModelResourceLocation(chestItem.getRegistryName(), "variant=" + type.getName()));
            }
        }
    }
}
