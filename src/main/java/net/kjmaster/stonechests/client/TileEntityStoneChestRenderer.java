package net.kjmaster.stonechests.client;

import com.mojang.blaze3d.platform.GlStateManager;
import net.kjmaster.stonechests.block.entity.TileStoneChest;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.block.ChestAnimationProgress;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.model.ChestEntityModel;
import net.minecraft.client.render.entity.model.LargeChestEntityModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class TileEntityStoneChestRenderer extends BlockEntityRenderer<TileStoneChest> {

    private final ChestEntityModel modelSingleChest = new ChestEntityModel();
    private final ChestEntityModel modelDoubleChest = new LargeChestEntityModel();

    @Override
    public void render(TileStoneChest be, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        BlockState state = be.hasWorld() ? be.getCachedState() : Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
        ChestType type = state.contains(ChestBlock.CHEST_TYPE) ? state.get(ChestBlock.CHEST_TYPE) : ChestType.SINGLE;
        if (type != ChestType.LEFT) {
            boolean isLarge = type != ChestType.SINGLE;
            ChestEntityModel model = this.method_3562(be, destroyStage, isLarge);
            if (destroyStage >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.pushMatrix();
                GlStateManager.scalef(isLarge ? 8.0F : 4.0F, 4.0F, 1.0F);
                GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
                GlStateManager.matrixMode(5888);
            } else {
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();
            GlStateManager.translatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
            GlStateManager.scalef(1.0F, -1.0F, -1.0F);
            float rotation = (state.get(ChestBlock.FACING)).asRotation();
            if ((double)Math.abs(rotation) > 1.0E-5D) {
                GlStateManager.translatef(0.5F, 0.5F, 0.5F);
                GlStateManager.rotatef(rotation, 0.0F, 1.0F, 0.0F);
                GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
            }

            this.method_3561(be, partialTicks, model);
            model.method_2799();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            if (destroyStage >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }

        }
    }

    private ChestEntityModel method_3562(TileStoneChest be, int destroyStage, boolean isLarge) {
        Identifier texId;
        if (destroyStage >= 0) {
            texId = DESTROY_STAGE_TEXTURES[destroyStage];
        } else {
            texId = isLarge ? be.getStoneChestType().getDoubleModelTexture() : be.getStoneChestType().getModelTexture();
        }
        this.bindTexture(texId);
        return isLarge ? this.modelDoubleChest : this.modelSingleChest;
    }

    private void method_3561(TileStoneChest be, float partialTicks, ChestEntityModel model) {
        float progress = ((ChestAnimationProgress)be).getAnimationProgress(partialTicks);
        progress = 1.0F - progress;
        progress = 1.0F - progress * progress * progress;
        model.method_2798().pitch = -(progress * 1.5707964F);
    }
}