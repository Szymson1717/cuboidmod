package com.cuboiddroid.cuboidmod.setup;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    
    public static final RegistryObject<CreativeModeTab> CUBOIDMOD_ITEM_GROUP = Registration.TAB.register("cuboidmod", () -> CreativeModeTab
            .builder().icon(() -> new ItemStack(ModBlocks.THATLDU_BLOCK.get()))
            .displayItems((params, output) -> Registration.ITEMS.getEntries().forEach(item -> output.accept(item.get())))
            .title(Component.translatable("itemGroup.cuboidmod"))
            .build());

    static void register() {
    }
}
