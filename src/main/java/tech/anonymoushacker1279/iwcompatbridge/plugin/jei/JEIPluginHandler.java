package tech.anonymoushacker1279.iwcompatbridge.plugin.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.util.thread.EffectiveSide;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.Nullable;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.immersiveweapons.init.*;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.*;
import tech.anonymoushacker1279.immersiveweapons.menu.*;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
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
	public static final RecipeType<StarForgeRecipe> STAR_FORGE =
			RecipeType.create(ImmersiveWeapons.MOD_ID, "star_forge", StarForgeRecipe.class);

	/**
	 * Get the plugin UID.
	 *
	 * @return ResourceLocation
	 */
	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, "jei_plugin");
	}

	/**
	 * Register recipe catalysts.
	 *
	 * @param registration an <code>IRecipeCatalystRegistration</code> instance
	 */
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(BlockRegistry.TESLA_SYNTHESIZER.get()), TESLA_SYNTHESIZER);
		registration.addRecipeCatalyst(new ItemStack(BlockRegistry.ASTRAL_CRYSTAL.get()), ASTRAL_CRYSTAL);
		registration.addRecipeCatalyst(new ItemStack(BlockRegistry.BARREL_TAP.get()), BARREL_TAP);
		registration.addRecipeCatalyst(new ItemStack(Blocks.PISTON), PISTON_CRUSHING);
		registration.addRecipeCatalyst(new ItemStack(BlockRegistry.AMMUNITION_TABLE.get()), AMMUNITION_TABLE);
		registration.addRecipeCatalyst(new ItemStack(BlockRegistry.STAR_FORGE_CONTROLLER.get()), STAR_FORGE);
	}

	/**
	 * Register recipes.
	 *
	 * @param registration an <code>IRecipeRegistration</code> instance
	 */
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
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

		registration.addRecipes(STAR_FORGE, recipeManager
				.getAllRecipesFor(RecipeTypeRegistry.STAR_FORGE_RECIPE_TYPE.get())
				.stream()
				.map(RecipeHolder::value)
				.toList());

		registration.addItemStackInfo(BlockRegistry.TESLA_SYNTHESIZER.get().asItem().getDefaultInstance(), Component.translatable("gui.jei.item.tesla_synthesizer.info"));
		registration.addItemStackInfo(BlockRegistry.ASTRAL_CRYSTAL.get().asItem().getDefaultInstance(), Component.translatable("gui.jei.item.astral_crystal.info"));
		registration.addItemStackInfo(BlockRegistry.BARREL_TAP.get().asItem().getDefaultInstance(), Component.translatable("gui.jei.item.barrel_tap.info"));
		registration.addItemStackInfo(Blocks.PISTON.asItem().getDefaultInstance(), Component.translatable("gui.jei.item.piston_crushing.info"));
		registration.addItemStackInfo(BlockRegistry.AMMUNITION_TABLE.get().asItem().getDefaultInstance(), Component.translatable("gui.jei.item.ammunition_table.info"));
		registration.addItemStackInfo(BlockRegistry.STAR_FORGE_CONTROLLER.get().asItem().getDefaultInstance(), Component.translatable("gui.jei.item.star_forge.info"));
	}

	/**
	 * Register recipe categories.
	 *
	 * @param registration an <code>IRecipeCategoryRegistration</code> instance
	 */
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new TeslaSynthesizerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new AstralCrystalRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new BarrelTapRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new PistonCrushingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new AmmunitionTableRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new StarForgeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

	}

	/**
	 * Register recipe transfer handlers.
	 *
	 * @param registration an <code>IRecipeTransferRegistration</code> instance
	 */
	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(TeslaSynthesizerMenu.class,
				MenuTypeRegistry.TESLA_SYNTHESIZER_MENU.get(), TESLA_SYNTHESIZER,
				0, 3, 4, 36);
		registration.addRecipeTransferHandler(AmmunitionTableMenu.class,
				MenuTypeRegistry.AMMUNITION_TABLE_MENU.get(), AMMUNITION_TABLE,
				0, 6, 7, 36);
		registration.addRecipeTransferHandler(StarForgeMenu.class,
				MenuTypeRegistry.STAR_FORGE_MENU.get(), STAR_FORGE,
				0, 2, 3, 36);
	}

	@Override
	public void registerModInfo(IModInfoRegistration modAliasRegistration) {
		modAliasRegistration.addModAliases(ImmersiveWeapons.MOD_ID, "iw");
		modAliasRegistration.addModAliases(IWCompatBridge.MOD_ID, "iwcb");
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