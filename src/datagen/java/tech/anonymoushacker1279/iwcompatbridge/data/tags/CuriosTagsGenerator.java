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
import tech.anonymoushacker1279.immersiveweapons.item.AccessoryItem;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;

import java.util.List;
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
		List<AccessoryItem> accessories = ItemRegistry.ITEMS.getEntries()
				.stream()
				.filter(holder -> holder.get() instanceof AccessoryItem)
				.map(holder -> (AccessoryItem) holder.get())
				.toList();

		for (AccessoryItem accessory : accessories) {
			switch (accessory.getSlot()) {
				case HEAD:
					tag(HEAD).add(accessory);
					break;
				case BODY:
					tag(BODY).add(accessory);
					break;
				case NECKLACE:
					tag(NECKLACE).add(accessory);
					break;
				case HAND:
					tag(HANDS).add(accessory);
					break;
				case BRACELET:
					tag(BRACELET).add(accessory);
					break;
				case RING:
					tag(RING).add(accessory);
					break;
				case BELT:
					tag(BELT).add(accessory);
					break;
				case CHARM:
					tag(CHARM).add(accessory);
					break;
				case SPIRIT:
					tag(SPIRIT).add(accessory);
					break;
			}
		}
	}
}