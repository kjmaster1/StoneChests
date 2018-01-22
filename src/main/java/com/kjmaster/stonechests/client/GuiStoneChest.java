package com.kjmaster.stonechests.client;

import com.kjmaster.stonechests.common.blocks.StoneChestType;
import com.kjmaster.stonechests.common.blocks.tile.container.ContainerStoneChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiStoneChest extends GuiContainer {

    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    public GuiStoneChest(StoneChestType type, IInventory player, IInventory chest) {
        super(new ContainerStoneChest(player, chest, type));
        this.xSize = 176;
        this.ySize = 168;
        this.allowUserInput = false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 3 * 18 + 17);
        this.drawTexturedModalRect(i, j + 3 * 18 + 17, 0, 126, this.xSize, 96);
    }
}
