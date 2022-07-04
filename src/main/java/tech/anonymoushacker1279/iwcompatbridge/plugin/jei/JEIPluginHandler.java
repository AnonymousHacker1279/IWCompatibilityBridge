package tech.anonymoushacker1279.iwcompatbridge.plugin.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.fml.util.thread.EffectiveSide;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.immersiveweapons.client.gui.screen.TeslaSynthesizerScreen;
import tech.anonymoushacker1279.immersiveweapons.container.TeslaSynthesizerContainer;
import tech.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.TeslaSynthesizerRecipe;
import tech.anonymoushacker1279.iwcompatbridge.config.CommonConfig;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.category.TeslaSynthesizerRecipeCategory;

import java.util.List;
import java.util.Objects;

@JeiPlugin
@SuppressWarnings("unused")
public class JEIPluginHandler implements IModPlugin {

	public static final RecipeType<TeslaSynthesizerRecipe> TESLA_SYNTHESIZER =
			RecipeType.create(ImmersiveWeapons.MOD_ID, "tesla_synthesizer", TeslaSynthesizerRecipe.class);

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
			registration.addRecipeCatalyst(new ItemStack(DeferredRegistryHandler.TESLA_SYNTHESIZER.get()), TESLA_SYNTHESIZER);
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
			List<TeslaSynthesizerRecipe> teslaSynthesizerRecipes = Objects.requireNonNull(getRecipeManager())
					.getAllRecipesFor(DeferredRegistryHandler.TESLA_SYNTHESIZER_RECIPE_TYPE.get());

			registration.addRecipes(TESLA_SYNTHESIZER, teslaSynthesizerRecipes);
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
			registration.addRecipeTransferHandler(TeslaSynthesizerContainer.class,
					DeferredRegistryHandler.TESLA_SYNTHESIZER_CONTAINER.get(), TESLA_SYNTHESIZER,
					0, 3, 0, 35);
		}
	}

	/**
	 * Register GUI handlers.
	 *
	 * @param registration an <code>IGuiHandlerRegistration</code> instance
	 */
	@Override
	public void registerGuiHandlers(@NotNull IGuiHandlerRegistration registration) {
		if (CommonConfig.ENABLE_JEI_PLUGIN.get()) {
			registration.addRecipeClickArea(TeslaSynthesizerScreen.class, 107, 16, 25, 15, TESLA_SYNTHESIZER);
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
}