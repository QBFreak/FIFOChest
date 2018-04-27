package net.qbfreak.fifochest.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.qbfreak.fifochest.FIFOChest;
import net.qbfreak.fifochest.item.ModItems;

public class CustomTab extends CreativeTabs {

    public CustomTab() {
        super(FIFOChest.modId);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.ingotCubium);
    }

}

