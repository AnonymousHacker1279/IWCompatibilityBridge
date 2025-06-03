package tech.anonymoushacker1279.iwcompatbridge.plugin.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import tech.anonymoushacker1279.immersiveweapons.init.BlockRegistry;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.AstralCrystalRecipe;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.JEIPluginHandler;

public class AstralCrystalRecipeCategory extends AbstractRecipeCategory<AstralCrystalRecipe> {

	private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID,
			"textures/gui/jei/astral_crystal.png");
	private final IDrawable background;

	/**
	 * Constructor for AstralCrystalRecipeCategory.
	 *
	 * @param guiHelper a <code>IGuiHelper</code> instance
	 */
	public AstralCrystalRecipeCategory(IGuiHelper guiHelper) {
		super(JEIPluginHandler.ASTRAL_CRYSTAL,
				Component.translatable("gui.jei.category.astral_crystal"),
				guiHelper.createDrawableItemLike(BlockRegistry.ASTRAL_CRYSTAL.get()),
				98,
				48);

		background = guiHelper.createDrawable(GUI_TEXTURE, 0, 0, 98, 48);
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
	public void setRecipe(IRecipeLayoutBuilder builder, AstralCrystalRecipe recipe, IFocusGroup focuses) {
		Ingredient material = recipe.material();
		Ingredient catalyst = recipe.catalyst();

		builder.addSlot(RecipeIngredientRole.INPUT, 16, 1)
				.add(material);
		builder.addSlot(RecipeIngredientRole.INPUT, 0, 16)
				.add(material);
		builder.addSlot(RecipeIngredientRole.INPUT, 16, 32)
				.add(material);
		builder.addSlot(RecipeIngredientRole.INPUT, 31, 16)
				.add(material);
		builder.addSlot(RecipeIngredientRole.INPUT, 54, 3)
				.add(catalyst);
		builder.addSlot(RecipeIngredientRole.OUTPUT, 79, 29)
				.add(recipe.result());
	}
}