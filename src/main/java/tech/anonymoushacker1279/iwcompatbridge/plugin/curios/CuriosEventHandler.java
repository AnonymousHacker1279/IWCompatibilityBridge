package tech.anonymoushacker1279.iwcompatbridge.plugin.curios;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event.Result;
import tech.anonymoushacker1279.immersiveweapons.init.ItemRegistry;
import tech.anonymoushacker1279.immersiveweapons.item.AccessoryItem;
import tech.anonymoushacker1279.iwcompatbridge.config.CommonConfig;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.event.CurioEquipEvent;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.List;
import java.util.Optional;

public class CuriosEventHandler {

	public static void curioEquipEvent(CurioEquipEvent event) {
		if (!CommonConfig.ENABLE_ACCESSORY_STACKING.get()) {
			// Prevent stacking of the same types of accessories, for IW items
			LivingEntity entity = event.getEntity();

			Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(entity).resolve();
			if (optional.isPresent()) {
				ICuriosItemHandler itemHandler = optional.get();
				List<SlotResult> curios = itemHandler.findCurios(itemStack -> {
					if (ItemRegistry.ITEMS.getEntries().stream().anyMatch(item -> item.get() instanceof AccessoryItem)) {
						return itemStack.getItem() == event.getStack().getItem();
					}

					return false;
				});

				if (!curios.isEmpty()) {
					event.setResult(Result.DENY);
				}
			}
		}
	}
}