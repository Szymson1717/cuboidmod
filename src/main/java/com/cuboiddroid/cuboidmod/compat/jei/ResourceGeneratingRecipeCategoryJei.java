package com.cuboiddroid.cuboidmod.compat.jei;

// import com.cuboiddroid.cuboidmod.Config;
// import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
// import com.cuboiddroid.cuboidmod.modules.resourcegen.screen.SingularityResourceGeneratorScreenBase;
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

// import java.util.Arrays;
// import java.util.Collections;

public class ResourceGeneratingRecipeCategoryJei {// implements IRecipeCategory<ResourceGeneratingRecipe> {
//     private static final int GUI_START_X = 24;
//     private static final int GUI_START_Y = 28;
//     private static final int GUI_WIDTH = 170 - GUI_START_X;
//     private static final int GUI_HEIGHT = 78 - GUI_START_Y;

//     private final IDrawable background;
//     private final IDrawable icon;
//     private final IDrawableAnimated arrow;
//     private final IDrawableAnimated itemBar;
//     private final String localizedName;
//     private final float notsogudiumItemsPerSecond;
//     private final float kudbebeddaItemsPerSecond;
//     private final float notarfbadiumItemsPerSecond;
//     private final float wikidiumItemsPerSecond;
//     private final float thatlduItemsPerSecond;

//     public ResourceGeneratingRecipeCategoryJei(IGuiHelper guiHelper) {
//         background = guiHelper.createDrawable(SingularityResourceGeneratorScreenBase.GUI, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
//         icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get()));
//         arrow = guiHelper.drawableBuilder(SingularityResourceGeneratorScreenBase.GUI, 184, 0, 24, 17)
//                 .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
//         itemBar = guiHelper.drawableBuilder(SingularityResourceGeneratorScreenBase.GUI, 176, 0, 8, 36)
//                 .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
//         localizedName = new TranslatableComponent("jei.category.cuboidmod.resource_generating").getString();

//         notsogudiumItemsPerSecond =
//                 20.0F * Config.notsogudiumSingularityResourceGeneratorItemsPerOperation.get()
//                         / Config.notsogudiumSingularityResourceGeneratorTicksPerOperation.get();

//         kudbebeddaItemsPerSecond =
//                 20.0F * Config.kudbebeddaSingularityResourceGeneratorItemsPerOperation.get()
//                         / Config.kudbebeddaSingularityResourceGeneratorTicksPerOperation.get();

//         notarfbadiumItemsPerSecond =
//                 20.0F * Config.notarfbadiumSingularityResourceGeneratorItemsPerOperation.get()
//                         / Config.notarfbadiumSingularityResourceGeneratorTicksPerOperation.get();

//         wikidiumItemsPerSecond =
//                 20.0F * Config.wikidiumSingularityResourceGeneratorItemsPerOperation.get()
//                         / Config.wikidiumSingularityResourceGeneratorTicksPerOperation.get();

//         thatlduItemsPerSecond =
//                 20.0F * Config.thatlduSingularityResourceGeneratorItemsPerOperation.get()
//                         / Config.thatlduSingularityResourceGeneratorTicksPerOperation.get();
//     }

//     private static void renderScaledText(MatrixStack matrix, FontRenderer fontRenderer, IReorderingProcessor text, int x, int y, float scale, int color) {
//         matrix.pushPose();
//         matrix.scale(scale, scale, scale);
//         fontRenderer.draw(matrix, text, x / scale, y / scale, color);
//         matrix.popPose();
//     }

//     @Override
//     public ResourceLocation getUid() {
//         return Constants.RESOURCE_GENERATING;
//     }

//     @Override
//     public Class<? extends ResourceGeneratingRecipe> getRecipeClass() {
//         return ResourceGeneratingRecipe.class;
//     }

//     @Override
//     public String getTitle() {
//         return localizedName;
//     }

//     @Override
//     public IDrawable getBackground() {
//         return background;
//     }

//     @Override
//     public IDrawable getIcon() {
//         return icon;
//     }

//     @Override
//     public void setIngredients(ResourceGeneratingRecipe recipe, IIngredients ingredients) {
//         ingredients.setInputIngredients(Collections.singletonList(recipe.getSingularity()));
//         ingredients.setOutputs(VanillaTypes.ITEM, Collections.singletonList(recipe.getResultItem()));
//     }

//     @Override
//     public void setRecipe(IRecipeLayout recipeLayout, ResourceGeneratingRecipe recipe, IIngredients ingredients) {
//         IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
//         itemStacks.init(0, true, 7, 14);
//         itemStacks.init(1, false, 46, 14);

//         // Should only be one ingredient...
//         itemStacks.set(0, Arrays.asList(recipe.getSingularity().getItems()));
//         // Output
//         itemStacks.set(1, recipe.getResultItem().copy());
//     }

//     @Override
//     public void draw(ResourceGeneratingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
//         FontRenderer font = Minecraft.getInstance().font;

//         // arrow
//         arrow.draw(matrixStack, 48 - GUI_START_X, 43 - GUI_START_Y);

//         float factor = recipe.getOutputMultiplier() / recipe.getWorkTimeMultiplier();

//         renderScaledText(matrixStack, font, new StringTextComponent(
//                 "Notso.: " + String.format("%.2f",
//                         notsogudiumItemsPerSecond * factor
//                 ) + " items/s").getVisualOrderText(), 74, 2, 0.6F, 0x444444);

//         renderScaledText(matrixStack, font, new StringTextComponent(
//                 "Kudbe.: " + String.format("%.2f",
//                         kudbebeddaItemsPerSecond * factor
//                 ) + " items/s").getVisualOrderText(), 74, 11, 0.6F, 0x444444);

//         renderScaledText(matrixStack, font, new StringTextComponent(
//                 "Notarf.: " + String.format("%.2f",
//                         notarfbadiumItemsPerSecond * factor
//                 ) + " items/s").getVisualOrderText(), 74, 20, 0.6F, 0x444444);

//         renderScaledText(matrixStack, font, new StringTextComponent(
//                 "Wikid.: " + String.format("%.2f",
//                         wikidiumItemsPerSecond * factor
//                 ) + " items/s").getVisualOrderText(), 74, 29, 0.6F, 0x444444);

//         renderScaledText(matrixStack, font, new StringTextComponent(
//                 "Thatl.: " + String.format("%.2f",
//                         thatlduItemsPerSecond * factor
//                 ) + " items/s").getVisualOrderText(), 74, 38, 0.6F, 0x444444);
//     }
}
