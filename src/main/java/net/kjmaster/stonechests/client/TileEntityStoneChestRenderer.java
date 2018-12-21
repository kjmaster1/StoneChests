package net.kjmaster.stonechests.client;

import com.mojang.blaze3d.platform.GlStateManager;
import net.kjmaster.stonechests.block.entity.TileStoneChest;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.block.ChestAnimationProgress;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.model.ChestDoubleEntityModel;
import net.minecraft.client.render.entity.model.ChestEntityModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class TileEntityStoneChestRenderer extends BlockEntityRenderer<TileStoneChest> {

    private final ChestEntityModel modelSingleChest = new ChestEntityModel();
    private final ChestEntityModel modelDoubleChest = new ChestDoubleEntityModel();

    @Override
    public void render(TileStoneChest var1, double var2, double var4, double var6, float var8, int var9) {
        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        BlockState var10 = var1.hasWorld() ? var1.getCachedState() : (BlockState) Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
        ChestType var11 = var10.contains(ChestBlock.field_10770) ? (ChestType)var10.get(ChestBlock.field_10770) : ChestType.SINGLE;
        if (var11 != ChestType.LEFT) {
            boolean var12 = var11 != ChestType.SINGLE;
            ChestEntityModel var13 = this.method_3562(var1, var9, var12);
            if (var9 >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.pushMatrix();
                GlStateManager.scalef(var12 ? 8.0F : 4.0F, 4.0F, 1.0F);
                GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
                GlStateManager.matrixMode(5888);
            } else {
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();
            GlStateManager.translatef((float)var2, (float)var4 + 1.0F, (float)var6 + 1.0F);
            GlStateManager.scalef(1.0F, -1.0F, -1.0F);
            float var14 = ((Direction)var10.get(ChestBlock.FACING)).asRotation();
            if ((double)Math.abs(var14) > 1.0E-5D) {
                GlStateManager.translatef(0.5F, 0.5F, 0.5F);
                GlStateManager.rotatef(var14, 0.0F, 1.0F, 0.0F);
                GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
            }

            this.method_3561(var1, var8, var13);
            var13.method_2799();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            if (var9 >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }

        }
    }

    private ChestEntityModel method_3562(TileStoneChest var1, int var2, boolean var3) {
        Identifier var4;
        if (var2 >= 0) {
            var4 = DESTROY_STAGE_TEXTURES[var2];
        } else {
            var4 = var3 ? var1.getStoneChestType().getDoubleModelTexture() : var1.getStoneChestType().getModelTexture();
        }
        this.bindTexture(var4);
        return var3 ? this.modelDoubleChest : this.modelSingleChest;
    }

    private void method_3561(TileStoneChest var1, float var2, ChestEntityModel var3) {
        float var4 = ((ChestAnimationProgress)var1).getAnimationProgress(var2);
        var4 = 1.0F - var4;
        var4 = 1.0F - var4 * var4 * var4;
        var3.method_2798().pitch = -(var4 * 1.5707964F);
    }
}