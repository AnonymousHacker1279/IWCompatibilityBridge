package tech.anonymoushacker1279.iwcompatbridge.data.recipe.mekanism;

import mekanism.api.chemical.gas.GasStack;
import mekanism.api.datagen.recipe.builder.*;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismGases;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import tech.anonymoushacker1279.immersiveweapons.data.tags.groups.forge.ForgeItemTagGroups;
import tech.anonymoushacker1279.immersiveweapons.init.BlockRegistry;
import tech.anonymoushacker1279.immersiveweapons.init.ItemRegistry;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.data.recipe.IWCBRecipeProvider;
import tech.anonymoushacker1279.iwcompatbridge.init.IWCBItemRegistry;

import java.util.List;
import java.util.function.Consumer;

public class MekanismRecipeGenerator extends IWCBRecipeProvider {

	protected final ICondition modLoaded;

	public MekanismRecipeGenerator(PackOutput output) {
		super(output);
		modLoaded = new ModLoadedCondition("mekanism");

	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {
		super.buildRecipes(recipeConsumer);

		injecting(new ItemStack(ItemRegistry.RAW_COBALT.get(), 3),
				MekanismGases.HYDROGEN_CHLORIDE.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_SHARD.get(), 8),
				"cobalt/shard/from_raw_ore");
		injecting(new ItemStack(BlockRegistry.RAW_COBALT_BLOCK.get()),
				MekanismGases.HYDROGEN_CHLORIDE.getStack(2),
				new ItemStack(IWCBItemRegistry.COBALT_SHARD.get(), 24),
				"cobalt/shard/from_raw_block");
		injecting(ForgeItemTagGroups.COBALT_ORES,
				MekanismGases.HYDROGEN_CHLORIDE.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_SHARD.get(), 4),
				"cobalt/shard/from_ore");

		purifying(new ItemStack(IWCBItemRegistry.COBALT_SHARD.get()),
				MekanismGases.OXYGEN.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_CLUMP.get()),
				"cobalt/clump/from_shard");
		purifying(new ItemStack(ItemRegistry.RAW_COBALT.get()),
				MekanismGases.OXYGEN.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_CLUMP.get(), 2),
				"cobalt/clump/from_raw_ore");
		purifying(new ItemStack(BlockRegistry.RAW_COBALT_BLOCK.get()),
				MekanismGases.OXYGEN.getStack(2),
				new ItemStack(IWCBItemRegistry.COBALT_CLUMP.get(), 18),
				"cobalt/clump/from_raw_block");
		purifying(ForgeItemTagGroups.COBALT_ORES,
				MekanismGases.OXYGEN.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_CLUMP.get(), 3),
				"cobalt/clump/from_ore");

		crushing(IWCBItemRegistry.COBALT_CLUMP.get(), IWCBItemRegistry.COBALT_DIRTY_DUST.get(), "cobalt/dirty_dust/from_clump");
		crushing(ForgeItemTagGroups.COBALT_INGOTS, IWCBItemRegistry.COBALT_DUST.get(), "cobalt/dust/from_ingot");

		enriching(IWCBItemRegistry.COBALT_DIRTY_DUST.get(), IWCBItemRegistry.COBALT_DUST.get(), "cobalt/dust/from_dirty_dust");
		enriching(ForgeItemTagGroups.COBALT_ORES, new ItemStack(IWCBItemRegistry.COBALT_DUST.get(), 2), "cobalt/dust/from_ore");
		enriching(BlockRegistry.RAW_COBALT_BLOCK.get(), new ItemStack(IWCBItemRegistry.COBALT_DUST.get(), 12), "cobalt/dust/from_raw_block");
		enriching(new ItemStack(ItemRegistry.RAW_COBALT.get(), 3), new ItemStack(IWCBItemRegistry.COBALT_DUST.get(), 4), "cobalt/dust/from_raw_ore");

		createSmeltingRecipe(List.of(IWCBItemRegistry.COBALT_DUST.get()), ItemRegistry.COBALT_INGOT.get(), 0.35f, 200, null);
		createBlastingRecipe(List.of(IWCBItemRegistry.COBALT_DUST.get()), ItemRegistry.COBALT_INGOT.get(), 0.35f, 100, null);

		combining(new ItemStack(ItemRegistry.RAW_COBALT.get(), 20), Items.COBBLESTONE, BlockRegistry.COBALT_ORE.get(), "cobalt/ore/from_raw_ore");
		combining(new ItemStack(ItemRegistry.RAW_COBALT.get(), 20), Items.COBBLED_DEEPSLATE, BlockRegistry.DEEPSLATE_COBALT_ORE.get(), "cobalt/ore/deepslate_from_raw_ore");
	}

	private void injecting(ItemStack input, GasStack gas, ItemStack output, String path) {
		ItemStackChemicalToItemStackRecipeBuilder.injecting(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.gas().from(gas),
						output)
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}

	private void injecting(TagKey<Item> input, GasStack gas, ItemStack output, String path) {
		ItemStackChemicalToItemStackRecipeBuilder.injecting(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.gas().from(gas),
						output)
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}

	private void purifying(ItemStack input, GasStack gas, ItemStack output, String path) {
		ItemStackChemicalToItemStackRecipeBuilder.purifying(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.gas().from(gas),
						output)
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}

	private void purifying(TagKey<Item> input, GasStack gas, ItemStack output, String path) {
		ItemStackChemicalToItemStackRecipeBuilder.purifying(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.gas().from(gas),
						output)
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}

	private void crushing(ItemLike input, ItemLike output, String path) {
		ItemStackToItemStackRecipeBuilder.crushing(IngredientCreatorAccess.item().from(input), new ItemStack(output))
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}

	private void crushing(TagKey<Item> input, ItemLike output, String path) {
		ItemStackToItemStackRecipeBuilder.crushing(IngredientCreatorAccess.item().from(input), new ItemStack(output))
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}

	private void enriching(ItemLike input, ItemLike output, String path) {
		ItemStackToItemStackRecipeBuilder.enriching(IngredientCreatorAccess.item().from(input), new ItemStack(output))
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}

	private void enriching(ItemLike input, ItemStack output, String path) {
		ItemStackToItemStackRecipeBuilder.enriching(IngredientCreatorAccess.item().from(input), output)
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}

	private void enriching(ItemStack input, ItemStack output, String path) {
		ItemStackToItemStackRecipeBuilder.enriching(IngredientCreatorAccess.item().from(input), output)
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}

	private void enriching(TagKey<Item> input, ItemStack output, String path) {
		ItemStackToItemStackRecipeBuilder.enriching(IngredientCreatorAccess.item().from(input), output)
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}

	private void combining(ItemStack input, ItemLike extraInput, ItemLike output, String path) {
		CombinerRecipeBuilder.combining(IngredientCreatorAccess.item().from(input), IngredientCreatorAccess.item().from(extraInput), new ItemStack(output))
				.addCondition(modLoaded)
				.build(finishedRecipeConsumer, new ResourceLocation(IWCompatBridge.MOD_ID, path));
	}
}