package com.lizqwerscott.mymod;

import com.lizqwerscott.mymod.gui.GuiElementLoader;
import com.lizqwerscott.mymod.proxy.CommonProxy;
import com.lizqwerscott.mymod.util.Reference;
import com.lizqwerscott.mymod.tabs.BlockTab;
import com.lizqwerscott.mymod.tabs.ItemTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

  @Mod.Instance
  public static Main instance;

  public static Logger logger;

  @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
  public static CommonProxy proxy;

  @Mod.EventHandler
  public static void preInit(FMLPreInitializationEvent event) {
    logger = event.getModLog();
  }

  @Mod.EventHandler
  public static void init(FMLInitializationEvent event) {
    logger.info("Hello, This is MyMod");
    new GuiElementLoader();
  }

  @Mod.EventHandler
  public static void posInit(FMLPostInitializationEvent event) {

  }

  public static CreativeTabs BLOCK_TAB = new BlockTab();
  public static CreativeTabs ITEM_TAB = new ItemTab();
}
