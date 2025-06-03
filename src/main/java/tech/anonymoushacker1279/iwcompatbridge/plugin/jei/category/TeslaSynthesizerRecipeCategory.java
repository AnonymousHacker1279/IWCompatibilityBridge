package tech.anonymoushacker1279.iwcompatbridge.plugin.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import tech.anonymoushacker1279.immersiveweapons.init.BlockRegistry;
import tech.anonymoushacker1279.immersiveweapons.init.ItemRegistry;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.TeslaSynthesizerRecipe;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.JEIPluginHandler;

public class TeslaSynthesizerRecipeCategory extends AbstractRecipeCategory<TeslaSynthesizerRecipe> {

	private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID,
			"textures/gui/jei/tesla_synthesizer.png");
	private final IDrawable background;

	/**
	 * Constructor for TeslaSynthesizerRecipeCategory.
	 *
	 * @param guiHelper a <code>IGuiHelper</code> instance
	 */
	public TeslaSynthesizerRecipeCategory(IGuiHelper guiHelper) {
		super(JEIPluginHandler.TESLA_SYNTHESIZER,
				Component.translatable("gui.jei.category.tesla_synthesizer"),
				guiHelper.createDrawableItemLike(BlockRegistry.TESLA_SYNTHESIZER.get()),
				132,
				54);

		background = guiHelper.createDrawable(GUI_TEXTURE, 0, 0, 132, 54);
	}

	@Override
	public void draw(TeslaSynthesizerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		drawText(recipe, guiGraphics);
	}

	@Override
	public void createRecipeExtras(IRecipeExtrasBuilder builder, TeslaSynthesizerRecipe recipe, IFocusGroup focuses) {
		builder.addAnimatedRecipeArrow(recipe.getCookTime() / 16)
				.setPosition(75, 19);
		builder.addAnimatedRecipeFlame(recipe.getCookTime() / 16)
				.setPosition(52, 20);
	}

	protected void drawText(TeslaSynthesizerRecipe recipe, GuiGraphics guiGraphics) {
		int cookTime = recipe.getCookTime();
		if (cookTime > 0) {
			int cookTimeSeconds = cookTime / 20;
			MutableComponent timeString = Component.translatable("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
			MutableComponent noteString = Component.translatable("gui.jei.category.tesla_synthesizer.note");
			Minecraft minecraft = Minecraft.getInstance();
			Font fontRenderer = minecraft.font;
			int timeStringWidth = fontRenderer.width(timeString);
			int noteStringWidth = fontRenderer.width(noteString);
			guiGraphics.drawString(fontRenderer, timeString, background.getWidth() - timeStringWidth, 45, 0x808080, false);
			guiGraphics.drawString(fontRenderer, noteString, background.getWidth() - noteStringWidth, 1, 0x4582b3, false);
		}
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
	public void setRecipe(IRecipeLayoutBuilder builder, TeslaSynthesizerRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
				.add(recipe.material1());
		builder.addSlot(RecipeIngredientRole.INPUT, 26, 1)
				.add(recipe.material2());
		builder.addSlot(RecipeIngredientRole.INPUT, 51, 1)
				.add(recipe.material3());
		builder.addSlot(RecipeIngredientRole.INPUT, 51, 37)
				.add(new ItemStack(ItemRegistry.MOLTEN_INGOT.get()));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 19)
				.add(recipe.result());
	}
}