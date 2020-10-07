package com.lizqwerscott.mymod.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class GoldObsidianBlock extends BlockBase {

    public GoldObsidianBlock(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);

        setSoundType(SoundType.STONE);
        setHardness(50.0f);
        setResistance(6000.0f);
        setHarvestLevel("pickaxe", 3);
        setLightLevel(12.0f);
        //setLightOpacity(1);
        //setBlockUnbreakable();
    }
}
