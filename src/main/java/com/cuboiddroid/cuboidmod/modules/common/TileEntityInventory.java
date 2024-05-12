package com.cuboiddroid.cuboidmod.modules.common;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Nameable;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import javax.annotation.Nullable;

public abstract class TileEntityInventory extends BlockEntity implements ITileInventory, WorldlyContainer, MenuProvider, Nameable {

    public NonNullList<ItemStack> inventory;
    protected Component name;

    public TileEntityInventory(BlockEntityType<?> tileEntityTypeIn, int sizeInventory) {
        this(tileEntityTypeIn, null, null, sizeInventory);
    }

    public TileEntityInventory(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int sizeInventory) {
        super(tileEntityTypeIn, pos, state);
        inventory = NonNullList.withSize(sizeInventory, ItemStack.EMPTY);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket(){
        CompoundTag nbtTag = new CompoundTag();
        this.save(nbtTag);
        this.setChanged();
        return new ClientboundBlockEntityDataPacket(getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt){
        CompoundTag tag = pkt.getTag();
        this.load(tag);
        this.setChanged();
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition).getBlock().defaultBlockState(), level.getBlockState(worldPosition), 2);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compound = new CompoundTag();

        this.save(compound);
        return compound;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return this.IisItemValidForSlot(index, stack);
    }

    public void setCustomName(Component name) {
        this.name = name;
    }

    @Override
    public Component getName() {
        return (this.name != null ? this.name : new TranslatableComponent(IgetName()));
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return this.IgetSlotsForFace(side);
    }

    @Override

    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return IcanExtractItem(index, stack, direction);
    }

    @Override

    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return this.IisItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.inventory) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.inventory.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(this.inventory, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.inventory, index);
    }


    @Override
    public void setItem(int index, ItemStack stack) {
        ItemStack itemstack = this.inventory.get(index);
        boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
        this.inventory.set(index, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
    }

    @Override
    public int getContainerSize() {
        return this.inventory.size();
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.inventory);
        if (compound.contains("CustomName", 8)) {
            this.name = Component.Serializer.fromJson(compound.getString("CustomName"));
        }
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        super.save(compound);
        ContainerHelper.saveAllItems(compound, this.inventory);
        if (this.name != null) {
            compound.putString("CustomName", Component.Serializer.toJson(this.name));
        }
        return compound;
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }

    @Override
    public void startOpen(Player player) { }

    @Override
    public void stopOpen(Player player) { }

    @Override
    public void setRemoved() {
        this.inventory.clear();
    }

    @Override
    public boolean hasCustomName() {
        return this.name != null;
    }

    @Nullable
    @Override
    public Component getCustomName() {
        return this.name;
    }

    @Override
    public Component getDisplayName() {
        return this.getName();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
        return IcreateMenu(i, playerInventory, playerEntity);
    }
}