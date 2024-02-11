package com.cuboiddroid.cuboidmod.compat.jei;

// import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;
// import com.cuboiddroid.cuboidmod.modules.transmuter.screen.QuantumTransmutationChamberScreen;
// import com.cuboiddroid.cuboidmod.setup.ModBlocks;
// import com.cuboiddroid.cuboidmod.util.Constants;
// import com.mojang.blaze3d.matrix.MatrixStack;
// import mezz.jei.api.constants.VanillaTypes;
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

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.concurrent.atomic.AtomicInteger;

public class TransmutingRecipeCategoryJei {//implements IRecipeCategory<TransmutingRecipe> {
    // private static final int GUI_START_X = 24;
    // private static final int GUI_START_Y = 28;
    // private static final int GUI_WIDTH = 170 - GUI_START_X;
    // private static final int GUI_HEIGHT = 78 - GUI_START_Y;

    // private final IDrawable background;
    // private final IDrawable icon;
    // private final IDrawableAnimated arrow;
    // private final IDrawableAnimated energyBar;
    // private final String localizedName;

    // public TransmutingRecipeCategoryJei(IGuiHelper guiHelper) {
    //     background = guiHelper.createDrawable(QuantumTransmutationChamberScreen.GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
    //     icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER.get()));
    //     arrow = guiHelper.drawableBuilder(QuantumTransmutationChamberScreen.GUI, 184, 0, 24, 17)
    //             .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    //     energyBar = guiHelper.drawableBuilder(QuantumTransmutationChamberScreen.GUI, 176, 0, 8, 36)
    //             .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
    //     localizedName = new TranslatableComponent("jei.category.cuboidmod.transmuting").getString();
    // }

    // private static void renderScaledTextWithShadow(MatrixStack matrix, FontRenderer fontRenderer, IReorderingProcessor text, int x, int y, int width, float scale, int color) {
    //     matrix.pushPose();
    //     matrix.scale(scale, scale, scale);
    //     float xOffset = (width / scale - fontRenderer.width(text)) / 2;
    //     fontRenderer.drawShadow(matrix, text, xOffset + x / scale, y / scale, color);
    //     matrix.popPose();
    // }

    // @Override
    // public ResourceLocation getUid() {
    //     return Constants.TRANSMUTING;
    // }

    // @Override
    // public Class<? extends TransmutingRecipe> getRecipeClass() {
    //     return TransmutingRecipe.class;
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
    // public void setIngredients(TransmutingRecipe recipe, IIngredients ingredients) {
    //     ingredients.setInputIngredients(new ArrayList<>(recipe.getIngredients()));
    //     ingredients.setOutputs(VanillaTypes.ITEM, Collections.singletonList(recipe.getResultItem().copy()));
    // }

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

    // @Override
    // public void draw(TransmutingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
    //     FontRenderer font = Minecraft.getInstance().font;

    //     // arrow
    //     arrow.draw(matrixStack, 78 - GUI_START_X, 43 - GUI_START_Y);

    //     int workSeconds = recipe.getWorkTicks() / 20;
    //     int workDecimal = (recipe.getWorkTicks() % 20) / 2;
    //     String arrowText = "" + workSeconds + "." + workDecimal + " s";
    //     renderScaledTextWithShadow(matrixStack, font, new StringTextComponent(arrowText).getVisualOrderText(), 78 - GUI_START_X, 61 - GUI_START_Y, 24, 0.6f, 0xFFFFFF);

    //     // energy
    //     energyBar.draw(matrixStack, 32 - GUI_START_X, 34 - GUI_START_Y);

    //     String energyText = "" + recipe.getEnergyRequired() + " FE";
    //     renderScaledTextWithShadow(matrixStack, font, new StringTextComponent(energyText).getVisualOrderText(), 32 - GUI_START_X, 71 - GUI_START_Y, 8, 0.6f, 0xFFFFFF);
    // }
}
