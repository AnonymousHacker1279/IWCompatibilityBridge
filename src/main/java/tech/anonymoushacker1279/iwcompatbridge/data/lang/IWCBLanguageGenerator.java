package tech.anonymoushacker1279.iwcompatbridge.data.lang;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.init.IWCBItemRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class IWCBLanguageGenerator extends IWCBLanguageProvider {

	public IWCBLanguageGenerator(PackOutput output) {
		super(output, IWCompatBridge.MOD_ID);
	}

	@Override
	protected void addTranslations() {
		addItems();
		addJEICategories();
		addWTHITTooltips();

		add("itemGroup.iwcompatbridge.creative_tab", "IW Compatibility Bridge");
		add("curios.identifier.spirit", "Spirit");
	}

	private void addItems() {
		// Not all items are automatically added below; make a list of exceptions here
		List<Item> excludedItems = new ArrayList<>(25);

		excludedItems.add(IWCBItemRegistry.COBALT_DIRTY_DUST.get());

		// Filter the excluded items from the registry
		Stream<DeferredHolder<Item, ? extends Item>> items = IWCBItemRegistry.ITEMS.getEntries().stream()
				.filter(item -> !excludedItems.contains(item.get()));

		// Get a list of all items, and convert their registry names to proper names
		// Turn underscores into spaces, and capitalize the first letter of each word
		items.forEach(item -> {
			// Get the item name for the item
			String itemName = item.get().toString();

			// Convert underscores to spaces
			itemName = itemName.replace("_", " ");
			// Capitalize the first letter of all words
			itemName = capitalizeWords(itemName);

			// Add the item to the language file
			addItem(item, itemName);
		});

		// Manually add the items that were excluded above
		addItem(IWCBItemRegistry.COBALT_DIRTY_DUST, "Dirty Cobalt Dust");
	}

	private void addJEICategories() {
		addJEICategory("small_parts", "Small Parts Crafting");
		addJEICategory("tesla_synthesizer", "Tesla Synthesizing", "*16x speed");
		addJEICategory("astral_crystal", "Astral Crystal Sorcery");
		addJEICategory("barrel_tap_fermenting", "Barrel Fermenting");
		addJEICategory("piston_crushing", "Piston Crushing", "%s-%s drops");
		addJEICategory("ammunition_table", "Ammunition Crafting");
	}

	private void addWTHITTooltips() {
		addWTHITTooltip("damageable_block.health", "Health: %s/%s");
		addWTHITTooltip("damageable_block.stage", "Stage: %s/%s");
		addWTHITTooltip("merchant.trade_refresh_time", "Trades refresh in: %s:%s");
		addWTHITTooltip("tesla_synthesizer.progress", "Time remaining: %s:%s");
	}

	private String capitalizeWords(String str) {
		String[] words = str.split(" ");
		StringBuilder capitalizedString = new StringBuilder(25);
		for (String word : words) {
			String firstLetter = word.substring(0, 1);
			String restOfWord = word.substring(1);
			capitalizedString.append(firstLetter.toUpperCase()).append(restOfWord).append(" ");
		}
		return capitalizedString.toString().trim();
	}
}