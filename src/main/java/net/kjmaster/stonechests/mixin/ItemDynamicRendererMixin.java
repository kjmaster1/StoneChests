package net.kjmaster.stonechests.mixin;

import net.kjmaster.stonechests.ModBlocks;
import net.kjmaster.stonechests.block.StoneChestType;
import net.kjmaster.stonechests.block.entity.TileStoneChest;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.ItemDynamicRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.block.BlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemDynamicRenderer.class)
public class ItemDynamicRendererMixin {

	private final TileStoneChest renderStoneChest = new TileStoneChest(StoneChestType.STONE);
    private final TileStoneChest renderCobbleChest = new TileStoneChest(StoneChestType.COBBLE);
    private final TileStoneChest renderAndesiteChest = new TileStoneChest(StoneChestType.ANDESITE);
    private final TileStoneChest renderAndesiteSmoothChest = new TileStoneChest(StoneChestType.ANDESITE_SMOOTH);
    private final TileStoneChest renderDioriteChest = new TileStoneChest(StoneChestType.DIORITE);
    private final TileStoneChest renderDioriteSmoothChest = new TileStoneChest(StoneChestType.DIORITE_SMOOTH);
    private final TileStoneChest renderGraniteChest = new TileStoneChest(StoneChestType.GRANITE);
    private final TileStoneChest renderGraniteSmoothChest = new TileStoneChest(StoneChestType.GRANITE_SMOOTH);

	@Inject(at = @At("TAIL"), method = "render")
	private void method_3166(ItemStack stack, CallbackInfo info) {
		Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block == ModBlocks.stoneChestBlock) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderStoneChest);
            } else if (block == ModBlocks.cobbleChestBlock) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderCobbleChest);
            } else if (block == ModBlocks.andesiteChestBlock) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderAndesiteChest);
            } else if (block == ModBlocks.dioriteChestBlock) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderDioriteChest);
            } else if (block == ModBlocks.andesiteSmoothChestBlock) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderAndesiteSmoothChest);
            } else if (block == ModBlocks.dioriteSmoothChestBlock) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderDioriteSmoothChest);
            } else if (block == ModBlocks.graniteChestBlock) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderGraniteChest);
            } else if (block == ModBlocks.graniteSmoothChestBlock) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderGraniteSmoothChest);
            }
        }
	}
}
