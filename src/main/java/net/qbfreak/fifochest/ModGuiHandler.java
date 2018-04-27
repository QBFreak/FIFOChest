package net.qbfreak.fifochest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.qbfreak.fifochest.block.fifochest.ContainerFIFOChest;
import net.qbfreak.fifochest.block.fifochest.GuiFIFOChest;
import net.qbfreak.fifochest.block.fifochest.TileEntityFIFOChest;

public class ModGuiHandler implements IGuiHandler {

    public static final int FIFOCHEST = 0;

    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case FIFOCHEST:
                return new ContainerFIFOChest(player.inventory, (TileEntityFIFOChest)world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case FIFOCHEST:
                return new GuiFIFOChest(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            default:
                return null;
        }
    }

}
