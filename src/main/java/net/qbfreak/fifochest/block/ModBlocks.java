package net.qbfreak.fifochest.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.qbfreak.fifochest.block.counter.BlockCounter;
import net.qbfreak.fifochest.block.fifochest.BlockChest;

public class ModBlocks {

    public static BlockOre oreCubium = new BlockOre("ore_cubium");
    public static BlockChest fifoChest = new BlockChest();
    public static BlockCounter counter = new BlockCounter();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                oreCubium,
                fifoChest,
                counter
        );
        GameRegistry.registerTileEntity(counter.getTileEntityClass(), counter.getRegistryName().toString());
        GameRegistry.registerTileEntity(fifoChest.getTileEntityClass(), fifoChest.getRegistryName().toString());
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                oreCubium.createItemBlock(),
                fifoChest.createItemBlock(),
                counter.createItemBlock()
        );
    }

    public static void registerModels() {
        oreCubium.registerItemModel(Item.getItemFromBlock(oreCubium));
        fifoChest.registerItemModel(Item.getItemFromBlock(fifoChest));
        counter.registerItemModel(Item.getItemFromBlock(counter));
    }

}
