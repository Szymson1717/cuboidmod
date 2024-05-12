package com.cuboiddroid.cuboidmod.modules.chest.inventory;

import com.cuboiddroid.cuboidmod.setup.ModContainers;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CuboidChestContainer extends AbstractContainerMenu {

    private final Container inventory;

    private final CuboidChestTypes chestType;

    // Notsogudium
    public static CuboidChestContainer createNotsogudiumContainer(int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
        return new CuboidChestContainer(ModContainers.NOTSOGUDIUM_CHEST.get(), windowId, playerInventory, new SimpleContainer(CuboidChestTypes.NOTSOGUDIUM.size), CuboidChestTypes.NOTSOGUDIUM);
    }

    public static CuboidChestContainer createNotsogudiumContainer(int windowId, Inventory playerInventory, Container inventory) {
        return new CuboidChestContainer(ModContainers.NOTSOGUDIUM_CHEST.get(), windowId, playerInventory, inventory, CuboidChestTypes.NOTSOGUDIUM);
    }

    // Kudbebedda
    public static CuboidChestContainer createKudbebeddaContainer(int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
        return new CuboidChestContainer(ModContainers.KUDBEBEDDA_CHEST.get(), windowId, playerInventory, new SimpleContainer(CuboidChestTypes.KUDBEBEDDA.size), CuboidChestTypes.KUDBEBEDDA);
    }

    public static CuboidChestContainer createKudbebeddaContainer(int windowId, Inventory playerInventory, Container inventory) {
        return new CuboidChestContainer(ModContainers.KUDBEBEDDA_CHEST.get(), windowId, playerInventory, inventory, CuboidChestTypes.KUDBEBEDDA);
    }

    // Notarfbadium
    public static CuboidChestContainer createNotarfbadiumContainer(int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
        return new CuboidChestContainer(ModContainers.NOTARFBADIUM_CHEST.get(), windowId, playerInventory, new SimpleContainer(CuboidChestTypes.NOTARFBADIUM.size), CuboidChestTypes.NOTARFBADIUM);
    }

    public static CuboidChestContainer createNotarfbadiumContainer(int windowId, Inventory playerInventory, Container inventory) {
        return new CuboidChestContainer(ModContainers.NOTARFBADIUM_CHEST.get(), windowId, playerInventory, inventory, CuboidChestTypes.NOTARFBADIUM);
    }

    // Wikidium
    public static CuboidChestContainer createWikidiumContainer(int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
        return new CuboidChestContainer(ModContainers.WIKIDIUM_CHEST.get(), windowId, playerInventory, new SimpleContainer(CuboidChestTypes.WIKIDIUM.size), CuboidChestTypes.WIKIDIUM);
    }

    public static CuboidChestContainer createWikidiumContainer(int windowId, Inventory playerInventory, Container inventory) {
        return new CuboidChestContainer(ModContainers.WIKIDIUM_CHEST.get(), windowId, playerInventory, inventory, CuboidChestTypes.WIKIDIUM);
    }

    // Thatldu
    public static CuboidChestContainer createThatlduContainer(int windowId, Inventory playerInventory, FriendlyByteBuf buffer) {
        return new CuboidChestContainer(ModContainers.THATLDU_CHEST.get(), windowId, playerInventory, new SimpleContainer(CuboidChestTypes.THATLDU.size), CuboidChestTypes.THATLDU);
    }

    public static CuboidChestContainer createThatlduContainer(int windowId, Inventory playerInventory, Container inventory) {
        return new CuboidChestContainer(ModContainers.THATLDU_CHEST.get(), windowId, playerInventory, inventory, CuboidChestTypes.THATLDU);
    }

    public CuboidChestContainer(MenuType<?> containerType, int windowId, Inventory playerInventory, Container inventory, CuboidChestTypes chestType) {
        super(containerType, windowId);

        this.inventory = inventory;
        this.chestType = chestType;

        inventory.startOpen(playerInventory.player);

        for (int chestRow = 0; chestRow < chestType.getRowCount(); chestRow++) {
            for (int chestCol = 0; chestCol < chestType.rowLength; chestCol++) {
                this.addSlot(new Slot(inventory, chestCol + chestRow * chestType.rowLength, 12 + chestCol * 18, 18 + chestRow * 18));
            }
        }

        int leftCol = (chestType.xSize - 162) / 2 + 1;

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
                this.addSlot(new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, chestType.ySize - (4 - playerInvRow) * 18 - 10));
            }

        }

        for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
            this.addSlot(new Slot(playerInventory, hotbarSlot, leftCol + hotbarSlot * 18, chestType.ySize - 24));
        }
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return this.inventory.stillValid(playerIn);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < this.chestType.size) {
                if (!this.moveItemStackTo(itemstack1, this.chestType.size, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(itemstack1, 0, this.chestType.size, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.inventory.stopOpen(player);
    }

    @OnlyIn(Dist.CLIENT)
    public CuboidChestTypes getChestType() {
        return this.chestType;
    }
}