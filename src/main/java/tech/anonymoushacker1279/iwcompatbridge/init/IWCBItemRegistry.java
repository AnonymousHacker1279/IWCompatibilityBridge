package tech.anonymoushacker1279.iwcompatbridge.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.*;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;

public class IWCBItemRegistry {

	// Item Register
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IWCompatBridge.MOD_ID);

	public static final RegistryObject<Item> COBALT_SHARD = ITEMS.register("cobalt_shard", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> COBALT_CLUMP = ITEMS.register("cobalt_clump", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> COBALT_DIRTY_DUST = ITEMS.register("cobalt_dirty_dust", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> COBALT_DUST = ITEMS.register("cobalt_dust", () -> new Item(new Item.Properties()));
}