package net.qbfreak.fifochest.recipie;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.qbfreak.fifochest.block.ModBlocks;
import net.qbfreak.fifochest.item.ModItems;

public class ModRecipies {

    public static void init() {
        GameRegistry.addSmelting(ModBlocks.oreCubium, new ItemStack(ModItems.ingotCubium), 0.7f);
    }

}
