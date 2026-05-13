package tech.anonymoushacker1279.iwcompatbridge.plugin.curios;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import tech.anonymoushacker1279.immersiveweapons.api.events.AccessoryEvent;
import tech.anonymoushacker1279.immersiveweapons.item.accessory.Accessory;
import tech.anonymoushacker1279.immersiveweapons.item.accessory.AccessoryLoader;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.List;
import java.util.Optional;


@EventBusSubscriber(modid = IWCompatBridge.MOD_ID)
public class AccessoryEvents {

	@SubscribeEvent
	public static void collectEffects(AccessoryEvent.CollectEffects event) {
		double value = 0;

		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(event.getPlayer());
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> AccessoryLoader.ACCESSORIES.get(itemStack.getItem()) != null);

			for (SlotResult slotResult : curios) {
				if (AccessoryLoader.ACCESSORIES.get(slotResult.stack().getItem()) instanceof Accessory accessory) {
					if (!event.getPlayer().getCooldowns().isOnCooldown(slotResult.stack())) {
						value += accessory.getEffectValue(event.getType(), event.getPlayer());
					}
				}
			}
		}

		event.setEffect(value);
	}

	@SubscribeEvent
	public static void collectStandardAttributes(AccessoryEvent.CollectStandardAttributes event) {
		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(event.getPlayer());
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> AccessoryLoader.ACCESSORIES.get(itemStack.getItem()) != null);

			for (SlotResult slotResult : curios) {
				if (AccessoryLoader.ACCESSORIES.get(slotResult.stack().getItem()) instanceof Accessory accessory) {
					event.addAttributes(accessory.attributeModifiers());
				}
			}
		}
	}

	@SubscribeEvent
	public static void collectDynamicAttributes(AccessoryEvent.CollectDynamicAttributes event) {
		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(event.getPlayer());
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> AccessoryLoader.ACCESSORIES.get(itemStack.getItem()) != null);

			for (SlotResult slotResult : curios) {
				if (AccessoryLoader.ACCESSORIES.get(slotResult.stack().getItem()) instanceof Accessory accessory) {
					event.addAttributes(accessory.dynamicAttributeModifiers());
				}
			}
		}
	}

	@SubscribeEvent
	public static void collectMobEffects(AccessoryEvent.CollectMobEffects event) {
		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(event.getPlayer());
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> AccessoryLoader.ACCESSORIES.get(itemStack.getItem()) != null);

			for (SlotResult slotResult : curios) {
				if (AccessoryLoader.ACCESSORIES.get(slotResult.stack().getItem()) instanceof Accessory accessory) {
					event.addEffects(accessory.mobEffectInstances());
				}
			}
		}
	}

	@SubscribeEvent
	public static void accessoryActive(AccessoryEvent.AccessoryActive event) {
		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(event.getPlayer());
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItem() == event.getStack().getItem());

			if (!event.getPlayer().getCooldowns().isOnCooldown(event.getStack())) {
				event.setActive(!curios.isEmpty());
			}
		}
	}
}