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
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.init.BlockRegistry;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.AstralCrystalRecipe;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.JEIPluginHandler;

public class AstralCrystalRecipeCategory implements IRecipeCategory<AstralCrystalRecipe> {

	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(IWCompatBridge.MOD_ID,
			"textures/gui/jei/astral_crystal.png");
	private final IDrawable background;
	private final IDrawable icon;

	/**
	 * Constructor for AstralCrystalRecipeCategory.
	 *
	 * @param guiHelper a <code>IGuiHelper</code> instance
	 */
	public AstralCrystalRecipeCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(BlockRegistry.ASTRAL_CRYSTAL.get()));

		background = guiHelper.createDrawable(GUI_TEXTURE, 0, 0, 98, 48);
	}

	@Override
	public @NotNull RecipeType<AstralCrystalRecipe> getRecipeType() {
		return JEIPluginHandler.ASTRAL_CRYSTAL;
	}

	/**
	 * Get the title of the recipe category.
	 *
	 * @return String
	 */
	@Override
	public @NotNull Component getTitle() {
		return Component.translatable("gui.jei.category.astral_crystal");
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
	public void setRecipe(IRecipeLayoutBuilder builder, AstralCrystalRecipe recipe, @NotNull IFocusGroup focuses) {
		NonNullList<Ingredient> ingredients = NonNullList.create();
		ingredients.addAll(recipe.getIngredients());

		Ingredient primaryMaterial = recipe.getPrimaryMaterial();
		Ingredient secondaryMaterial = recipe.getSecondaryMaterial();

		builder.addSlot(RecipeIngredientRole.INPUT, 16, 1)
				.addIngredients(primaryMaterial);
		builder.addSlot(RecipeIngredientRole.INPUT, 0, 16)
				.addIngredients(primaryMaterial);
		builder.addSlot(RecipeIngredientRole.INPUT, 16, 32)
				.addIngredients(primaryMaterial);
		builder.addSlot(RecipeIngredientRole.INPUT, 31, 16)
				.addIngredients(primaryMaterial);
		builder.addSlot(RecipeIngredientRole.CATALYST, 54, 3)
				.addIngredients(secondaryMaterial);
		builder.addSlot(RecipeIngredientRole.OUTPUT, 79, 29)
				.addItemStack(recipe.getResultItem(RegistryAccess.EMPTY));

	}
}