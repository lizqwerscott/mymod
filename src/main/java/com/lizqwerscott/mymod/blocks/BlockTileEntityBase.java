package com.lizqwerscott.mymod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTileEntityBase extends BlockBase implements IRegisterTileEntity{

    public BlockTileEntityBase(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public void registerTileEntity() {}
}
