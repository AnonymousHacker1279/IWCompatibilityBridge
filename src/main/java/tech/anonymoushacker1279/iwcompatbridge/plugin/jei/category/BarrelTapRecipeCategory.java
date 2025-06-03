package tech.anonymoushacker1279.iwcompatbridge.plugin.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import tech.anonymoushacker1279.immersiveweapons.init.BlockRegistry;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.BarrelTapRecipe;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.JEIPluginHandler;

public class BarrelTapRecipeCategory extends AbstractRecipeCategory<BarrelTapRecipe> {

	private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID,
			"textures/gui/jei/barrel_tap_fermenting.png");
	private final IDrawable background;

	/**
	 * Constructor for BarrelTapRecipeCategory.
	 *
	 * @param guiHelper a <code>IGuiHelper</code> instance
	 */
	public BarrelTapRecipeCategory(IGuiHelper guiHelper) {
		super(JEIPluginHandler.BARREL_TAP,
				Component.translatable("gui.jei.category.barrel_tap_fermenting"),
				guiHelper.createDrawableItemLike(BlockRegistry.BARREL_TAP.get()),
				162,
				102);

		background = guiHelper.createDrawable(GUI_TEXTURE, 0, 0, 162, 102);
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

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, BarrelTapRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 39, 69)
				.add(new ItemStack(recipe.input().getValues().get(0), recipe.getMaterialCount()));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 137, 42)
				.add(recipe.result());
	}
}