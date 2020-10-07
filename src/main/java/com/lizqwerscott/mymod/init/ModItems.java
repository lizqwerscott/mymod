package com.lizqwerscott.mymod.init;

import com.lizqwerscott.mymod.Main;
import com.lizqwerscott.mymod.items.ItemBase;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot", Main.ITEM_TAB);
}
