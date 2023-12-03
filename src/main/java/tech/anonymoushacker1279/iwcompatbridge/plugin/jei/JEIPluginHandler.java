/* TODO: reimplement when JEI updates
package tech.anonymoushacker1279.iwcompatbridge.plugin.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.immersiveweapons.init.*;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.*;
import tech.anonymoushacker1279.immersiveweapons.menu.AmmunitionTableMenu;
import tech.anonymoushacker1279.immersiveweapons.menu.TeslaSynthesizerMenu;
import tech.anonymoushacker1279.iwcompatbridge.config.CommonConfig;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.category.*;

import java.util.List;
import java.util.Objects;

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

	*/
/**
 * Get the plugin UID.
 *
 * @return ResourceLocation
 * <p>
 * Register recipe catalysts.
 * @param registration an <code>IRecipeCatalystRegistration</code> instance
 * <p>
 * Register recipes.
 * @param registration an <code>IRecipeRegistration</code> instance
 * <p>
 * Register recipe categories.
 * @param registration an <code>IRecipeCategoryRegistration</code> instance
 * <p>
 * Register recipe transfer handlers.
 * @param registration an <code>IRecipeTransferRegistration</code> instance
 *//*

	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return new ResourceLocation(ImmersiveWeapons.MOD_ID, "jei_plugin");
	}

	*/
/**
 * Register recipe catalysts.
 *
 * @param registration an <code>IRecipeCatalystRegistration</code> instance
 *//*

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

	*/
/**
 * Register recipes.
 *
 * @param registration an <code>IRecipeRegistration</code> instance
 *//*

	@Override
	public void registerRecipes(@NotNull IRecipeRegistration registration) {
		if (CommonConfig.ENABLE_JEI_PLUGIN.get()) {
			List<TeslaSynthesizerRecipe> teslaSynthesizerRecipes = Objects.requireNonNull(getRecipeManager())
					.getAllRecipesFor(RecipeTypeRegistry.TESLA_SYNTHESIZER_RECIPE_TYPE.get());
			List<AstralCrystalRecipe> astralCrystalRecipes = Objects.requireNonNull(getRecipeManager())
					.getAllRecipesFor(RecipeTypeRegistry.ASTRAL_CRYSTAL_RECIPE_TYPE.get());
			List<BarrelTapRecipe> barrelTapRecipes = Objects.requireNonNull(getRecipeManager())
					.getAllRecipesFor(RecipeTypeRegistry.BARREL_TAP_RECIPE_TYPE.get());
			List<PistonCrushingRecipe> pistonCrushingRecipes = Objects.requireNonNull(getRecipeManager())
					.getAllRecipesFor(RecipeTypeRegistry.PISTON_CRUSHING_RECIPE_TYPE.get());
			List<AmmunitionTableRecipe> ammunitionTableRecipes = Objects.requireNonNull(getRecipeManager())
					.getAllRecipesFor(RecipeTypeRegistry.AMMUNITION_TABLE_RECIPE_TYPE.get());

			registration.addRecipes(TESLA_SYNTHESIZER, teslaSynthesizerRecipes);
			registration.addRecipes(ASTRAL_CRYSTAL, astralCrystalRecipes);
			registration.addRecipes(BARREL_TAP, barrelTapRecipes);
			registration.addRecipes(PISTON_CRUSHING, pistonCrushingRecipes);
			registration.addRecipes(AMMUNITION_TABLE, ammunitionTableRecipes);
		}
	}

	*/
/**
 * Register recipe categories.
 *
 * @param registration an <code>IRecipeCategoryRegistration</code> instance
 *//*

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

	*/
/**
 * Register recipe transfer handlers.
 *
 * @param registration an <code>IRecipeTransferRegistration</code> instance
 *//*

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
}*/