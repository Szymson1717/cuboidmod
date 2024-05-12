package com.cuboiddroid.cuboidmod.modules.furnace.inventory;

import com.cuboiddroid.cuboidmod.modules.furnace.tile.CuboidFurnaceTileEntityBase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public abstract class CuboidFurnaceContainerBase extends AbstractContainerMenu {

    protected CuboidFurnaceTileEntityBase te;
    protected ContainerData fields;
    protected Player playerEntity;
    protected IItemHandler playerInventory;
    protected final Level level;
    private RecipeType<? extends AbstractCookingRecipe> recipeType;

    public CuboidFurnaceContainerBase(MenuType<?> containerType, int windowId, Level level, BlockPos pos, Inventory playerInventory, Player player) {
        super(containerType, windowId);
        this.te = (CuboidFurnaceTileEntityBase) level.getBlockEntity(pos);
        this.recipeType = te.recipeType;
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.level = playerInventory.player.level;
        this.fields = te.fields;
        this.addDataSlots(this.fields);

        this.addSlot(new Slot(te, 0, 56, 17));
        this.addSlot(new CuboidFurnaceFuelSlot(this.te, 1, 56, 53));
        this.addSlot(new CuboidFurnaceSlot(playerEntity, te, 2, 116, 35));
        layoutPlayerInventorySlots(8, 84);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean showInventoryButtons() {
        return this.te.fields.get(4) == 1;
    }

    @OnlyIn(Dist.CLIENT)
    public int getRedstoneMode() {
        return this.te.getRedstoneSetting();
    }

    @OnlyIn(Dist.CLIENT)
    public int getComSub() {
        return this.te.getRedstoneComSub();
    }

    @OnlyIn(Dist.CLIENT)
    public BlockPos getPos() {
        return this.te.getBlockPos();
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.te.isBurning();
    }

    @OnlyIn(Dist.CLIENT)
    public int getCookScaled(int pixels) {
        int i = this.te.fields.get(2);
        int j = this.te.fields.get(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled(int pixels) {
        int i = this.te.fields.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.te.fields.get(0) * pixels / i;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void setData(int id, int data) {
        // super.setData(id, data);
        this.te.fields.set(id, data);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (this.hasRecipe(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (CuboidFurnaceTileEntityBase.isItemFuel(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected boolean hasRecipe(ItemStack stack) {
        return this.level.getRecipeManager().getRecipeFor((RecipeType)this.recipeType, new SimpleContainer(stack), this.level).isPresent();
    }
}