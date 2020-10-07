package com.lizqwerscott.mymod.blocks;

import com.lizqwerscott.mymod.Main;
import com.lizqwerscott.mymod.init.ModBlocks;
import com.lizqwerscott.mymod.init.ModItems;
import com.lizqwerscott.mymod.tileentity.TileEntityBase;
import com.lizqwerscott.mymod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String name, Material material, CreativeTabs tab) {

        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}
