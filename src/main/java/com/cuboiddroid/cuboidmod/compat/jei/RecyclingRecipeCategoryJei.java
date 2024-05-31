package com.cuboiddroid.cuboidmod.compat.jei;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.modules.recycler.screen.MolecularRecyclerScreen;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.util.Pair;
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
import java.util.List;
import java.util.stream.IntStream;

public class RecyclingRecipeCategoryJei implements IRecipeCategory<RecyclingRecipe> {
    private static final int GUI_START_X = 24;
    private static final int GUI_START_Y = 28;
    private static final int GUI_WIDTH = 170 - GUI_START_X;
    private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final IDrawableAnimated energyBar;
    private final Component localizedName;

    public RecyclingRecipeCategoryJei(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(MolecularRecyclerScreen.GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
        icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.MOLECULAR_RECYCLER.get()));
        arrow = guiHelper.drawableBuilder(MolecularRecyclerScreen.GUI, 184, 0, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        energyBar = guiHelper.drawableBuilder(MolecularRecyclerScreen.GUI, 176, 0, 8, 36)
                .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
        localizedName = Component.translatable("jei.category." + CuboidMod.MOD_ID + ".recycling");
    }

    private static void renderScaledTextWithShadow(GuiGraphics guiGraphics, Font font, Component text, int x, int y, int width, float scale, int color) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        float xOffset = (width / scale - font.width(text)) / 2;
        guiGraphics.drawString(font, text, (int) (xOffset + x / scale), (int) (y / scale), color);
        poseStack.popPose();
    }

    @Override
    public RecipeType<RecyclingRecipe> getRecipeType() {
        return CuboidModJeiPlugin.RECYCLING;
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
    // public void setIngredients(RecyclingRecipe recipe, IIngredients ingredients) {
    //     ingredients.setInputIngredients(Collections.singletonList(recipe.getIngredient()));
    //     ingredients.setOutputs(VanillaTypes.ITEM, new ArrayList<>(recipe.getPossibleResults(new SimpleContainer(6))));
    // }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecyclingRecipe recipe, IFocusGroup focuses) {
        List<ItemStack> outputs = recipe.getPossibleResultsWithChances().stream().map(pair -> pair.getKey()).toList();
        IntStream.range(0,6-outputs.size()).forEach(value -> outputs.add(ItemStack.EMPTY));

        builder.addSlot(RecipeIngredientRole.INPUT, 28, 14).addItemStacks(Arrays.asList(recipe.getIngredient().getItems()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 85, 6).addItemStack(outputs.get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 6).addItemStack(outputs.get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 121, 6).addItemStack(outputs.get(2));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 85, 24).addItemStack(outputs.get(3));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 24).addItemStack(outputs.get(4));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 121, 24).addItemStack(outputs.get(5));
    }

//     @Override
//     public void setRecipe(IRecipeLayout recipeLayout, RecyclingRecipe recipe, IIngredients ingredients) {
//         IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
//         itemStacks.init(0, true, 28, 14);
//         itemStacks.init(1, false, 85, 6);
//         itemStacks.init(2, false, 103, 6);
//         itemStacks.init(3, false, 121, 6);
//         itemStacks.init(4, false, 85, 24);
//         itemStacks.init(5, false, 103, 24);
//         itemStacks.init(6, false, 121, 24);

//         // Should only be one ingredient...
// //        recipe.getIngredients().forEach(ing -> itemStacks.set(0, Arrays.asList(ing.getMatchingStacks())));
//         itemStacks.set(0, Arrays.asList(recipe.getIngredient().getItems()));
//         // Outputs
//         List<Pair<ItemStack, Float>> results = recipe.getPossibleResultsWithChances();
//         for (int i = 0; i < results.size(); ++i) {
//             itemStacks.set(i + 1, results.get(i).getKey());
//         }
//     }

    @Override
    public void draw(RecyclingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics matrix, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;

        // arrow
        arrow.draw(matrix, 78 - GUI_START_X, 43 - GUI_START_Y);

        int workSeconds = recipe.getWorkTicks() / 20;
        int workDecimal = (recipe.getWorkTicks() % 20) / 2;
        String arrowText = workSeconds + "." + workDecimal + " s";
        renderScaledTextWithShadow(matrix, font, Component.literal(arrowText), 78 - GUI_START_X, 61 - GUI_START_Y, 24, 0.6f, 0xFFFFFF);

        // energy
        energyBar.draw(matrix, 32 - GUI_START_X, 34 - GUI_START_Y);

        String energyText = recipe.getEnergyRequired() + " FE";
        renderScaledTextWithShadow(matrix, font, Component.literal(energyText), 32 - GUI_START_X, 71 - GUI_START_Y, 8, 0.6f, 0xFFFFFF);

        // % chances on outputs
        List<Pair<ItemStack, Float>> results = recipe.getPossibleResultsWithChances();
        for (int i = 0; i < results.size(); ++i) {
            float chance = results.get(i).getValue();
            if (chance < 1) {
                int asPercent = (int) (100 * chance);
                String text = asPercent < 1 ? "<1%" : asPercent + "%";
                renderScaledTextWithShadow(matrix, font, Component.literal(text), 85 + 18 * (i % 3), i < 3 ? 0 : 44, 18, 0.6f, 0xFFFFFF);
            }
        }
    }
}
