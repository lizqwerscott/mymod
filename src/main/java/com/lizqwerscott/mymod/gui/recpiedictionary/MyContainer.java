package com.lizqwerscott.mymod.gui.recpiedictionary;

import com.lizqwerscott.mymod.Main;
import com.lizqwerscott.mymod.init.ModItems;
import com.lizqwerscott.mymod.tileentity.recpieDictionary.TileEntityRepiceDictionary;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MyContainer extends Container {

    private ItemStackHandler items = null;

    public MyContainer(EntityPlayer entityPlayer, World world, BlockPos pos) {
        super();
        TileEntityRepiceDictionary tileEntity = (TileEntityRepiceDictionary)world.getTileEntity(pos);
        this.items = tileEntity.getInventory();
        addSlotToContainer(new SlotItemHandler(items, 0, 80, 36) {
            @Override
            public int getItemStackLimit(ItemStack stack) {
                return 1;
            }
        });
        addSlotToContainer(new SlotItemHandler(items, 1, 147, 85) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack != null && stack.getItem() == Items.GOLD_INGOT && super.isItemValid(stack);
            }
            @Override
            public int getItemStackLimit(ItemStack stack) {
                return 1;
            }
        });

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 114 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 172));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    protected List<Slot> getInventorySlots(IInventory inventory, boolean invertRule){
        ArrayList<Slot> slots = new ArrayList<>();
        for(Slot slot : inventorySlots) {
            if (invertRule && slot.inventory.getClass().equals(inventory.getClass()))
                continue;

            if (!invertRule && !slot.inventory.equals(inventory))
                continue;

            slots.add(slot);
        }

        return slots;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot sourceSlot = getSlot(index);

        for(Slot targetSlot : getInventorySlots(sourceSlot.inventory, true))
            if(transferToSlot(sourceSlot, targetSlot) == 0) break;

        return ItemStack.EMPTY; //this has to return empty itemstack, otherwise it runs as loop
    }
    protected int transferToSlot(Slot source, Slot target){
        if(source.getStack().isEmpty())
            return 0;

        if(!target.isItemValid(source.getStack()))
            return source.getStack().getCount();

        //return if stack in slot is not equal to input

        ItemStack stack = source.getStack().copy();
        ItemStack stackOld = source.getStack().copy();

        int transfer = Math.min(target.getSlotStackLimit(), source.getStack().getCount());

        if(target.getHasStack()) {
            if (!ItemHandlerHelper.canItemStacksStack(source.getStack(), target.getStack()))
                return source.getStack().getCount();

            transfer = Math.min(transfer, Math.min(target.getStack().getMaxStackSize(), target.getSlotStackLimit()) - target.getStack().getCount());
            stackOld.shrink(transfer);
            stack = target.getStack();
            stack.grow(transfer);
        }
        else{
            stack.setCount(transfer);
            stackOld.shrink(transfer);
        }
        if(transfer > 0) {
            source.putStack(stackOld);
            target.putStack(stack);
        }

        return source.getStack().getCount();
    }

}
