package com.cuboiddroid.cuboidmod.setup;

import java.util.concurrent.Callable;

import com.cuboiddroid.cuboidmod.Config;

public enum ModGeneratorTiers {
    
    NOTSOGUDIUM(
        () -> Config.notsogudiumQuantumCollapserSpeed.get().floatValue(),

        () -> Config.notsogudiumSingularityResourceGeneratorTicksPerOperation.get(),
        () -> Config.notsogudiumSingularityResourceGeneratorItemsPerOperation.get(),

        () -> Config.notsogudiumSingularityPowerGeneratorEnergyCapacity.get(),
        () -> Config.notsogudiumSingularityPowerGeneratorTicksPerCycle.get(),
        () -> Config.notsogudiumSingularityPowerGeneratorBaseEnergyGenerated.get(),
        () -> Config.notsogudiumSingularityPowerGeneratorMaxEnergyOutputPerTick.get()
    ),

    KUDBEBEDDA(
        () -> Config.kudbebeddaQuantumCollapserSpeed.get().floatValue(),

        () -> Config.kudbebeddaSingularityResourceGeneratorTicksPerOperation.get(),
        () -> Config.kudbebeddaSingularityResourceGeneratorItemsPerOperation.get(),

        () -> Config.kudbebeddaSingularityPowerGeneratorEnergyCapacity.get(),
        () -> Config.kudbebeddaSingularityPowerGeneratorTicksPerCycle.get(),
        () -> Config.kudbebeddaSingularityPowerGeneratorBaseEnergyGenerated.get(),
        () -> Config.kudbebeddaSingularityPowerGeneratorMaxEnergyOutputPerTick.get()
    ),

    NOTARFBADIUM(
        () -> Config.notarfbadiumQuantumCollapserSpeed.get().floatValue(),

        () -> Config.notarfbadiumSingularityResourceGeneratorTicksPerOperation.get(),
        () -> Config.notarfbadiumSingularityResourceGeneratorItemsPerOperation.get(),

        () -> Config.notarfbadiumSingularityPowerGeneratorEnergyCapacity.get(),
        () -> Config.notarfbadiumSingularityPowerGeneratorTicksPerCycle.get(),
        () -> Config.notarfbadiumSingularityPowerGeneratorBaseEnergyGenerated.get(),
        () -> Config.notarfbadiumSingularityPowerGeneratorMaxEnergyOutputPerTick.get()
    ),

    WIKIDIUM(
        () -> Config.wikidiumQuantumCollapserSpeed.get().floatValue(),

        () -> Config.wikidiumSingularityResourceGeneratorTicksPerOperation.get(),
        () -> Config.wikidiumSingularityResourceGeneratorItemsPerOperation.get(),

        () -> Config.wikidiumSingularityPowerGeneratorEnergyCapacity.get(),
        () -> Config.wikidiumSingularityPowerGeneratorTicksPerCycle.get(),
        () -> Config.wikidiumSingularityPowerGeneratorBaseEnergyGenerated.get(),
        () -> Config.wikidiumSingularityPowerGeneratorMaxEnergyOutputPerTick.get()
    ),

    THATLDU(
        () -> Config.thatlduQuantumCollapserSpeed.get().floatValue(),

        () -> Config.thatlduSingularityResourceGeneratorTicksPerOperation.get(),
        () -> Config.thatlduSingularityResourceGeneratorItemsPerOperation.get(),

        () -> Config.thatlduSingularityPowerGeneratorEnergyCapacity.get(),
        () -> Config.thatlduSingularityPowerGeneratorTicksPerCycle.get(),
        () -> Config.thatlduSingularityPowerGeneratorBaseEnergyGenerated.get(),
        () -> Config.thatlduSingularityPowerGeneratorMaxEnergyOutputPerTick.get()
    );


    private final Callable<Float> collapserSpeed;
    private final Callable<Integer> ticksPerOperation;
    private final Callable<Integer> itemsPerOperation;
    private final Callable<Integer> energyCapacity;
    private final Callable<Integer> ticksPerCycle;
    private final Callable<Integer> baseEnergyGenerated;
    private final Callable<Integer> maxEnergyOutputPerTick;


    ModGeneratorTiers(
        // Quantum Collapser
        Callable<Float> collapserSpeed,

        // Resource Generation
        Callable<Integer> ticksPerOperation,
        Callable<Integer> itemsPerOperation,

        // Power Generation
        Callable<Integer> energyCapacity,
        Callable<Integer> ticksPerCycle,
        Callable<Integer> baseEnergyGenerated,
        Callable<Integer> maxEnergyOutputPerTick
    ) {
        this.collapserSpeed = collapserSpeed;
        this.ticksPerOperation = ticksPerOperation;
        this.itemsPerOperation = itemsPerOperation;
        this.energyCapacity = energyCapacity;
        this.ticksPerCycle = ticksPerCycle;
        this.baseEnergyGenerated = baseEnergyGenerated;
        this.maxEnergyOutputPerTick = maxEnergyOutputPerTick;
    }


    public Float getCollapserSpeed() {
        try {
            return collapserSpeed.call();
        } catch (Exception e) {
            return 0f;
        }
    }

    public Integer getTicksPerOperation() {
        try {
            return ticksPerOperation.call();
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer getItemsPerOperation() {
        try {
            return itemsPerOperation.call();
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer getEnergyCapacity() {
        try {
            return energyCapacity.call();
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer getTicksPerCycle() {
        try {
            return ticksPerCycle.call();
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer getBaseEnergyGenerated() {
        try {
            return baseEnergyGenerated.call();
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer getMaxEnergyOutputPerTick() {
        try {
            return maxEnergyOutputPerTick.call();
        } catch (Exception e) {
            return 0;
        }
    }


}
