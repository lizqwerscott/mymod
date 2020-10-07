package com.lizqwerscott.mymod.tileentity.opencomputer;

import com.lizqwerscott.mymod.tileentity.TileEntityBase;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = "OpenComputers")
public class MyTileEntityComputer extends TileEntityBase implements SimpleComponent {

    private int test = 1;

    @Override
    public String getComponentName() {
        return "test_computer";
    }

    @Callback(doc = "hello")
    @Optional.Method(modid = "OpenComputers")
    public Object[] greet(Context context, Arguments arguments) {
        return new Object[]{String.format("Hello, %s!", arguments.checkString(0))};
    }
}
