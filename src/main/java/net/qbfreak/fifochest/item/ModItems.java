package net.qbfreak.fifochest.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static ItemBase ingotCubium = new ItemBase("ingot_cubium");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                ingotCubium
        );
    }

    public static void registerModels() {
        ingotCubium.registerItemModel();
    }

}
