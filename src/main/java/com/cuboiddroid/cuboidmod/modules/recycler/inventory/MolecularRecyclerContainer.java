package com.cuboiddroid.cuboidmod.modules.recycler.inventory;

import com.cuboiddroid.cuboidmod.modules.recycler.tile.MolecularRecyclerTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModContainers;
import com.cuboiddroid.cuboidmod.util.CuboidEnergyStorage;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import static com.cuboiddroid.cuboidmod.modules.craftingtable.inventory.CuboidCraftingContainer.isWithinUsableDistance;

public class MolecularRecyclerContainer extends AbstractContainerMenu {

    protected final Level level;
    protected MolecularRecyclerTileEntity tileEntity;
    protected Player playerEntity;
    protected IItemHandler playerInventory;

    public MolecularRecyclerContainer(int windowId,
                                      Level world,
                                      BlockPos pos,
                                      Inventory playerInventory,
                                      Player player) {
        super(ModContainers.MOLECULAR_RECYCLER.get(), windowId);
        this.tileEntity = (MolecularRecyclerTileEntity) world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.level = playerInventory.player.level;

        if (tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, MolecularRecyclerTileEntity.SLOT_INPUT, 53, 43));
                addSlot(new SlotItemHandler(h, MolecularRecyclerTileEntity.SLOT_OUTPUT_1, 110, 35));
                addSlot(new SlotItemHandler(h, MolecularRecyclerTileEntity.SLOT_OUTPUT_2, 128, 35));
                addSlot(new SlotItemHandler(h, MolecularRecyclerTileEntity.SLOT_OUTPUT_3, 146, 35));
                addSlot(new SlotItemHandler(h, MolecularRecyclerTileEntity.SLOT_OUTPUT_4, 110, 53));
                addSlot(new SlotItemHandler(h, MolecularRecyclerTileEntity.SLOT_OUTPUT_5, 128, 53));
                addSlot(new SlotItemHandler(h, MolecularRecyclerTileEntity.SLOT_OUTPUT_6, 146, 53));
            });
        }

        layoutPlayerInventorySlots(8, 84);
        trackPower();
    }

    // Setup syncing of power from server to client so that the GUI can show the amount of power in the block
    private void trackPower() {
        // Unfortunately on a dedicated server ints are actually truncated to short so we need
        // to split our integer here (split our 32 bit integer into two 16 bit integers)
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getEnergy() & 0xffff;
            }

            @Override
            public void set(int value) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0xffff0000;
                    ((CuboidEnergyStorage) h).setEnergy(energyStored + (value & 0xffff));
                });
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (getEnergy() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0x0000ffff;
                    ((CuboidEnergyStorage) h).setEnergy(energyStored | (value << 16));
                });
            }
        });

        // processing time
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getProcessingTime();
            }

            @Override
            public void set(int value) {
                tileEntity.setProcessingTime(value);
            }
        });
    }

    public int getEnergy() {
        return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    public int getEnergyCapacity() {
        return tileEntity.getEnergyCapacity();
    }

    @OnlyIn(Dist.CLIENT)
    public BlockPos getPos() {
        return this.tileEntity.getBlockPos();
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        int playerInventoryStartSlot = MolecularRecyclerTileEntity.TOTAL_SLOTS;
        int playerInventoryEndSlot = playerInventoryStartSlot + 27;
        int playerHotbarStartSlot = playerInventoryEndSlot;
        int playerHotbarEndSlot = playerHotbarStartSlot + 9;

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index < MolecularRecyclerTileEntity.TOTAL_SLOTS) {
                // moving the inputs or output out into player inventory slots
                if (!this.moveItemStackTo(stack, playerInventoryStartSlot, playerHotbarEndSlot, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
                // moving something in the player inventory
                if (!this.slots.get(0).hasItem() || this.slots.get(0).getItem().sameItem(stack)) {
                    // first input is empty or already contains this item - try move
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < playerHotbarStartSlot) {
                    // it's something we can't use, and the player
                    // clicked in their inventory, so try move to hotbar
                    if (!this.moveItemStackTo(stack, playerHotbarStartSlot, playerHotbarEndSlot, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < playerHotbarEndSlot) {
                    // it's something we can't use, and the player
                    // clicked in their hotbar, so try move to their
                    // inventory slots instead.
                    if (!this.moveItemStackTo(stack, playerInventoryStartSlot, playerHotbarStartSlot, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return isWithinUsableDistance(ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos()),
                playerEntity,
                ModBlocks.MOLECULAR_RECYCLER.get());
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
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
}
