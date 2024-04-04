package com.cuboiddroid.cuboidmod.modules.dryingcupboard.inventory;

import com.cuboiddroid.cuboidmod.modules.dryingcupboard.tile.DryingCupboardTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModContainers;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
import com.cuboiddroid.cuboidmod.util.CuboidEnergyStorage;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.SimpleContainer;
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

public class DryingCupboardContainer extends AbstractContainerMenu {

    protected final Level level;
    protected DryingCupboardTileEntity tileEntity;
    protected Player playerEntity;
    protected IItemHandler playerInventory;

    public DryingCupboardContainer(int windowId,
                                   Level world,
                                   BlockPos pos,
                                   Inventory playerInventory,
                                   Player player) {
        super(ModContainers.DRYING_CUPBOARD.get(), windowId);
        this.tileEntity = (DryingCupboardTileEntity) world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.level = playerInventory.player.level;

        if (tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                // input slots
                for (int i = 0; i < DryingCupboardTileEntity.INPUT_SLOTS; i++)
                    addSlot(new SlotItemHandler(h, i, 26 + 18 * i, 20));

                // output slots
                for (int i = 0; i < DryingCupboardTileEntity.OUTPUT_SLOTS; i++)
                    addSlot(new SlotItemHandler(h, DryingCupboardTileEntity.INPUT_SLOTS + i, 26 + 18 * i, 55));
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

        // processing time - slot 0
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getProcessingTime(0) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setProcessingTime(0, value);
            }
        });

        // processing time - slot 1
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getProcessingTime(1) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setProcessingTime(1, value);
            }
        });

        // processing time - slot 2
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getProcessingTime(2) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setProcessingTime(2, value);
            }
        });

        // processing time - slot 3
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getProcessingTime(3) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setProcessingTime(3, value);
            }
        });

        // processing time - slot 4
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getProcessingTime(4) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setProcessingTime(4, value);
            }
        });

        // processing time - slot 5
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getProcessingTime(5) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setProcessingTime(5, value);
            }
        });

        // processing time - slot 6
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getProcessingTime(6) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setProcessingTime(6, value);
            }
        });

        // processing time - slot 7
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getProcessingTime(7) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setProcessingTime(7, value);
            }
        });

        // recipe time - slot 0
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getRecipeTime(0) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setRecipeTime(0, value);
            }
        });

        // recipe time - slot 1
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getRecipeTime(1) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setRecipeTime(1, value);
            }
        });

        // recipe time - slot 2
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getRecipeTime(2) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setRecipeTime(2, value);
            }
        });

        // recipe time - slot 3
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getRecipeTime(3) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setRecipeTime(3, value);
            }
        });

        // recipe time - slot 4
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getRecipeTime(4) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setRecipeTime(4, value);
            }
        });

        // recipe time - slot 5
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getRecipeTime(5) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setRecipeTime(5, value);
            }
        });

        // recipe time - slot 6
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getRecipeTime(6) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setRecipeTime(6, value);
            }
        });

        // recipe time - slot 7
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return tileEntity.getRecipeTime(7) & 0x7FFF;
            }

            @Override
            public void set(int value) {
                tileEntity.setRecipeTime(7, value);
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
        int playerInventoryStartSlot = DryingCupboardTileEntity.TOTAL_SLOTS;
        int playerInventoryEndSlot = playerInventoryStartSlot + 27;
        int playerHotbarStartSlot = playerInventoryEndSlot;
        int playerHotbarEndSlot = playerHotbarStartSlot + 9;

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index < DryingCupboardTileEntity.TOTAL_SLOTS) {
                // moving the inputs or output out into player inventory slots
                if (!this.moveItemStackTo(stack, playerInventoryStartSlot, playerHotbarEndSlot, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
                // moving something in the player inventory

                // check if it's something that can be moved into the
                if (hasRecipe(stack)) {
                    // the stack being moved has a valid recipe - try and move it to input slots
                    if (!this.moveItemStackTo(stack, 0, DryingCupboardTileEntity.INPUT_SLOTS, false)) {
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
                ModBlocks.DRYING_CUPBOARD.get());
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

    protected boolean hasRecipe(ItemStack stack) {
        return this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.DRYING.get(), new SimpleContainer(stack), this.level).isPresent();
    }

}
