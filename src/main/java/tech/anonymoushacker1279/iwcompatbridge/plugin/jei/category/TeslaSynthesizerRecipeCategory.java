package tech.anonymoushacker1279.iwcompatbridge.plugin.jei.category;

import com.google.common.cache.*;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.*;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.*;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.immersiveweapons.init.BlockRegistry;
import tech.anonymoushacker1279.immersiveweapons.init.ItemRegistry;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.TeslaSynthesizerRecipe;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.JEIPluginHandler;

public class TeslaSynthesizerRecipeCategory implements IRecipeCategory<TeslaSynthesizerRecipe> {

	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(IWCompatBridge.MOD_ID,
			"textures/gui/jei/tesla_synthesizer.png");
	private static final ResourceLocation CONTAINER_TEXTURE = new ResourceLocation(ImmersiveWeapons.MOD_ID,
			"textures/gui/container/tesla_synthesizer.png");
	private static final ResourceLocation RECIPE_GUI_VANILLA = new ResourceLocation(ModIds.JEI_ID,
			"textures/jei/gui/gui_vanilla.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;
	protected final IDrawableStatic staticFlame;
	protected final IDrawableAnimated animatedFlame;

	/**
	 * Constructor for TeslaSynthesizerRecipeCategory.
	 *
	 * @param guiHelper a <code>IGuiHelper</code> instance
	 */
	public TeslaSynthesizerRecipeCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(BlockRegistry.TESLA_SYNTHESIZER.get()));

		background = guiHelper.createDrawable(GUI_TEXTURE, 0, 0, 132, 54);

		staticFlame = guiHelper.createDrawable(CONTAINER_TEXTURE, 176, 0, 14, 14);
		animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 75,
				IDrawableAnimated.StartDirection.TOP, true);

		cachedArrows = CacheBuilder.newBuilder()
				.maximumSize(25)
				.build(new CacheLoader<>() {
					@Override
					public @NotNull IDrawableAnimated load(@NotNull Integer cookTime) {
						return guiHelper.drawableBuilder(RECIPE_GUI_VANILLA, 82, 128, 24, 17)
								.buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
					}
				});
	}

	protected IDrawableAnimated getArrow(TeslaSynthesizerRecipe recipe) {
		return cachedArrows.getUnchecked(recipe.getCookTime() / 32);
	}

	@Override
	public void draw(@NotNull TeslaSynthesizerRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView,
	                 @NotNull PoseStack poseStack, double mouseX, double mouseY) {

		animatedFlame.draw(poseStack, 52, 20);

		IDrawableAnimated arrow = getArrow(recipe);
		arrow.draw(poseStack, 75, 19);

		drawText(recipe, poseStack);
	}

	protected void drawText(TeslaSynthesizerRecipe recipe, PoseStack poseStack) {
		int cookTime = recipe.getCookTime();
		if (cookTime > 0) {
			int cookTimeSeconds = cookTime / 20;
			MutableComponent timeString = Component.translatable("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
			MutableComponent noteString = Component.translatable("gui.jei.category.tesla_synthesizer.note");
			Minecraft minecraft = Minecraft.getInstance();
			Font fontRenderer = minecraft.font;
			int timeStringWidth = fontRenderer.width(timeString);
			int noteStringWidth = fontRenderer.width(noteString);
			fontRenderer.draw(poseStack, timeString, background.getWidth() - timeStringWidth, 45, 0xFF808080);
			fontRenderer.draw(poseStack, noteString, background.getWidth() - noteStringWidth, 1, 0x4582b3);
		}
	}

	@Override
	public @NotNull RecipeType<TeslaSynthesizerRecipe> getRecipeType() {
		return JEIPluginHandler.TESLA_SYNTHESIZER;
	}

	/**
	 * Get the title of the recipe category.
	 *
	 * @return String
	 */
	@Override
	public @NotNull Component getTitle() {
		return Component.translatable("gui.jei.category.tesla_synthesizer");
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
	public void setRecipe(IRecipeLayoutBuilder builder, TeslaSynthesizerRecipe recipe, @NotNull IFocusGroup focuses) {
		NonNullList<Ingredient> ingredients = NonNullList.create();
		ingredients.addAll(recipe.getIngredients());

		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
				.addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 26, 1)
				.addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 51, 1)
				.addIngredients(recipe.getIngredients().get(2));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 19)
				.addItemStack(recipe.getResultItem());
		builder.addSlot(RecipeIngredientRole.CATALYST, 51, 37)
				.addItemStack(new ItemStack(ItemRegistry.MOLTEN_INGOT.get()));
	}
}