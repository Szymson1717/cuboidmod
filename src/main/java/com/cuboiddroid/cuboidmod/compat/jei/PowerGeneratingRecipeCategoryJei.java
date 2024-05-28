package com.cuboiddroid.cuboidmod.compat.jei;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.powergen.screen.SingularityPowerGeneratorScreenBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.GuiGraphics;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

public class PowerGeneratingRecipeCategoryJei implements IRecipeCategory<PowerGeneratingRecipe> {
    private static final int GUI_START_X = 10;
    private static final int GUI_START_Y = 28;
    private static final int GUI_WIDTH = 170 - GUI_START_X;
    private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated energyBar;
    private final Component localizedName;
    private final float notsogudiumEnergyPerTick;
    private final float kudbebeddaEnergyPerTick;
    private final float notarfbadiumEnergyPerTick;
    private final float wikidiumEnergyPerTick;
    private final float thatlduEnergyPerTick;

    public PowerGeneratingRecipeCategoryJei(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(SingularityPowerGeneratorScreenBase.GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
        icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get()));
        energyBar = guiHelper.drawableBuilder(SingularityPowerGeneratorScreenBase.GUI, 176, 0, 8, 36)
                .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
        localizedName = Component.translatable("jei.category." + CuboidMod.MOD_ID + ".power_generating");

        notsogudiumEnergyPerTick =
                Config.notsogudiumSingularityPowerGeneratorBaseEnergyGenerated.get();

        kudbebeddaEnergyPerTick =
                Config.kudbebeddaSingularityPowerGeneratorBaseEnergyGenerated.get();

        notarfbadiumEnergyPerTick =
                Config.notarfbadiumSingularityPowerGeneratorBaseEnergyGenerated.get();

        wikidiumEnergyPerTick =
                Config.wikidiumSingularityPowerGeneratorBaseEnergyGenerated.get();

        thatlduEnergyPerTick =
                Config.thatlduSingularityPowerGeneratorBaseEnergyGenerated.get();
    }

    private static void renderScaledTextWithShadow(GuiGraphics guiGraphics, Font font, Component text, int x, int y, int width, float scale, int color) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        float xOffset = (width / scale - font.width(text)) / 2;
        guiGraphics.drawString(font, text, (int) (xOffset + x / scale), (int) (y / scale), color);
        poseStack.popPose();
    }

    private static void renderScaledText(GuiGraphics guiGraphics, Font font, Component text, int x, int y, float scale, int color) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        guiGraphics.drawString(font, text, (int) (x / scale), (int) (y / scale), color);
        poseStack.popPose();
    }

    @Override
    public RecipeType<PowerGeneratingRecipe> getRecipeType() {
        return CuboidModJeiPlugin.POWER_GENERATING;
    }

    @Override
    public Component getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    // @Override
    // public void setIngredients(PowerGeneratingRecipe recipe, IIngredients ingredients) {
    //     ingredients.setInputIngredients(Collections.singletonList(recipe.getSingularity()));
    // }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PowerGeneratingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 14).addItemStacks(Arrays.asList(recipe.getSingularity().getItems()));
    }

    // @Override
    // public void setRecipe(IRecipeLayout recipeLayout, PowerGeneratingRecipe recipe, IIngredients ingredients) {
    //     IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
    //     itemStacks.init(0, true, 50, 14);

    //     // Should only be one ingredient...
    //     itemStacks.set(0, Arrays.asList(recipe.getSingularity().getItems()));
    //     // Output - no item output - just energy
    // }

    @Override
    public void draw(PowerGeneratingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics matrix, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;

        // energy
        energyBar.draw(matrix, 33 - GUI_START_X, 34 - GUI_START_Y);

        float multiplier = recipe.getPowerMultiplier();
        String energyText = multiplier + "x";
        renderScaledTextWithShadow(matrix, font, Component.literal(energyText), 33 - GUI_START_X, 71 - GUI_START_Y, 8, 0.6f, 0xFFFFFF);

        renderScaledText(matrix, font, Component.literal("Notso.: " + String.format("%.02f", notsogudiumEnergyPerTick * multiplier) + " FE/t"), 74, 2, 0.6F, 0x444444);
        renderScaledText(matrix, font, Component.literal("Kudbe.: " + String.format("%.02f", kudbebeddaEnergyPerTick * multiplier) + " FE/t"), 74, 11, 0.6F, 0x444444);
        renderScaledText(matrix, font, Component.literal("Notarf.: " + String.format("%.02f", notarfbadiumEnergyPerTick * multiplier) + " FE/t"), 74, 20, 0.6F, 0x444444);
        renderScaledText(matrix, font, Component.literal("Wikid.: " + String.format("%.02f", wikidiumEnergyPerTick * multiplier) + " FE/t"), 74, 29, 0.6F, 0x444444);
        renderScaledText(matrix, font, Component.literal("Thatl.: " + String.format("%.02f", thatlduEnergyPerTick * multiplier) + " FE/t"), 74, 38, 0.6F, 0x444444);
    }
}
