package net.qbfreak.fifochest.block.fifochest;

import jline.internal.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityFIFOChest extends TileEntity implements ITickable {

    private static int chestSize = 27;
    private int tickDelay = 10;

    private ItemStack[] contents;
    private ItemStack[] prevContents;
    private boolean init = false;

    TileEntityFIFOChest() {
        contents = new ItemStack[chestSize];
        prevContents = new ItemStack[chestSize];
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            if (!init) {
                for (int i=0; i<chestSize;i++) {
                    contents[i] = inventory.getStackInSlot(i);
                    prevContents[i] = contents[i];
                }
                init = true;
            }

            tickDelay--;
            if (tickDelay <= 0) {
                checkContents();
                tickDelay = 40; // 10
            }
        }
    }

    private void checkContents() {
        for (int i=0; i<chestSize;i++) {
            contents[i] = inventory.getStackInSlot(i);
            if (contents[i] != prevContents[i]) {
                System.out.println("Slot " + i + " has changed.");
                System.out.println(prevContents[i].toString());
                System.out.println(contents[i].toString());
                System.out.println("");
            }
            prevContents[i] = contents[i];
        }
    }

    private ItemStackHandler inventory = new ItemStackHandler(chestSize );

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (!world.isRemote) {
            checkContents();
        }
    }

}
