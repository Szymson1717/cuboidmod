package com.cuboiddroid.cuboidmod.compat.jei;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

public class DryingRecipeCategoryJei implements IRecipeCategory<DryingRecipe> {
    private static final ResourceLocation GUI = new ResourceLocation(CuboidMod.MOD_ID, "textures/gui/drying_cupboard_jei.png");

    private static final int GUI_START_X = 10;
    private static final int GUI_START_Y = 15;
    private static final int GUI_WIDTH = 170 - GUI_START_X;
    private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated energyBar;
    private final IDrawableAnimated dryingBar;
    private final Component localizedName;

    public DryingRecipeCategoryJei(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
        icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.DRYING_CUPBOARD.get()));
        energyBar = guiHelper.drawableBuilder(GUI, 176, 0, 8, 53)
                .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
        dryingBar = guiHelper.drawableBuilder(GUI, 184, 0, 6, 13)
                .buildAnimated(200, IDrawableAnimated.StartDirection.TOP, false);
        localizedName = Component.translatable("jei.category.cuboidmod.drying");
    }

    private static void renderScaledTextWithShadow(PoseStack matrix, Font font, Component text, int x, int y, int width, float scale, int color) {
        matrix.pushPose();
        matrix.scale(scale, scale, scale);
        float xOffset = (width / scale - font.width(text)) / 2;
        // font.drawShadow(matrix, text, xOffset + x / scale, y / scale, color);
        matrix.popPose();
    }

    private static void renderScaledText(PoseStack matrix, Font fibt, Component text, int x, int y, float scale, int color) {
        matrix.pushPose();
        matrix.scale(scale, scale, scale);
        // font.draw(matrix, text, x / scale, y / scale, color);
        matrix.popPose();
    }

    @Override
    public RecipeType<DryingRecipe> getRecipeType() {
        return CuboidModJeiPlugin.DRYING;
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
    // public void setIngredients(DryingRecipe recipe, IIngredients ingredients) {
    //     ingredients.setInputIngredients(Collections.singletonList(recipe.getIngredient()));
    //     ingredients.setOutputs(VanillaTypes.ITEM, Collections.singletonList(recipe.getResultItem()));
    // }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DryingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 25-GUI_START_X, 19-GUI_START_Y).addItemStacks(Arrays.asList(recipe.getIngredient().getItems()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 25-GUI_START_X, 54-GUI_START_Y).addItemStack(recipe.getResultItem(RegistryAccess.EMPTY).copy());
    }

    // @Override
    // public void setRecipe(IRecipeLayout recipeLayout, DryingRecipe recipe, IIngredients ingredients) {
    //     IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
    //     itemStacks.init(0, true, 25-GUI_START_X, 19-GUI_START_Y);
    //     itemStacks.init(1, false, 25-GUI_START_X, 54-GUI_START_Y);

    //     // Should only be one ingredient...
    //     itemStacks.set(0, Arrays.asList(recipe.getIngredient().getItems()));
    //     // Output
    //     itemStacks.set(1, recipe.getResultItem().copy());
    // }

    @Override
    public void draw(DryingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics matrix, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;

        // energy
        energyBar.draw(matrix, 12 - GUI_START_X, 19 - GUI_START_Y);

        // drying time bar
        dryingBar.draw(matrix, 31 - GUI_START_X, 39 - GUI_START_Y);

        int workSeconds = recipe.getWorkTicks() / 20;
        int workDecimal = (recipe.getWorkTicks() % 20) / 2;
        String dryingTimeText = "" + workSeconds + "." + workDecimal + " s";
        renderScaledTextWithShadow(matrix.pose(), font, Component.literal(dryingTimeText), 40 - GUI_START_X, 42 - GUI_START_Y, 24, 0.8f, 0xFFFFFF);
    }
}
