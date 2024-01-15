package tech.anonymoushacker1279.iwcompatbridge.data.recipe;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class IWCBRecipeProvider extends RecipeProvider {

	protected static RecipeOutput output;
	protected final PackOutput packOutput;

	public IWCBRecipeProvider(PackOutput output, CompletableFuture<Provider> lookupProvider) {
		super(output, lookupProvider);
		packOutput = output;
	}

	@Override
	protected void buildRecipes(RecipeOutput output) {
		IWCBRecipeProvider.output = output;
	}

	public static void createSmeltingRecipe(List<ItemLike> ingredients, ItemLike result, float experience, int cookTime, @Nullable String group) {
		for (ItemLike itemlike : ingredients) {
			SimpleCookingRecipeBuilder.smelting(Ingredient.of(itemlike), RecipeCategory.MISC, result, experience, cookTime)
					.group(group)
					.unlockedBy(getHasName(itemlike), has(itemlike))
					.save(output, ImmersiveWeapons.MOD_ID + ":" + getItemName(result) + "_from_smelting_" + getItemName(itemlike));
		}
	}

	private static void createSmeltingRecipe(ItemLike ingredient, ItemLike result, float experience, int cookTime, String group) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, result, experience, cookTime)
				.group(group).unlockedBy(getHasName(ingredient), has(ingredient))
				.save(output, ImmersiveWeapons.MOD_ID + ":" + getItemName(result) + "_from_smelting_" + getItemName(ingredient));
	}

	private static void createBlastingRecipe(List<ItemLike> ingredients, ItemLike result, float experience, int cookTime, @Nullable String group) {
		for (ItemLike itemlike : ingredients) {
			SimpleCookingRecipeBuilder.smelting(Ingredient.of(itemlike), RecipeCategory.MISC, result, experience, cookTime)
					.group(group)
					.unlockedBy(getHasName(itemlike), has(itemlike))
					.save(output, ImmersiveWeapons.MOD_ID + ":" + getItemName(result) + "_from_blasting_" + getItemName(itemlike));
		}
	}

	private static void createBlastingRecipe(ItemLike ingredient, ItemLike result, float experience, int cookTime, String group) {
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC, result, experience, cookTime)
				.group(group).unlockedBy(getHasName(ingredient), has(ingredient))
				.save(output, ImmersiveWeapons.MOD_ID + ":" + getItemName(result) + "_from_blasting_" + getItemName(ingredient));
	}
}