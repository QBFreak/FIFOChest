package net.qbfreak.fifochest.block.fifochest;

import jline.internal.Nullable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityFIFOChest extends TileEntity implements ITickable {

    private int tickDelay = 10;
    private NBTTagCompound contents;
    private NBTTagCompound prevContents;

    @Override
    public void update() {
        if (world.isRemote) {
            tickDelay--;
            if (tickDelay <= 0) {
                checkContents();
                tickDelay = 10;
            }
        }
    }

    private void checkContents() {
        contents = inventory.serializeNBT();
        if (contents != prevContents) {
            System.out.println("Contents have changed.");
        }
        prevContents = contents;
    }

    private ItemStackHandler inventory = new ItemStackHandler(27);

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
        checkContents();
    }

}
