package tech.anonymoushacker1279.iwcompatbridge.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;

import java.util.function.Supplier;

public class IWCBItemRegistry {

	// Item Register
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, IWCompatBridge.MOD_ID);

	public static final Supplier<Item> COBALT_SHARD = ITEMS.register("cobalt_shard", () -> new Item(new Item.Properties()));
	public static final Supplier<Item> COBALT_CLUMP = ITEMS.register("cobalt_clump", () -> new Item(new Item.Properties()));
	public static final Supplier<Item> COBALT_DIRTY_DUST = ITEMS.register("cobalt_dirty_dust", () -> new Item(new Item.Properties()));
	public static final Supplier<Item> COBALT_DUST = ITEMS.register("cobalt_dust", () -> new Item(new Item.Properties()));
}