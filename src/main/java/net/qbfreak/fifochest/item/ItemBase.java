package net.qbfreak.fifochest.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.qbfreak.fifochest.FIFOChest;

public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name) {
        this.name = name;

        setCreativeTab(FIFOChest.creativeTab);

        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public void registerItemModel() {
        FIFOChest.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ItemBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

}
