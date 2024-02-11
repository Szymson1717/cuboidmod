package com.cuboiddroid.cuboidmod.compat.jei;

// import com.cuboiddroid.cuboidmod.Config;
// import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
// import com.cuboiddroid.cuboidmod.modules.powergen.screen.SingularityPowerGeneratorScreenBase;
// import com.cuboiddroid.cuboidmod.setup.ModBlocks;
// import com.cuboiddroid.cuboidmod.util.Constants;
// import com.mojang.blaze3d.matrix.MatrixStack;
// import mezz.jei.api.gui.IRecipeLayout;
// import mezz.jei.api.gui.drawable.IDrawable;
// import mezz.jei.api.gui.drawable.IDrawableAnimated;
// import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
// import mezz.jei.api.helpers.IGuiHelper;
// import mezz.jei.api.ingredients.IIngredients;
// import mezz.jei.api.recipe.category.IRecipeCategory;
// import net.minecraft.client.Minecraft;
// import net.minecraft.client.gui.FontRenderer;
// import net.minecraft.world.item.ItemStack;
// import net.minecraft.util.IReorderingProcessor;
// import net.minecraft.resources.ResourceLocation;
// import net.minecraft.network.chat.StringTextComponent;
// import net.minecraft.network.chat.TranslatableComponent;

// import java.util.Arrays;
// import java.util.Collections;

public class PowerGeneratingRecipeCategoryJei {//implements IRecipeCategory<PowerGeneratingRecipe> {
    // private static final int GUI_START_X = 10;
    // private static final int GUI_START_Y = 28;
    // private static final int GUI_WIDTH = 170 - GUI_START_X;
    // private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    // private final IDrawable background;
    // private final IDrawable icon;
    // private final IDrawableAnimated energyBar;
    // private final String localizedName;
    // private final float notsogudiumEnergyPerTick;
    // private final float kudbebeddaEnergyPerTick;
    // private final float notarfbadiumEnergyPerTick;
    // private final float wikidiumEnergyPerTick;
    // private final float thatlduEnergyPerTick;

    // public PowerGeneratingRecipeCategoryJei(IGuiHelper guiHelper) {
    //     background = guiHelper.createDrawable(SingularityPowerGeneratorScreenBase.GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
    //     icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get()));
    //     energyBar = guiHelper.drawableBuilder(SingularityPowerGeneratorScreenBase.GUI, 176, 0, 8, 36)
    //             .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
    //     localizedName = new TranslatableComponent("jei.category.cuboidmod.power_generating").getString();

    //     notsogudiumEnergyPerTick =
    //             Config.notsogudiumSingularityPowerGeneratorBaseEnergyGenerated.get();

    //     kudbebeddaEnergyPerTick =
    //             Config.kudbebeddaSingularityPowerGeneratorBaseEnergyGenerated.get();

    //     notarfbadiumEnergyPerTick =
    //             Config.notarfbadiumSingularityPowerGeneratorBaseEnergyGenerated.get();

    //     wikidiumEnergyPerTick =
    //             Config.wikidiumSingularityPowerGeneratorBaseEnergyGenerated.get();

    //     thatlduEnergyPerTick =
    //             Config.thatlduSingularityPowerGeneratorBaseEnergyGenerated.get();
    // }

    // private static void renderScaledTextWithShadow(MatrixStack matrix, FontRenderer fontRenderer, IReorderingProcessor text, int x, int y, int width, float scale, int color) {
    //     matrix.pushPose();
    //     matrix.scale(scale, scale, scale);
    //     float xOffset = (width / scale - fontRenderer.width(text)) / 2;
    //     fontRenderer.drawShadow(matrix, text, xOffset + x / scale, y / scale, color);
    //     matrix.popPose();
    // }

    // private static void renderScaledText(MatrixStack matrix, FontRenderer fontRenderer, IReorderingProcessor text, int x, int y, float scale, int color) {
    //     matrix.pushPose();
    //     matrix.scale(scale, scale, scale);
    //     fontRenderer.draw(matrix, text, x / scale, y / scale, color);
    //     matrix.popPose();
    // }

    // @Override
    // public ResourceLocation getUid() {
    //     return Constants.POWER_GENERATING;
    // }

    // @Override
    // public Class<? extends PowerGeneratingRecipe> getRecipeClass() {
    //     return PowerGeneratingRecipe.class;
    // }

    // @Override
    // public String getTitle() {
    //     return localizedName;
    // }

    // @Override
    // public IDrawable getBackground() {
    //     return background;
    // }

    // @Override
    // public IDrawable getIcon() {
    //     return icon;
    // }

    // @Override
    // public void setIngredients(PowerGeneratingRecipe recipe, IIngredients ingredients) {
    //     ingredients.setInputIngredients(Collections.singletonList(recipe.getSingularity()));
    // }

    // @Override
    // public void setRecipe(IRecipeLayout recipeLayout, PowerGeneratingRecipe recipe, IIngredients ingredients) {
    //     IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
    //     itemStacks.init(0, true, 50, 14);

    //     // Should only be one ingredient...
    //     itemStacks.set(0, Arrays.asList(recipe.getSingularity().getItems()));
    //     // Output - no item output - just energy
    // }

    // @Override
    // public void draw(PowerGeneratingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
    //     FontRenderer font = Minecraft.getInstance().font;

    //     // energy
    //     energyBar.draw(matrixStack, 33 - GUI_START_X, 34 - GUI_START_Y);

    //     float multiplier = recipe.getPowerMultiplier();
    //     String energyText = "" + multiplier + "x";
    //     renderScaledTextWithShadow(matrixStack, font, new StringTextComponent(energyText).getVisualOrderText(), 33 - GUI_START_X, 71 - GUI_START_Y, 8, 0.6f, 0xFFFFFF);

    //     renderScaledText(matrixStack, font, new StringTextComponent("Notso.: " + String.format("%.02f", notsogudiumEnergyPerTick * multiplier) + " FE/t").getVisualOrderText(), 74, 2, 0.6F, 0x444444);
    //     renderScaledText(matrixStack, font, new StringTextComponent("Kudbe.: " + String.format("%.02f", kudbebeddaEnergyPerTick * multiplier) + " FE/t").getVisualOrderText(), 74, 11, 0.6F, 0x444444);
    //     renderScaledText(matrixStack, font, new StringTextComponent("Notarf.: " + String.format("%.02f", notarfbadiumEnergyPerTick * multiplier) + " FE/t").getVisualOrderText(), 74, 20, 0.6F, 0x444444);
    //     renderScaledText(matrixStack, font, new StringTextComponent("Wikid.: " + String.format("%.02f", wikidiumEnergyPerTick * multiplier) + " FE/t").getVisualOrderText(), 74, 29, 0.6F, 0x444444);
    //     renderScaledText(matrixStack, font, new StringTextComponent("Thatl.: " + String.format("%.02f", thatlduEnergyPerTick * multiplier) + " FE/t").getVisualOrderText(), 74, 38, 0.6F, 0x444444);
    // }
}
