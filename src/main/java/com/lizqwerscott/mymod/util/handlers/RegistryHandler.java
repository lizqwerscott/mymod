package com.lizqwerscott.mymod.util.handlers;

import com.lizqwerscott.mymod.Main;
import com.lizqwerscott.mymod.blocks.BlockBase;
import com.lizqwerscott.mymod.blocks.BlockTileEntityBase;
import com.lizqwerscott.mymod.init.ModBlocks;
import com.lizqwerscott.mymod.init.ModItems;
import com.lizqwerscott.mymod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        for (Block block : ModBlocks.BLOCKS) {
            event.getRegistry().register(block);
            if (block.hasTileEntity()) {
                ((BlockTileEntityBase)block).registerTileEntity();
            }
        }
        //event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ModItems.ITEMS) {
           if (item instanceof IHasModel) {
               ((IHasModel)item).registerModels();
           }
        }

        for (Block block : ModBlocks.BLOCKS) {
            if (block instanceof IHasModel) {
                ((IHasModel)block).registerModels();
            }
        }
    }

    /*
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        String name = event.getItemStack().getUnlocalizedName();
        Main.logger.info("The Right Block Name >> {}", name);
    }

     */

}
