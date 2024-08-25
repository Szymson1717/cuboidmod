package com.cuboiddroid.cuboidmod.modules.collapser.item;

import java.util.List;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularity;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularityRegistry;
import com.cuboiddroid.cuboidmod.modules.collapser.recipe.CollapsingRecipeData;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipeData;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipeData;
import com.cuboiddroid.cuboidmod.setup.ModGeneratorTiers;
import com.cuboiddroid.cuboidmod.util.IColored;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;    
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

public class QuantumSingularityItem extends Item implements IColored {
    private ResourceLocation forcedSingularity = null;
    public static final String QUANTUM_ID = "quantumId";

    public QuantumSingularityItem() {
        super(new Properties());
    }

    public QuantumSingularityItem(ResourceLocation forcedSingularity) {
        this();
        this.forcedSingularity = forcedSingularity;
    }

    @Override
    public String getDescriptionId() {
        return CuboidMod.MOD_ID + ".quantum_singularity.unknown";
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Component getName(ItemStack stack) {
        if (hasQuantumItem(stack)) {
            if (canProduceItems(stack)) {
                QuantumSingularity singularity = getSingularity(stack);
                Item productionItem = ForgeRegistries.ITEMS.getValue(singularity.getProduction().output);
                String translatedName = I18n.get(productionItem.getDescriptionId());
                return Component.translatable(CuboidMod.MOD_ID + ".quantum_singularity", translatedName);
            }
            
            return Component.translatable(CuboidMod.MOD_ID + ".quantum_singularity.unknown");
        }

        return Component.translatable(CuboidMod.MOD_ID + ".quantum_singularity.missing");
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        QuantumSingularity singularity = getSingularity(stack);
        Style primaryColor = Style.EMPTY.withColor(singularity.getOverlayColor());
        Style secondaryColor = Style.EMPTY.withColor(singularity.getUnderlayColor());

        long time = System.currentTimeMillis() / 1000;

        if (singularity.isDisabled())
            tooltip.add(Component.literal("(Disabled Singularity)").withStyle(ChatFormatting.RED));


        if (!showingExtras()) {
            Minecraft minecraft = Minecraft.getInstance();
            KeyMapping shiftKey = minecraft.options.keyShift;
            String keyName = I18n.get(shiftKey.getName()).toUpperCase();

            final Component ITEM_INFO_PREFIX = Component.translatable(CuboidMod.MOD_ID + ".hover_text.more_info_prefix");
            final Component ITEM_INFO_SUFFIX = Component.translatable(CuboidMod.MOD_ID + ".hover_text.more_info_suffix");

            final Component ITEM_ACTIVATE = Component.empty().withStyle(primaryColor)
                .append(Component.literal(" ["))
                .append(Component.literal(keyName).withStyle(ChatFormatting.BOLD))
                .append(Component.literal("] "));

            tooltip.add(
                Component.empty().withStyle(ChatFormatting.GRAY).append(ITEM_INFO_PREFIX).append(ITEM_ACTIVATE).append(ITEM_INFO_SUFFIX)
            );
        } else {
            final Component KEY_MATCH = Component.literal(" * ").withStyle(ChatFormatting.RED);

            ModGeneratorTiers[] tiers = ModGeneratorTiers.values();
            ModGeneratorTiers productionTier = tiers[(int) (time / 2) % tiers.length];

            if (singularity.getRecipe() != null) {
                CollapsingRecipeData recipeData = singularity.getRecipe();
                final Component RECIPE_INFO = Component.translatable(CuboidMod.MOD_ID + ".hover_text.recipe");
                final String inputCount = String.valueOf(recipeData.recipeCount);

                String itemKey = null;
                
                if (recipeData.usesTag) {
                    final TagKey<Item> itemTags = TagKey.create(Registries.ITEM, recipeData.recipeInputTag);
                    final ITag<Item> itemTag = ForgeRegistries.ITEMS.tags().getTag(itemTags);
                    final List<Item> validItems = ForgeRegistries.ITEMS.getEntries().stream()
                        .map(e -> e.getValue()).filter(i -> itemTag.contains(i)).toList();

                    if (validItems.size() > 0) {
                        final Item item = validItems.get((int) time % validItems.size());
                        if (!item.equals(Items.AIR)) itemKey = item.getDescriptionId();
                    }
                } else {
                    final Item item = ForgeRegistries.ITEMS.getValue(recipeData.recipeInput);
                    if (!item.equals(Items.AIR)) itemKey = item.getDescriptionId();
                }

                if (itemKey != null) {
                    final String inputValue = I18n.get(itemKey);
    
                    final Component ITEM_INPUT = Component.empty().withStyle(secondaryColor)
                        .append(" (x" + inputCount + ") ").append(Component.literal(inputValue).withStyle(primaryColor));
    
                    tooltip.add(Component.empty().withStyle(ChatFormatting.GRAY).append(RECIPE_INFO));
                    tooltip.add(Component.empty().withStyle(ChatFormatting.GRAY).append(" -").append(ITEM_INPUT));
                }
            }

            if (singularity.getProduction() != null) {
                ResourceGeneratingRecipeData recipeData = singularity.getProduction();
                final Component PRODUCTION_INFO = Component.translatable(CuboidMod.MOD_ID + ".hover_text.production");
                
                final Item product = ForgeRegistries.ITEMS.getValue(recipeData.output);
                final String productAmount = String.valueOf(recipeData.outputMult * productionTier.getItemsPerOperation());
                final String itemKey = product.getDescriptionId();
                final String outputValue = I18n.get(itemKey);

                if (!product.equals(Items.AIR)) {
                    final Component ITEM_OUTPUT = Component.empty().withStyle(secondaryColor)
                        .append(" (x" + productAmount + ") ").append(Component.literal(outputValue).withStyle(primaryColor));
    
                    tooltip.add(Component.empty().withStyle(ChatFormatting.GRAY).append(PRODUCTION_INFO).append(KEY_MATCH));
                    tooltip.add(Component.empty().withStyle(ChatFormatting.GRAY).append(" -").append(ITEM_OUTPUT));
                }
            }

            if (singularity.getPowerOutput() != null) {
                PowerGeneratingRecipeData recipeData = singularity.getPowerOutput();
                int powerOutput = (int) (recipeData.powerOutput * productionTier.getMaxEnergyOutputPerTick());

                final Component POWER_INFO = Component.translatable(CuboidMod.MOD_ID + ".hover_text.power");
                final Component POWER_PER_TICK = Component.literal(" ").withStyle(primaryColor)
                    .append(String.valueOf(powerOutput))
                    .append(Component.literal("/").withStyle(secondaryColor))
                    .append("⚡");
                

                tooltip.add(Component.empty().withStyle(ChatFormatting.GRAY).append(POWER_INFO).append(KEY_MATCH));
                tooltip.add(Component.empty().withStyle(ChatFormatting.GRAY).append(" -").append(POWER_PER_TICK));
            }

            final Component TIER_INFO = Component.translatable(CuboidMod.MOD_ID + ".hover_text.production_info").withStyle(ChatFormatting.ITALIC);
            final Component GENERATOR_TIER = Component.translatable(CuboidMod.MOD_ID + ".hover_text." + productionTier.name().toLowerCase() + "_tier");

            tooltip.add(Component.empty());
            tooltip.add(Component.empty().withStyle(ChatFormatting.RED).append(KEY_MATCH).append(TIER_INFO).append(" "));
            tooltip.add(Component.empty().withStyle(ChatFormatting.GRAY).append("   - ").append(GENERATOR_TIER));


            if (flag.isAdvanced()) {
                if (forcedSingularity != null) {
                    tooltip.add(Component.literal("(Outdated Singularity)").withStyle(ChatFormatting.DARK_GRAY));
                }
            }
        }

        // if (flag.isAdvanced()) {
        //     if (forcedSingularity != null) {
        //         tooltip.add(Component.literal("(Outdated Singularity)").withStyle(ChatFormatting.RED));
        //     }

        //     ResourceLocation quantumItem = getQuantumIdentifier(stack);
        //     tooltip.add(Component.literal("Quantum Singularity ID: " + quantumItem.toString()));
        // }
    }

    @Override
    public int getColor(int tintIndex, ItemStack stack) {
        QuantumSingularity quantumSingularity = getSingularity(stack);

        if (showingExtras() && canProduceItems(stack)) return -1;

        return switch (tintIndex) {
            case 0 -> quantumSingularity.getUnderlayColor();
            case 1 -> quantumSingularity.getOverlayColor();
            default -> -1;
        };
    }

    public boolean showingExtras() {
        Minecraft minecraft = Minecraft.getInstance();
        long window = Minecraft.getInstance().getWindow().getWindow();
        KeyMapping shiftKey = minecraft.options.keyShift;
        return InputConstants.isKeyDown(window, shiftKey.getKey().getValue());
    }

    public boolean hasQuantumItem(ItemStack stack) {
        ResourceLocation singularity = getQuantumIdentifier(stack);
        return QuantumSingularityRegistry.getInstance().hasSingularity(singularity);
    }

    public ResourceLocation getQuantumIdentifier(ItemStack stack) {
        if (forcedSingularity != null)
            return forcedSingularity;

        CompoundTag tag = stack.getTag();
        if (tag != null) {
            String quantumItemId = tag.getString(QuantumSingularityItem.QUANTUM_ID);
            ResourceLocation key = ResourceLocation.tryParse(quantumItemId);
            if (key != null)
                return key;
        }

        return new ResourceLocation("air");
    }

    public QuantumSingularity getSingularity(ItemStack stack) {
        ResourceLocation identifier = getQuantumIdentifier(stack);
        return QuantumSingularityRegistry.getInstance().getSingularity(identifier);
    }

    public boolean canProduceItems(ItemStack stack) {
        QuantumSingularity singularity = getSingularity(stack);
        if (singularity.getProduction() == null) return false;
        Item item = ForgeRegistries.ITEMS.getValue(singularity.getProduction().output);

        return item != null && !item.equals(Items.AIR);
    }
}