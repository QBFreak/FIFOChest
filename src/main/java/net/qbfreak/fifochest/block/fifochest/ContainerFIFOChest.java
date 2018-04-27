package net.qbfreak.fifochest.block.fifochest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerFIFOChest extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    // Shadowfacts' Item Stack transfer (shift-click)
    //  See https://shadowfacts.net/tutorials/forge-modding-112/tile-entities-inventory-gui/
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    public ContainerFIFOChest(InventoryPlayer playerInv, final TileEntityFIFOChest fifoChest) {
        IItemHandler inventory = fifoChest.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);

        /* FIFOChest slots */
        // First slot is x=8, y=18
        for (int l = 0; l < 3; l++) {
            for (int m = 0; m < 9; m++) {
                addSlotToContainer(new SlotItemHandler(inventory, m + l * 9, 8 + m * 18, 18 + l * 18) {
                    @Override
                    public void onSlotChanged() {
                        fifoChest.markDirty();
                    }
                });
            }
        }

//        addSlotToContainer(new SlotItemHandler(inventory, 0, 8, 18) {
//            @Override
//            public void onSlotChanged() {
//                fifoChest.markDirty();
//            }
//        });

        /* Player inventory slots */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
        }
    }

}
