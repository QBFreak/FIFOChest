package net.qbfreak.fifochest;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.qbfreak.fifochest.block.ModBlocks;
import net.qbfreak.fifochest.client.CustomTab;
import net.qbfreak.fifochest.item.ModItems;
import net.qbfreak.fifochest.proxy.CommonProxy;
import net.qbfreak.fifochest.recipie.ModRecipies;

@Mod(modid = FIFOChest.modId, name = FIFOChest.name, version = FIFOChest.version)
public class FIFOChest {

    public static final String modId = "fifochest";
    public static final String name = "FIFO Chest";
    public static final String version = "1.0.0";

    public static final CustomTab creativeTab = new CustomTab();

    @Mod.Instance(modId)
    public static FIFOChest instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipies.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SidedProxy(serverSide = "net.qbfreak.fifochest.proxy.CommonProxy", clientSide = "net.qbfreak.fifochest.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }

    }

}
