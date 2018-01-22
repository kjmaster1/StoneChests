package com.kjmaster.stonechests.common.blocks.tile.container;

import com.kjmaster.stonechests.common.blocks.StoneChestType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerStoneChest extends Container {

    private StoneChestType type;
    private EntityPlayer player;
    private IInventory chest;

    public ContainerStoneChest(IInventory playerInv, IInventory chestInv, StoneChestType type) {
        chest = chestInv;
        player = ((InventoryPlayer) playerInv).player;
        this.type = type;
        chestInv.openInventory(player);
        layoutContainer(playerInv, chestInv);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return chest.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < 27)
            {
                if (!this.mergeItemStack(itemstack1, 27, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 27, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        this.chest.closeInventory(player);
    }

    protected void layoutContainer(IInventory playerInv, IInventory chestInv) {
        int numRows = chestInv.getSizeInventory() / 9;
        chestInv.openInventory(player);
        int i = (numRows - 4) * 18;

        for (int j = 0; j < numRows; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(chestInv, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(playerInv, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(playerInv, i1, 8 + i1 * 18, 161 + i));
        }
    }

}
