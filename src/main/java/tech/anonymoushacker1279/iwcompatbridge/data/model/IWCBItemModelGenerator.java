package tech.anonymoushacker1279.iwcompatbridge.data.model;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.init.IWCBItemRegistry;

import java.util.ArrayList;
import java.util.List;

public class IWCBItemModelGenerator extends ItemModelProvider {

	public IWCBItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, IWCompatBridge.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		List<Item> items = new ArrayList<>(100);

		IWCBItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(items::add);

		for (Item item : items) {
			basicItem(item);
		}
	}
}