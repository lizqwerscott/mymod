package com.lizqwerscott.mymod.gui;

import com.lizqwerscott.mymod.Main;
import com.lizqwerscott.mymod.gui.recpiedictionary.MyContainer;
import com.lizqwerscott.mymod.gui.recpiedictionary.MyGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiElementLoader implements IGuiHandler {

    public static final int GUI_RECPIEDICTIONARY = 1;

    public GuiElementLoader() {
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID)
        {
            case GUI_RECPIEDICTIONARY:
                return new MyContainer(player, world, new BlockPos(x, y, z));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID)
        {
            case GUI_RECPIEDICTIONARY:
                return new MyGuiContainer(new MyContainer(player, world, new BlockPos(x, y, z)));
            default:
                return null;
        }
    }
}
