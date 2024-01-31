package tech.anonymoushacker1279.iwcompatbridge.plugin.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.util.thread.EffectiveSide;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.immersiveweapons.init.*;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.*;
import tech.anonymoushacker1279.immersiveweapons.menu.AmmunitionTableMenu;
import tech.anonymoushacker1279.immersiveweapons.menu.TeslaSynthesizerMenu;
import tech.anonymoushacker1279.iwcompatbridge.config.CommonConfig;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.category.*;

@JeiPlugin
@SuppressWarnings("unused")
public class JEIPluginHandler implements IModPlugin {

	public static final RecipeType<TeslaSynthesizerRecipe> TESLA_SYNTHESIZER =
			RecipeType.create(ImmersiveWeapons.MOD_ID, "tesla_synthesizer", TeslaSynthesizerRecipe.class);
	public static final RecipeType<AstralCrystalRecipe> ASTRAL_CRYSTAL =
			RecipeType.create(ImmersiveWeapons.MOD_ID, "astral_crystal", AstralCrystalRecipe.class);
	public static final RecipeType<BarrelTapRecipe> BARREL_TAP =
			RecipeType.create(ImmersiveWeapons.MOD_ID, "barrel_tap", BarrelTapRecipe.class);
	public static final RecipeType<PistonCrushingRecipe> PISTON_CRUSHING =
			RecipeType.create(ImmersiveWeapons.MOD_ID, "piston_crushing", PistonCrushingRecipe.class);
	public static final RecipeType<AmmunitionTableRecipe> AMMUNITION_TABLE =
			RecipeType.create(ImmersiveWeapons.MOD_ID, "ammunition_table", AmmunitionTableRecipe.class);

	/**
	 * Get the plugin UID.
	 *
	 * @return ResourceLocation
	 */
	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return new ResourceLocation(ImmersiveWeapons.MOD_ID, "jei_plugin");
	}

	/**
	 * Register recipe catalysts.
	 *
	 * @param registration an <code>IRecipeCatalystRegistration</code> instance
	 */
	@Override
	public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
		if (CommonConfig.ENABLE_JEI_PLUGIN.get()) {
			registration.addRecipeCatalyst(new ItemStack(BlockRegistry.TESLA_SYNTHESIZER.get()), TESLA_SYNTHESIZER);
			registration.addRecipeCatalyst(new ItemStack(BlockRegistry.ASTRAL_CRYSTAL.get()), ASTRAL_CRYSTAL);
			registration.addRecipeCatalyst(new ItemStack(BlockRegistry.BARREL_TAP.get()), BARREL_TAP);
			registration.addRecipeCatalyst(new ItemStack(Blocks.PISTON), PISTON_CRUSHING);
			registration.addRecipeCatalyst(new ItemStack(BlockRegistry.AMMUNITION_TABLE.get()), AMMUNITION_TABLE);
		}
	}

	/**
	 * Register recipes.
	 *
	 * @param registration an <code>IRecipeRegistration</code> instance
	 */
	@Override
	public void registerRecipes(@NotNull IRecipeRegistration registration) {
		if (CommonConfig.ENABLE_JEI_PLUGIN.get()) {
			RecipeManager recipeManager = getRecipeManager();

			if (recipeManager == null) {
				return;
			}

			registration.addRecipes(TESLA_SYNTHESIZER, recipeManager
					.getAllRecipesFor(RecipeTypeRegistry.TESLA_SYNTHESIZER_RECIPE_TYPE.get())
					.stream()
					.map(RecipeHolder::value)
					.toList());

			registration.addRecipes(ASTRAL_CRYSTAL, recipeManager
					.getAllRecipesFor(RecipeTypeRegistry.ASTRAL_CRYSTAL_RECIPE_TYPE.get())
					.stream()
					.map(RecipeHolder::value)
					.toList());

			registration.addRecipes(BARREL_TAP, recipeManager
					.getAllRecipesFor(RecipeTypeRegistry.BARREL_TAP_RECIPE_TYPE.get())
					.stream()
					.map(RecipeHolder::value)
					.toList());

			registration.addRecipes(PISTON_CRUSHING, recipeManager
					.getAllRecipesFor(RecipeTypeRegistry.PISTON_CRUSHING_RECIPE_TYPE.get())
					.stream()
					.map(RecipeHolder::value)
					.toList());

			registration.addRecipes(AMMUNITION_TABLE, recipeManager
					.getAllRecipesFor(RecipeTypeRegistry.AMMUNITION_TABLE_RECIPE_TYPE.get())
					.stream()
					.map(RecipeHolder::value)
					.toList());
		}
	}

	/**
	 * Register recipe categories.
	 *
	 * @param registration an <code>IRecipeCategoryRegistration</code> instance
	 */
	@Override
	public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
		if (CommonConfig.ENABLE_JEI_PLUGIN.get()) {
			registration.addRecipeCategories(new TeslaSynthesizerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
			registration.addRecipeCategories(new AstralCrystalRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
			registration.addRecipeCategories(new BarrelTapRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
			registration.addRecipeCategories(new PistonCrushingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
			registration.addRecipeCategories(new AmmunitionTableRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		}
	}

	/**
	 * Register recipe transfer handlers.
	 *
	 * @param registration an <code>IRecipeTransferRegistration</code> instance
	 */
	@Override
	public void registerRecipeTransferHandlers(@NotNull IRecipeTransferRegistration registration) {
		if (CommonConfig.ENABLE_JEI_PLUGIN.get()) {
			registration.addRecipeTransferHandler(TeslaSynthesizerMenu.class,
					MenuTypeRegistry.TESLA_SYNTHESIZER_MENU.get(), TESLA_SYNTHESIZER,
					0, 3, 4, 35);
			registration.addRecipeTransferHandler(AmmunitionTableMenu.class,
					MenuTypeRegistry.AMMUNITION_TABLE_MENU.get(), AMMUNITION_TABLE,
					0, 6, 7, 36);
		}
	}

	@Nullable
	private static RecipeManager getRecipeManager() {
		try {
			if (EffectiveSide.get().isClient()) {
				if (Minecraft.getInstance().player != null) {
					return Minecraft.getInstance().player.connection.getRecipeManager();
				}
				return null;
			} else {
				return ServerLifecycleHooks.getCurrentServer().getRecipeManager();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}