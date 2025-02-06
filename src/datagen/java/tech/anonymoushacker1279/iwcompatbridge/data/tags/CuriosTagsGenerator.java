package tech.anonymoushacker1279.iwcompatbridge.data.tags;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import tech.anonymoushacker1279.immersiveweapons.init.ItemRegistry;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;

import java.util.concurrent.CompletableFuture;

public class CuriosTagsGenerator extends IntrinsicHolderTagsProvider<Item> {

	public static final TagKey<Item> BELT = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "belt"));
	public static final TagKey<Item> BODY = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "body"));
	public static final TagKey<Item> BRACELET = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "bracelet"));
	public static final TagKey<Item> CHARM = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "charm"));
	public static final TagKey<Item> HANDS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "hands"));
	public static final TagKey<Item> HEAD = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "head"));
	public static final TagKey<Item> NECKLACE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "necklace"));
	public static final TagKey<Item> RING = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "ring"));
	public static final TagKey<Item> SPIRIT = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "spirit"));

	public CuriosTagsGenerator(PackOutput output, CompletableFuture<Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
		super(output, Registries.ITEM, lookupProvider, item -> item.builtInRegistryHolder().key(), IWCompatBridge.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {
		tag(BELT).add(
				ItemRegistry.SATCHEL.get(),
				ItemRegistry.HOLY_MANTLE.get(),
				ItemRegistry.VENSTRAL_JAR.get());

		tag(BODY).add(
				ItemRegistry.POWDER_HORN.get(),
				ItemRegistry.BLOATED_HEART.get(),
				ItemRegistry.NETHERITE_SHIELD.get(),
				ItemRegistry.SUPER_BLANKET_CAPE.get());

		tag(BRACELET).add(ItemRegistry.AGILITY_BRACELET.get());

		tag(CHARM).add(
				ItemRegistry.BERSERKERS_AMULET.get(),
				ItemRegistry.BLADEMASTER_EMBLEM.get(),
				ItemRegistry.DEPTH_CHARM.get(),
				ItemRegistry.REINFORCED_DEPTH_CHARM.get(),
				ItemRegistry.ANCIENT_SCROLL.get(),
				ItemRegistry.MEDAL_OF_HONOR.get(),
				ItemRegistry.MEDAL_OF_DISHONOR.get());

		tag(HANDS).add(
				ItemRegistry.MELEE_MASTERS_MOLTEN_GLOVE.get(),
				ItemRegistry.IRON_FIST.get(),
				ItemRegistry.GLOVE_OF_RAPID_SWINGING.get(),
				ItemRegistry.HAND_OF_DOOM.get());

		tag(HEAD).add(
				ItemRegistry.GOGGLES.get(),
				ItemRegistry.LAVA_GOGGLES.get(),
				ItemRegistry.NIGHT_VISION_GOGGLES.get(),
				ItemRegistry.BLOODY_CLOTH.get());

		tag(NECKLACE).add(
				ItemRegistry.DEADEYE_PENDANT.get(),
				ItemRegistry.MEDAL_OF_ADEQUACY.get(),
				ItemRegistry.INSOMNIA_AMULET.get());

		tag(RING).add(
				ItemRegistry.COPPER_RING.get(),
				ItemRegistry.IRON_RING.get(),
				ItemRegistry.COBALT_RING.get(),
				ItemRegistry.GOLDEN_RING.get(),
				ItemRegistry.AMETHYST_RING.get(),
				ItemRegistry.EMERALD_RING.get(),
				ItemRegistry.DIAMOND_RING.get(),
				ItemRegistry.NETHERITE_RING.get(),
				ItemRegistry.DEATH_GEM_RING.get());

		tag(SPIRIT).add(
				ItemRegistry.HANS_BLESSING.get(),
				ItemRegistry.CELESTIAL_SPIRIT.get(),
				ItemRegistry.VOID_BLESSING.get());
	}
}