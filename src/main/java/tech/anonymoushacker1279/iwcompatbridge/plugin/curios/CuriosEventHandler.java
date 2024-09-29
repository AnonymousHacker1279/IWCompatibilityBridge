package tech.anonymoushacker1279.iwcompatbridge.plugin.curios;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.util.TriState;
import tech.anonymoushacker1279.immersiveweapons.item.AccessoryItem;
import tech.anonymoushacker1279.iwcompatbridge.config.IWCBConfigs;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioCanEquipEvent;

public class CuriosEventHandler {

	/**
	 * Prevent multiple IW accessories of the same item from being equipped at once, if the config option is enabled.
	 *
	 * @param event the <code>CurioCanEquipEvent</code> instance
	 */
	public static void curioEquipEvent(CurioCanEquipEvent event) {
		if (!IWCBConfigs.SERVER.accessoryStacking.getAsBoolean()) {
			CuriosApi.getCuriosInventory(event.getEntity())
					.ifPresent(iCuriosItemHandler -> iCuriosItemHandler.findCurios(event.getSlotContext().identifier())
							.forEach(slotResult -> {
								Item item = slotResult.stack().getItem();
								if (item == event.getStack().getItem() && item instanceof AccessoryItem) {
									event.setEquipResult(TriState.FALSE);
								}
							}));
		}
	}
}