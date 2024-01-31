package tech.anonymoushacker1279.iwcompatbridge.plugin.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.*;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.PistonCrushingRecipe;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.JEIPluginHandler;

public class PistonCrushingRecipeCategory implements IRecipeCategory<PistonCrushingRecipe> {

	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(IWCompatBridge.MOD_ID,
			"textures/gui/jei/piston_crushing.png");
	private final IDrawable background;
	private final IDrawable icon;

	/**
	 * Constructor for PistonCrushingRecipeCategory.
	 *
	 * @param guiHelper a <code>IGuiHelper</code> instance
	 */
	public PistonCrushingRecipeCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(Blocks.PISTON));

		background = guiHelper.createDrawable(GUI_TEXTURE, 0, 0, 100, 50);
	}

	@Override
	public void draw(@NotNull PistonCrushingRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView,
	                 @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {

		MutableComponent dropString = Component.translatable("gui.jei.category.piston_crushing.note", recipe.getMinCount(), recipe.getMaxCount());
		Minecraft minecraft = Minecraft.getInstance();
		Font fontRenderer = minecraft.font;
		int width = fontRenderer.width(dropString);
		guiGraphics.drawString(fontRenderer, dropString, background.getWidth() - width, 40, 0x808080, false);
	}

	@Override
	public @NotNull RecipeType<PistonCrushingRecipe> getRecipeType() {
		return JEIPluginHandler.PISTON_CRUSHING;
	}

	/**
	 * Get the title of the recipe category.
	 *
	 * @return String
	 */
	@Override
	public @NotNull Component getTitle() {
		return Component.translatable("gui.jei.category.piston_crushing");
	}

	/**
	 * Get the background.
	 *
	 * @return IDrawable
	 */
	@Override
	public @NotNull IDrawable getBackground() {
		return background;
	}

	/**
	 * Get the icon.
	 *
	 * @return IDrawable
	 */
	@Override
	public @NotNull IDrawable getIcon() {
		return icon;
	}


	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, PistonCrushingRecipe recipe, @NotNull IFocusGroup focuses) {
		NonNullList<Ingredient> ingredients = NonNullList.create();
		ingredients.addAll(recipe.getIngredients());

		builder.addSlot(RecipeIngredientRole.INPUT, 2, 17)
				.addItemStack(new ItemStack(recipe.getBlock()));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 55, 17)
				.addItemStack(recipe.getResultItem(RegistryAccess.EMPTY));
	}
}