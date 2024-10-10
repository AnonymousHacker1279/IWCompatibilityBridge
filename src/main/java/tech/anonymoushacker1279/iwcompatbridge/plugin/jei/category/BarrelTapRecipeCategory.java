package tech.anonymoushacker1279.iwcompatbridge.plugin.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.*;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import tech.anonymoushacker1279.immersiveweapons.init.BlockRegistry;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.BarrelTapRecipe;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.JEIPluginHandler;

public class BarrelTapRecipeCategory implements IRecipeCategory<BarrelTapRecipe> {

	private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID,
			"textures/gui/jei/barrel_tap_fermenting.png");
	private final IDrawable background;
	private final IDrawable icon;

	/**
	 * Constructor for BarrelTapRecipeCategory.
	 *
	 * @param guiHelper a <code>IGuiHelper</code> instance
	 */
	public BarrelTapRecipeCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(BlockRegistry.BARREL_TAP.get()));

		background = guiHelper.createDrawable(GUI_TEXTURE, 0, 0, 162, 102);
	}

	@Override
	public RecipeType<BarrelTapRecipe> getRecipeType() {
		return JEIPluginHandler.BARREL_TAP;
	}

	/**
	 * Get the title of the recipe category.
	 *
	 * @return String
	 */
	@Override
	public Component getTitle() {
		return Component.translatable("gui.jei.category.barrel_tap_fermenting");
	}

	/**
	 * Get the background.
	 *
	 * @return IDrawable
	 */
	@Override
	public IDrawable getBackground() {
		return background;
	}

	/**
	 * Get the icon.
	 *
	 * @return IDrawable
	 */
	@Override
	public IDrawable getIcon() {
		return icon;
	}


	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, BarrelTapRecipe recipe, IFocusGroup focuses) {
		NonNullList<Ingredient> ingredients = NonNullList.create();
		ingredients.addAll(recipe.getIngredients());

		builder.addSlot(RecipeIngredientRole.INPUT, 39, 69)
				.addItemStack(new ItemStack(recipe.getMaterial().getItems()[0].getItemHolder(), recipe.getMaterialCount()));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 137, 42)
				.addItemStack(recipe.getResultItem(RegistryAccess.EMPTY));
	}
}