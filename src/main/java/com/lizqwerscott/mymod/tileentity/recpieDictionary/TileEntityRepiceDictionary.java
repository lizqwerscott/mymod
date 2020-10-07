package com.lizqwerscott.mymod.tileentity.recpieDictionary;

import com.lizqwerscott.mymod.Main;
import com.lizqwerscott.mymod.blocks.RecpieDictionary;
import com.lizqwerscott.mymod.tileentity.TileEntityBase;
import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.TileEntityEnvironment;
import li.cil.oc.integration.appeng.NetworkControl;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

public class TileEntityRepiceDictionary extends TileEntityBase implements ITickable {

    private int number;
    private final ItemStackHandler inventory = new ItemStackHandler(2);

    public TileEntityRepiceDictionary() {
        node = Network.newNode(this, Visibility.Network).withComponent("repice_dictionary").create();
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            //Main.logger.info("TileEntity Number: >> {}", this.inventory.getStackInSlot(0).getCount());
        }
    }

    @Callback
    public Object[] greet(Context context, Arguments arguments) {
        return new Object[]{String.format("Hello, %s!", arguments.checkString(0))};
    }

    @Callback
    public Object[] getRepice(Context context, Arguments arguments) {
        IRecipe recipe = CraftingManager.getRecipe(new ResourceLocation(arguments.checkString(0)));
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        ArrayList<String> arrayList = new ArrayList<String>();
        for (Ingredient ingredient : ingredients) {
            arrayList.add(ingredient.getMatchingStacks()[0].getUnlocalizedName());
        }
        return new Object[]{arrayList};
    }

    @Callback
    public Object[] craft(Context context, Arguments arguments) {
        boolean result = false;
        return new Object[]{result};
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public ItemStack tryAcceptNumber(ItemStack thing) {
        number += thing.getCount();
        return new ItemStack(Items.AIR);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.number = compound.getInteger("Number");
        this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("Number", this.number);
        compound.setTag("Inventory", this.inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        /*
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
            T result  = (T) rightInventory;
            if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
                result = (T) upTnventory;
            }
            return result;
        }
        return super.getCapability(capability, facing);

         */
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
        } else {
            return super.getCapability(capability, facing);
        }
    }


}
