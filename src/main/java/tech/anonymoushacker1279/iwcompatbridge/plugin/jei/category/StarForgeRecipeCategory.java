package tech.anonymoushacker1279.iwcompatbridge.plugin.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.*;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import tech.anonymoushacker1279.immersiveweapons.init.BlockRegistry;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.StarForgeRecipe;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.JEIPluginHandler;

public class StarForgeRecipeCategory implements IRecipeCategory<StarForgeRecipe> {

	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(IWCompatBridge.MOD_ID,
			"textures/gui/jei/star_forge.png");
	private final IDrawable background;
	private final IDrawable icon;

	public StarForgeRecipeCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(BlockRegistry.STAR_FORGE_CONTROLLER.get()));

		background = guiHelper.createDrawable(GUI_TEXTURE, 0, 0, 95, 26);
	}

	@Override
	public RecipeType<StarForgeRecipe> getRecipeType() {
		return JEIPluginHandler.STAR_FORGE;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("gui.jei.category.star_forge");
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
	public void setRecipe(IRecipeLayoutBuilder builder, StarForgeRecipe recipe, IFocusGroup focuses) {
		ItemStack ingot = recipe.ingot().getItems()[0];

		builder.addSlot(RecipeIngredientRole.INPUT, 1, 5)
				.addItemStack(ingot.copyWithCount(recipe.ingotCount()));

		if (!recipe.secondaryMaterial().isEmpty()) {
			ItemStack secondaryMaterial = recipe.secondaryMaterial().getItems()[0];
			builder.addSlot(RecipeIngredientRole.INPUT, 27, 5)
					.addItemStack(secondaryMaterial.copyWithCount(recipe.secondaryMaterialCount()));
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 73, 5)
				.addItemStack(recipe.getResultItem(RegistryAccess.EMPTY));
	}
}