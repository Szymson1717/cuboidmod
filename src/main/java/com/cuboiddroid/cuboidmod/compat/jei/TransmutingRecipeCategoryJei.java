package com.cuboiddroid.cuboidmod.compat.jei;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;
import com.cuboiddroid.cuboidmod.modules.transmuter.screen.QuantumTransmutationChamberScreen;
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
import net.minecraft.network.chat.Component;

import java.util.Arrays;

public class TransmutingRecipeCategoryJei implements IRecipeCategory<TransmutingRecipe> {
    private static final int GUI_START_X = 24;
    private static final int GUI_START_Y = 28;
    private static final int GUI_WIDTH = 170 - GUI_START_X;
    private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final IDrawableAnimated energyBar;
    private final Component localizedName;

    public TransmutingRecipeCategoryJei(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(QuantumTransmutationChamberScreen.GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
        icon = guiHelper.createDrawableItemStack(new ItemStack(ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER.get()));
        arrow = guiHelper.drawableBuilder(QuantumTransmutationChamberScreen.GUI, 184, 0, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        energyBar = guiHelper.drawableBuilder(QuantumTransmutationChamberScreen.GUI, 176, 0, 8, 36)
                .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
        localizedName = Component.translatable("jei.category." + CuboidMod.MOD_ID + ".transmuting");
    }

    private static void renderScaledTextWithShadow(GuiGraphics guiGraphics, Font font, Component text, int x, int y, int width, float scale, int color) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        float xOffset = (width / scale - font.width(text)) / 2;
        guiGraphics.drawString(font, text, (int) (xOffset + x / scale), (int) (y / scale), color, false);
        poseStack.popPose();
    }

    @Override
    public RecipeType<TransmutingRecipe> getRecipeType() {
        return CuboidModJeiPlugin.TRANSMUTING;
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
    // public void setIngredients(TransmutingRecipe recipe, IIngredients ingredients) {
    //     ingredients.setInputIngredients(new ArrayList<>(recipe.getIngredients()));
    //     ingredients.setOutputs(VanillaTypes.ITEM, Collections.singletonList(recipe.getResultItem().copy()));
    // }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TransmutingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 29, 5).addItemStacks(Arrays.asList(recipe.getIngredients().get(0).getItems()));
        builder.addSlot(RecipeIngredientRole.INPUT, 29, 27).addItemStacks(Arrays.asList(recipe.getIngredients().get(1).getItems()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 15).addItemStack(recipe.getResultItem(RegistryAccess.EMPTY).copy());
    }

    // @Override
    // public void setRecipe(IRecipeLayout recipeLayout, TransmutingRecipe recipe, IIngredients ingredients) {
    //     IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
    //     itemStacks.init(0, true, 28, 4);
    //     itemStacks.init(1, true, 28, 26);
    //     itemStacks.init(2, false, 90, 14);

    //     AtomicInteger i = new AtomicInteger();
    //     recipe.getIngredients().forEach(ing -> itemStacks.set(i.getAndIncrement(), Arrays.asList(ing.getItems())));

    //     itemStacks.set(2, recipe.getResultItem().copy());
    // }

    @Override
    public void draw(TransmutingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics matrix, double mouseX, double mouseY) {
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
    }
}
