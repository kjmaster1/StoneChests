package com.kjmaster.stonechests.client;

import com.kjmaster.stonechests.common.blocks.BlockStoneChest;
import com.kjmaster.stonechests.common.blocks.StoneChestBlocks;
import com.kjmaster.stonechests.common.blocks.StoneChestType;
import com.kjmaster.stonechests.common.blocks.tile.TileStoneChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class TileEntityStoneChestRenderer extends TileEntitySpecialRenderer<TileStoneChest> {

    private ModelChest model;

    public TileEntityStoneChestRenderer()
    {
        this.model = new ModelChest();
    }

    @Override
    public void render(TileStoneChest te, double x, double y, double z, float partialTicks, int destroyStage, float partial)
    {
        if (te == null || te.isInvalid())
        {
            return;
        }

        EnumFacing facing = EnumFacing.SOUTH;
        StoneChestType type = te.getType();

        if (te.hasWorld() && te.getWorld().getBlockState(te.getPos()).getBlock() == StoneChestBlocks.stoneChestBlock)
        {
            facing = te.getFacing();
            IBlockState state = te.getWorld().getBlockState(te.getPos());
            type = state.getValue(BlockStoneChest.VARIANT_PROP);
        }

        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4F, 4F, 1F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            this.bindTexture(type.getModelTexture());
        }

        GlStateManager.pushMatrix();

        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.translate((float) x, (float) y + 1F, (float) z + 1F);
        GlStateManager.scale(1F, -1F, -1F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);

        switch (facing)
        {
            case NORTH:
            {
                GlStateManager.rotate(180F, 0F, 1F, 0F);
                break;
            }
            case SOUTH:
            {
                GlStateManager.rotate(0F, 0F, 1F, 0F);
                break;
            }
            case WEST:
            {
                GlStateManager.rotate(90F, 0F, 1F, 0F);
                break;
            }
            case EAST:
            {
                GlStateManager.rotate(270F, 0F, 1F, 0F);
                break;
            }
            default:
            {
                GlStateManager.rotate(0F, 0F, 1F, 0F);
                break;
            }
        }

        GlStateManager.translate(-0.5F, -0.5F, -0.5F);

        float lidangle = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
        lidangle = 1.0F - lidangle;
        lidangle = 1.0F - lidangle * lidangle * lidangle;
        model.chestLid.rotateAngleX = -(lidangle * ((float)Math.PI / 2F));

        this.model.renderAll();

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }

        GlStateManager.popMatrix();
        GlStateManager.color(1F, 1F, 1F, 1F);
    }
}
