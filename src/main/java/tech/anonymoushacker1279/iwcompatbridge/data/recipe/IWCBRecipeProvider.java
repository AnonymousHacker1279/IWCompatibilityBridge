package tech.anonymoushacker1279.iwcompatbridge.data.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;

import java.util.List;
import java.util.function.Consumer;

public abstract class IWCBRecipeProvider extends RecipeProvider {

	protected static Consumer<FinishedRecipe> finishedRecipeConsumer;
	protected final PackOutput packOutput;

	public IWCBRecipeProvider(PackOutput output) {
		super(output);
		packOutput = output;
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {
		finishedRecipeConsumer = recipeConsumer;
	}

	public static void createSmeltingRecipe(List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, @Nullable String pGroup) {
		oreCooking((SimpleCookingSerializer<?>) RecipeSerializer.SMELTING_RECIPE, pIngredients, pResult, pExperience,
				pCookingTime, pGroup, "_from_smelting");
	}

	private static void createSmeltingRecipe(ItemLike ingredient, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
		oreCooking((SimpleCookingSerializer<?>) RecipeSerializer.SMELTING_RECIPE, ingredient, pResult, pExperience,
				pCookingTime, pGroup, "_from_smelting");
	}

	protected static void createBlastingRecipe(List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, @Nullable String pGroup) {
		oreCooking((SimpleCookingSerializer<?>) RecipeSerializer.BLASTING_RECIPE, pIngredients, pResult, pExperience,
				pCookingTime, pGroup, "_from_blasting");
	}

	private static void createBlastingRecipe(ItemLike ingredient, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
		oreCooking((SimpleCookingSerializer<?>) RecipeSerializer.BLASTING_RECIPE, ingredient, pResult, pExperience,
				pCookingTime, pGroup, "_from_blasting");
	}

	private static void oreCooking(SimpleCookingSerializer<?> pCookingSerializer, List<ItemLike> pIngredients,
	                               ItemLike pResult, float pExperience, int pCookingTime, @Nullable String pGroup, String pRecipeName) {

		for (ItemLike itemlike : pIngredients) {
			SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), RecipeCategory.MISC, pResult, pExperience, pCookingTime, pCookingSerializer)
					.group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
					.save(finishedRecipeConsumer, IWCompatBridge.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
		}
	}

	private static void oreCooking(SimpleCookingSerializer<?> pCookingSerializer, ItemLike ingredient,
	                               ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {

		SimpleCookingRecipeBuilder.generic(Ingredient.of(ingredient), RecipeCategory.MISC, pResult, pExperience, pCookingTime, pCookingSerializer)
				.group(pGroup).unlockedBy(getHasName(ingredient), has(ingredient))
				.save(finishedRecipeConsumer, IWCompatBridge.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(ingredient));
	}
}