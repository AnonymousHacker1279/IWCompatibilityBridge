package tech.anonymoushacker1279.iwcompatbridge.plugin.curios;

public class CuriosEventHandler {

	/**
	 * Prevent multiple IW accessories of the same item from being equipped at once, if the config option is enabled.
	 *
	 * @param event the <code>CurioCanEquipEvent</code> instance
	 */
	/*public static void curioEquipEvent(CurioCanEquipEvent event) {
		if (!IWCBConfigs.SERVER.accessoryStacking.getAsBoolean()) {
			CuriosApi.getCuriosInventory(event.getEntity())
					.ifPresent(iCuriosItemHandler -> iCuriosItemHandler.findCurios(event.getSlotContext().identifier())
							.forEach(slotResult -> {
								if (slotResult.stack().getItem() == event.getStack().getItem() && event.getStack().getItemHolder().getData(Accessory.ACCESSORY) != null) {
									event.setEquipResult(TriState.FALSE);
								}
							}));
		}
	}*/
}