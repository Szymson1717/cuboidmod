package com.cuboiddroid.cuboidmod.compat.jei;

import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.modules.collapser.screen.QuantumCollapserScreenBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.util.Constants;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.Arrays;
import java.util.Collections;

public class CollapsingRecipeCategoryJei implements IRecipeCategory<QuantumCollapsingRecipe> {
    private static final int GUI_START_X = 24;
    private static final int GUI_START_Y = 28;
    private static final int GUI_WIDTH = 170 - GUI_START_X;
    private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final IDrawableAnimated itemBar;
    private final Component localizedName;

    public CollapsingRecipeCategoryJei(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(QuantumCollapserScreenBase.GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.THATLDU_QUANTUM_COLLAPSER.get()));
        arrow = guiHelper.drawableBuilder(QuantumCollapserScreenBase.GUI, 184, 0, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        itemBar = guiHelper.drawableBuilder(QuantumCollapserScreenBase.GUI, 176, 0, 8, 36)
                .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
        localizedName = new TranslatableComponent("jei.category.cuboidmod.collapsing");
    }

    private static void renderScaledTextWithShadow(PoseStack matrix, Font Font, Component text, int x, int y, int width, float scale, int color) {
        matrix.pushPose();
        matrix.scale(scale, scale, scale);
        float xOffset = (width / scale - Font.width(text)) / 2;
        Font.drawShadow(matrix, text, xOffset + x / scale, y / scale, color);
        matrix.popPose();
    }

    @Override
    public ResourceLocation getUid() {
        return Constants.COLLAPSING;
    }

    @Override
    public Class<? extends QuantumCollapsingRecipe> getRecipeClass() {
        return QuantumCollapsingRecipe.class;
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
    // public void setIngredients(QuantumCollapsingRecipe recipe, IIngredients ingredients) {
    //     ingredients.setInputIngredients(Collections.singletonList(recipe.getIngredient()));
    //     ingredients.setOutputs(VanillaTypes.ITEM, Collections.singletonList(recipe.getResultItem()));
    // }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, QuantumCollapsingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 28, 14).addItemStacks(Arrays.asList(recipe.getIngredient().getItems()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 87, 14).addItemStack(recipe.getResultItem().copy());
    }

    // @Override
    // public void setRecipe(IRecipeLayout recipeLayout, QuantumCollapsingRecipe recipe, IIngredients ingredients) {
    //     IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
    //     itemStacks.init(0, true, 28, 14);
    //     itemStacks.init(1, false, 87, 14);

    //     // Should only be one ingredient...
    //     itemStacks.set(0, Arrays.asList(recipe.getIngredient().getItems()));
    //     // Output
    //     itemStacks.set(1, recipe.getResultItem().copy());
    // }

    @Override
    public void draw(QuantumCollapsingRecipe recipe, PoseStack PoseStack, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;

        // arrow
        arrow.draw(PoseStack, 78 - GUI_START_X, 43 - GUI_START_Y);

        int workSeconds = recipe.getWorkTicks() / 20;
        int workDecimal = (recipe.getWorkTicks() % 20) / 2;
        String arrowText = "" + workSeconds + "." + workDecimal + " s";
        renderScaledTextWithShadow(PoseStack, font, new TextComponent(arrowText), 78 - GUI_START_X, 61 - GUI_START_Y, 24, 0.6f, 0xFFFFFF);

        // required amounts
        itemBar.draw(PoseStack, 32 - GUI_START_X, 34 - GUI_START_Y);

        String itemBarText = "" + recipe.getRequiredInputAmount();
        renderScaledTextWithShadow(PoseStack, font, new TextComponent(itemBarText), 32 - GUI_START_X, 71 - GUI_START_Y, 8, 0.6f, 0xFFFFFF);
    }
}
