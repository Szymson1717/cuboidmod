package com.cuboiddroid.cuboidmod.compat.jei;

import com.cuboiddroid.cuboidmod.modules.refinedinscriber.recipe.InscribingRecipe;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.screen.RefinedInscriberScreen;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.util.Constants;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class InscribingRecipeCategoryJei implements IRecipeCategory<InscribingRecipe> {
    private static final int GUI_START_X = 23;
    private static final int GUI_START_Y = 26;
    private static final int GUI_WIDTH = 170 - GUI_START_X;
    private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final IDrawableAnimated energyBar;
    private final Component localizedName;

    public InscribingRecipeCategoryJei(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(RefinedInscriberScreen.GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.REFINED_INSCRIBER.get()));
        arrow = guiHelper.drawableBuilder(RefinedInscriberScreen.GUI, 184, 0, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        energyBar = guiHelper.drawableBuilder(RefinedInscriberScreen.GUI, 176, 0, 8, 36)
                .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
        localizedName = new TranslatableComponent("jei.category.cuboidmod.inscribing");
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
        return Constants.INSCRIBING;
    }

    @Override
    public Class<? extends InscribingRecipe> getRecipeClass() {
        return InscribingRecipe.class;
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

    @Override
    public void setIngredients(InscribingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(new ArrayList<>(recipe.getIngredients()));
        ingredients.setOutputs(VanillaTypes.ITEM, Collections.singletonList(recipe.getResultItem().copy()));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, InscribingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init(0, true, 59 - GUI_START_X, 26 - GUI_START_Y);
        itemStacks.init(1, true, 80 - GUI_START_X, 54 - GUI_START_Y);
        itemStacks.init(2, true, 101 - GUI_START_X, 26 - GUI_START_Y);
        itemStacks.init(3, false, 137 - GUI_START_X, 54 - GUI_START_Y);

        AtomicInteger i = new AtomicInteger();
        recipe.getIngredients().forEach(ing -> itemStacks.set(i.getAndIncrement(), Arrays.asList(ing.getItems())));

        itemStacks.set(3, recipe.getResultItem().copy());
    }

    @Override
    public void draw(InscribingRecipe recipe, PoseStack PoseStack, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;

        // arrow
        arrow.draw(PoseStack, 103 - GUI_START_X, 56 - GUI_START_Y);

        int workSeconds = recipe.getWorkTicks() / 20;
        int workDecimal = (recipe.getWorkTicks() % 20) / 2;
        String arrowText = "" + workSeconds + "." + workDecimal + " s";
        renderScaledTextWithShadow(PoseStack, font, new TextComponent(arrowText), 104 - GUI_START_X, 73 - GUI_START_Y, 24, 0.6f, 0xFFFFFF);

        // energy
        energyBar.draw(PoseStack, 32 - GUI_START_X, 34 - GUI_START_Y);

        String energyText = "" + recipe.getEnergyRequired() + " FE";
        renderScaledTextWithShadow(PoseStack, font, new TextComponent(energyText), 32 - GUI_START_X, 71 - GUI_START_Y, 8, 0.6f, 0xFFFFFF);
    }
}
