package com.lizqwerscott.mymod.init;

import com.lizqwerscott.mymod.Main;
import com.lizqwerscott.mymod.blocks.BlockBase;
import com.lizqwerscott.mymod.blocks.GoldObsidianBlock;
import com.lizqwerscott.mymod.blocks.RecpieDictionary;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block GOLD_OBSIDIAN_BLOCK = new GoldObsidianBlock("gold_obsidian_block", Material.IRON, Main.BLOCK_TAB);

    public static final Block RECPIE_DICTIONARY = new RecpieDictionary("recpie_dictionary", Material.IRON, Main.BLOCK_TAB);
}
