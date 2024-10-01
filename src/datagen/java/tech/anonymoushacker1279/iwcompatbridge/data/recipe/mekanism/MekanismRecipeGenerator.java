package tech.anonymoushacker1279.iwcompatbridge.data.recipe.mekanism;

import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.datagen.recipe.builder.*;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.registries.MekanismChemicals;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import tech.anonymoushacker1279.immersiveweapons.data.groups.common.CommonItemTagGroups;
import tech.anonymoushacker1279.immersiveweapons.init.*;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.data.recipe.IWCBRecipeProvider;
import tech.anonymoushacker1279.iwcompatbridge.init.IWCBChemicalRegistry;
import tech.anonymoushacker1279.iwcompatbridge.init.IWCBItemRegistry;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MekanismRecipeGenerator extends IWCBRecipeProvider {

	protected final ICondition modLoaded;

	public MekanismRecipeGenerator(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider);
		modLoaded = new ModLoadedCondition("mekanism");
	}

	@Override
	protected void buildRecipes(RecipeOutput output) {
		super.buildRecipes(output);

		injecting(new ItemStack(ItemRegistry.RAW_COBALT.get(), 3),
				MekanismChemicals.HYDROGEN_CHLORIDE.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_SHARD.get(), 8),
				"cobalt/shard/from_raw_ore");
		injecting(new ItemStack(BlockRegistry.RAW_COBALT_BLOCK.get()),
				MekanismChemicals.HYDROGEN_CHLORIDE.getStack(2),
				new ItemStack(IWCBItemRegistry.COBALT_SHARD.get(), 24),
				"cobalt/shard/from_raw_block");
		injecting(CommonItemTagGroups.COBALT_ORES,
				MekanismChemicals.HYDROGEN_CHLORIDE.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_SHARD.get(), 4),
				"cobalt/shard/from_ore");
		injecting(new ItemStack(IWCBItemRegistry.COBALT_CRYSTAL.get()),
				MekanismChemicals.HYDROGEN_CHLORIDE.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_SHARD.get()),
				"cobalt/shard/from_crystal");

		purifying(new ItemStack(IWCBItemRegistry.COBALT_SHARD.get()),
				MekanismChemicals.OXYGEN.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_CLUMP.get()),
				"cobalt/clump/from_shard");
		purifying(new ItemStack(ItemRegistry.RAW_COBALT.get()),
				MekanismChemicals.OXYGEN.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_CLUMP.get(), 2),
				"cobalt/clump/from_raw_ore");
		purifying(new ItemStack(BlockRegistry.RAW_COBALT_BLOCK.get()),
				MekanismChemicals.OXYGEN.getStack(2),
				new ItemStack(IWCBItemRegistry.COBALT_CLUMP.get(), 18),
				"cobalt/clump/from_raw_block");
		purifying(CommonItemTagGroups.COBALT_ORES,
				MekanismChemicals.OXYGEN.getStack(1),
				new ItemStack(IWCBItemRegistry.COBALT_CLUMP.get(), 3),
				"cobalt/clump/from_ore");

		crushing(IWCBItemRegistry.COBALT_CLUMP.get(),
				IWCBItemRegistry.COBALT_DIRTY_DUST.get(),
				"cobalt/dirty_dust/from_clump");
		crushing(CommonItemTagGroups.COBALT_INGOTS,
				IWCBItemRegistry.COBALT_DUST.get(),
				"cobalt/dust/from_ingot");

		enriching(IWCBItemRegistry.COBALT_DIRTY_DUST.get(),
				IWCBItemRegistry.COBALT_DUST.get(),
				"cobalt/dust/from_dirty_dust");
		enriching(CommonItemTagGroups.COBALT_ORES,
				new ItemStack(IWCBItemRegistry.COBALT_DUST.get(), 2),
				"cobalt/dust/from_ore");
		enriching(BlockRegistry.RAW_COBALT_BLOCK.get(),
				new ItemStack(IWCBItemRegistry.COBALT_DUST.get(), 12),
				"cobalt/dust/from_raw_block");
		enriching(new ItemStack(ItemRegistry.RAW_COBALT.get(), 3),
				new ItemStack(IWCBItemRegistry.COBALT_DUST.get(), 4),
				"cobalt/dust/from_raw_ore");

		createSmeltingRecipe(List.of(IWCBItemRegistry.COBALT_DUST.get()),
				ItemRegistry.COBALT_INGOT.get(),
				0.35f,
				200,
				null);
		createBlastingRecipe(List.of(IWCBItemRegistry.COBALT_DUST.get()),
				ItemRegistry.COBALT_INGOT.get(),
				0.35f,
				100,
				null);

		combining(new ItemStack(ItemRegistry.RAW_COBALT.get(), 20),
				Items.COBBLESTONE,
				BlockRegistry.COBALT_ORE.get(),
				"cobalt/ore/from_raw_ore");
		combining(new ItemStack(ItemRegistry.RAW_COBALT.get(), 20),
				Items.COBBLED_DEEPSLATE,
				BlockRegistry.DEEPSLATE_COBALT_ORE.get(),
				"cobalt/ore/deepslate_from_raw_ore");

		chemicalDissolution(new ItemStack(ItemRegistry.RAW_COBALT.get(), 3),
				MekanismChemicals.SULFURIC_ACID.getStack(1),
				IWCBChemicalRegistry.COBALT,
				2_000,
				"cobalt/slurry/from_raw_ore");

		chemicalDissolution(CommonItemTagGroups.COBALT_ORES,
				MekanismChemicals.SULFURIC_ACID.getStack(1),
				IWCBChemicalRegistry.COBALT,
				1_000,
				"cobalt/slurry/from_ore");

		chemicalDissolution(new ItemStack(BlockItemRegistry.RAW_COBALT_BLOCK_ITEM.get(), 1),
				MekanismChemicals.SULFURIC_ACID.getStack(2),
				IWCBChemicalRegistry.COBALT,
				6_000,
				"cobalt/slurry/from_raw_block");

		washing(IWCBChemicalRegistry.COBALT, "cobalt/clean_slurry/from_dirty_slurry");

		crystallizing(IWCBChemicalRegistry.COBALT,
				new ItemStack(IWCBItemRegistry.COBALT_CRYSTAL.get(), 1),
				"cobalt/crystal/from_slurry");
	}

	private void injecting(ItemStack input, ChemicalStack chemical, ItemStack output, String path) {
		ItemStackChemicalToItemStackRecipeBuilder.injecting(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.chemicalStack().from(chemical),
						output,
						true)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output, ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void injecting(TagKey<Item> input, ChemicalStack gas, ItemStack output, String path) {
		ItemStackChemicalToItemStackRecipeBuilder.injecting(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.chemicalStack().from(gas),
						output,
						true)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output, ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void purifying(ItemStack input, ChemicalStack gas, ItemStack output, String path) {
		ItemStackChemicalToItemStackRecipeBuilder.purifying(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.chemicalStack().from(gas),
						output,
						true)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output, ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void purifying(TagKey<Item> input, ChemicalStack gas, ItemStack output, String path) {
		ItemStackChemicalToItemStackRecipeBuilder.purifying(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.chemicalStack().from(gas),
						output,
						true)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output, ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void crushing(ItemLike input, ItemLike output, String path) {
		ItemStackToItemStackRecipeBuilder.crushing(
						IngredientCreatorAccess.item().from(input),
						new ItemStack(output))
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void crushing(TagKey<Item> input, ItemLike output, String path) {
		ItemStackToItemStackRecipeBuilder.crushing(
						IngredientCreatorAccess.item().from(input),
						new ItemStack(output))
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void enriching(ItemLike input, ItemLike output, String path) {
		ItemStackToItemStackRecipeBuilder.enriching(
						IngredientCreatorAccess.item().from(input),
						new ItemStack(output))
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void enriching(ItemLike input, ItemStack output, String path) {
		ItemStackToItemStackRecipeBuilder.enriching(
						IngredientCreatorAccess.item().from(input),
						output)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void enriching(ItemStack input, ItemStack output, String path) {
		ItemStackToItemStackRecipeBuilder.enriching(
						IngredientCreatorAccess.item().from(input),
						output)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void enriching(TagKey<Item> input, ItemStack output, String path) {
		ItemStackToItemStackRecipeBuilder.enriching(
						IngredientCreatorAccess.item().from(input),
						output)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void combining(ItemStack input, ItemLike extraInput, ItemLike output, String path) {
		CombinerRecipeBuilder.combining(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.item().from(extraInput),
						new ItemStack(output))
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void chemicalDissolution(TagKey<Item> input, ChemicalStack chemicalInput, SlurryRegistryObject<Chemical, Chemical> slurryObject, long amount, String path) {
		ChemicalDissolutionRecipeBuilder.dissolution(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.chemicalStack().from(chemicalInput),
						slurryObject.getDirtySlurry().getStack(amount),
						true)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void chemicalDissolution(ItemStack input, ChemicalStack chemicalInput, SlurryRegistryObject<Chemical, Chemical> slurryObject, long amount, String path) {
		ChemicalDissolutionRecipeBuilder.dissolution(
						IngredientCreatorAccess.item().from(input),
						IngredientCreatorAccess.chemicalStack().from(chemicalInput),
						slurryObject.getDirtySlurry().getStack(amount),
						true)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void washing(SlurryRegistryObject<Chemical, Chemical> slurryObject, String path) {
		FluidChemicalToChemicalRecipeBuilder.washing(
						IngredientCreatorAccess.fluid().from(FluidTags.WATER, 5),
						IngredientCreatorAccess.chemicalStack().from(slurryObject.getDirtySlurry(), 1),
						slurryObject.getCleanSlurry().getStack(1)
				)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}

	private void crystallizing(SlurryRegistryObject<Chemical, Chemical> input, ItemStack output, String path) {
		ChemicalCrystallizerRecipeBuilder.crystallizing(
						IngredientCreatorAccess.chemicalStack().from(input.getCleanSlurry(), 200),
						output
				)
				.addCondition(modLoaded)
				.build(IWCBRecipeProvider.output,
						ResourceLocation.fromNamespaceAndPath(IWCompatBridge.MOD_ID, path));
	}
}