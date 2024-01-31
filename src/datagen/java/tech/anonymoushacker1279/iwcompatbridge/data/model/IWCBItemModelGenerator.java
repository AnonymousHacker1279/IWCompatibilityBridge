package tech.anonymoushacker1279.iwcompatbridge.data.model;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.init.IWCBItemRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class IWCBItemModelGenerator extends ItemModelProvider {

	public IWCBItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, IWCompatBridge.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		List<Item> items = new ArrayList<>(100);

		IWCBItemRegistry.ITEMS.getEntries().stream().map(Supplier::get).forEach(items::add);

		for (Item item : items) {
			basicItem(item);
		}
	}
}