package com.lizqwerscott.mymod.tabs;

import com.lizqwerscott.mymod.init.ModBlocks;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockTab extends CreativeTabs {

    public BlockTab() {

        super("block_tab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(ModBlocks.GOLD_OBSIDIAN_BLOCK));
    }
}
