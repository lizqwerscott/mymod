package com.lizqwerscott.mymod.blocks;

import com.lizqwerscott.mymod.Main;
import com.lizqwerscott.mymod.tileentity.recpieDictionary.TileEntityRepiceDictionary;
import li.cil.oc.api.driver.DriverItem;
import li.cil.oc.api.driver.EnvironmentProvider;
import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.client.gui.Drive;
import li.cil.oc.common.Slot;
import li.cil.repack.org.luaj.vm2.ast.Str;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentScore;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.ItemStackHandler;

public class RecpieDictionary extends BlockTileEntityBase {

    public RecpieDictionary(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);

        setSoundType(SoundType.STONE);
        setHardness(10.0f);
        setResistance(1000.0f);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityRepiceDictionary.class, getRegistryName());
        super.registerTileEntity();
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityRepiceDictionary();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileEntityRepiceDictionary) {
            playerIn.setHeldItem(hand, ((TileEntityRepiceDictionary)tileEntity).tryAcceptNumber(playerIn.getHeldItem(hand)));
            playerIn.sendStatusMessage(new TextComponentString("Number: " + ((TileEntityRepiceDictionary)tileEntity).getNumber()), true);
        }
        IRecipe repice = CraftingManager.getRecipe(new ResourceLocation("stick"));
        NonNullList<Ingredient> ingredients = repice.getIngredients();
        for (Ingredient ingredient : ingredients) {
            Main.logger.info("The repice: >> {}", ingredient.getMatchingStacks()[0].getDisplayName());
        }
        playerIn.openGui(Main.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return  true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        final TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileEntityRepiceDictionary) {
            final ItemStackHandler inv = ((TileEntityRepiceDictionary)tileEntity).getInventory();
            for (int i = 0; i < inv.getSlots(); i++) {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inv.getStackInSlot(i));
            }
        }
        super.breakBlock(worldIn, pos, state);
    }
}
