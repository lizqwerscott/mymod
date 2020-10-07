package com.lizqwerscott.mymod.tileentity;

import li.cil.oc.api.Network;
import li.cil.oc.api.prefab.TileEntityEnvironment;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityBase extends TileEntityEnvironment implements ITickable {

    private boolean addedNetwork = false;

    public TileEntityBase() {}

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public void update() {
        if (!addedNetwork) {
            addedNetwork = true;
            Network.joinOrCreateNetwork(this);
        }
    }
}
