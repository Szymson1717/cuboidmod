package com.cuboiddroid.cuboidmod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.energy.EnergyStorage;

public class CuboidEnergyStorage extends EnergyStorage { //implements INBTSerializable<Tag> {

    public CuboidEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public CuboidEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    protected void onEnergyChanged() {

    }

    public void setEnergy(int energy) {
        this.energy = energy;
        onEnergyChanged();
    }

    public void addEnergy(int energy, int maxCapacity) {
        this.energy += energy;
        this.capacity = maxCapacity;
        if (this.energy > this.capacity) {
            this.energy = this.capacity;
        }
        onEnergyChanged();
    }

    public void consumeEnergy(int energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
        onEnergyChanged();
    }

    @Override
    public Tag serializeNBT() {
       CompoundTag nbt = new CompoundTag();
        nbt.putInt("energy", getEnergyStored());
        nbt.putInt("capacity", this.capacity);
        nbt.putInt("maxExt", this.maxExtract);
        nbt.putInt("maxRec", this.maxReceive);
        return nbt;
    }

    @Override
    public void deserializeNBT(Tag tag) {
        CompoundTag nbt = (CompoundTag) tag;
        setEnergy(nbt.getInt("energy"));
        this.capacity = nbt.getInt("capacity");
        this.maxExtract = nbt.getInt("maxExt");
        this.maxReceive = nbt.getInt("maxRec");
        // super.deserializeNBT(nbt);
    }
}