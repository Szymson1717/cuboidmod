package com.cuboiddroid.cuboidmod.modules.furnace.inventory;

import com.cuboiddroid.cuboidmod.modules.furnace.tile.CuboidFurnaceTileEntityBase;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CuboidFurnaceFuelSlot extends Slot {
    CuboidFurnaceTileEntityBase te;

    public CuboidFurnaceFuelSlot(CuboidFurnaceTileEntityBase te, int index, int x, int y) {
        super(te, index, x, y);
        this.te = te;
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean mayPlace(ItemStack stack) {
        return CuboidFurnaceTileEntityBase.isItemFuel(stack) || isBucket(stack);
    }

    public int getMaxStackSize(ItemStack stack) {
        return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.getItem() == Items.BUCKET;
    }
}