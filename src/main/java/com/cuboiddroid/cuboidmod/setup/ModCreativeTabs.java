package com.cuboiddroid.cuboidmod.setup;

import net.minecraft.world.item.ItemStack;

import com.cuboiddroid.cuboidmod.CuboidMod;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import com.cuboiddroid.cuboidmod.modules.collapser.item.QuantumSingularityItem;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularityRegistry;

import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    
    public static final RegistryObject<CreativeModeTab> CUBOIDMOD_ITEM_GROUP = Registration.TAB.register(CuboidMod.MOD_ID + ".generic", () -> CreativeModeTab
            .builder().icon(() -> new ItemStack(ModBlocks.THATLDU_BLOCK.get()))
            .displayItems((params, output) -> {
                Registration.ITEMS.getEntries().stream()
                    .filter(item -> (!(item.get() instanceof QuantumSingularityItem)))
                    .forEach(item -> output.accept(item.get()));
            }).title(Component.translatable("itemGroup." + CuboidMod.MOD_ID))
            .build());

    public static final RegistryObject<CreativeModeTab> CUBOIDMOD_SINGULARITIES_ITEM_GROUP = Registration.TAB.register(CuboidMod.MOD_ID + ".singularities", () -> CreativeModeTab
            .builder().icon(() -> {
                ResourceLocation thatlduIdentifier = new ResourceLocation(CuboidMod.MOD_ID, "thatldu");
                ItemStack itemstack = new ItemStack(ModItems.QUANTUM_SINGULARITY.get(), 1);
                itemstack.getOrCreateTag().putString(QuantumSingularityItem.QUANTUM_ID, thatlduIdentifier.toString());
                return itemstack;
            }).displayItems((params, output) -> {
                QuantumSingularityRegistry.getInstance().getSingularities().forEach(singularity -> {
                    ItemStack tempSingularity = new ItemStack(ModItems.QUANTUM_SINGULARITY.get());
                    tempSingularity.getOrCreateTag().putString(QuantumSingularityItem.QUANTUM_ID, singularity.getId().toString());
                    output.accept(tempSingularity);
                });
            }).title(Component.translatable("itemGroup." + CuboidMod.MOD_ID + ".singularities"))
            .build());

    static void register() {
    }
}
