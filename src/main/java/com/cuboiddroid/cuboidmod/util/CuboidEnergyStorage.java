package com.cuboiddroid.cuboidmod.util;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class CuboidEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {

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
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("energy", getEnergyStored());
        tag.putInt("capacity", this.capacity);
        tag.putInt("maxExt", this.maxExtract);
        tag.putInt("maxRec", this.maxReceive);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setEnergy(nbt.getInt("energy"));
        this.capacity = nbt.getInt("capacity");
        this.maxExtract = nbt.getInt("maxExt");
        this.maxReceive = nbt.getInt("maxRec");
    }
}