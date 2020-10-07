package com.lizqwerscott.mymod.tabs;

import com.lizqwerscott.mymod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemTab extends CreativeTabs {

    public ItemTab() {

        super("item_tab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.OBSIDIAN_INGOT);
    }
}
