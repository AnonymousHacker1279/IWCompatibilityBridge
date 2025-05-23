package tech.anonymoushacker1279.iwcompatbridge.plugin.curios;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import tech.anonymoushacker1279.immersiveweapons.event.game_effects.AccessoryManager;
import tech.anonymoushacker1279.immersiveweapons.item.accessory.Accessory;
import tech.anonymoushacker1279.immersiveweapons.item.accessory.AccessoryEffectType;
import tech.anonymoushacker1279.immersiveweapons.item.accessory.scaling.AccessoryEffectScalingType;
import tech.anonymoushacker1279.immersiveweapons.item.accessory.scaling.AttributeOperation;
import tech.anonymoushacker1279.immersiveweapons.item.accessory.scaling.DynamicAttributeOperationInstance;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/*
 * A class to handle Curios integration. Classes here are invoked by IW in the relevant areas, when IWCB is loaded
 * and the Curios plugin is registered.
 */
@SuppressWarnings("unused")
public class AccessoryBridge {

	/**
	 * A modified version of {@link AccessoryManager#collectEffects(AccessoryEffectType, Player)} designed to work with
	 * Curios. Collect the value of the given effect from all accessories in the player's inventory.
	 *
	 * @param type   the <code>AccessoryEffectType</code> to collect
	 * @param player the <code>Player</code> to collect from
	 * @return the value of the effect
	 */
	public static double collectEffects(AccessoryEffectType type, Player player) {
		double value = 0;

		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItemHolder().getData(Accessory.ACCESSORY) != null);

			for (SlotResult slotResult : curios) {
				Accessory accessory = slotResult.stack().getItemHolder().getData(Accessory.ACCESSORY);
				AccessoryEffectScalingType scalingType;
				if (accessory != null) {
					scalingType = accessory.effectScalingTypes().get(type.name());
					if (scalingType != null) {
						value += scalingType.getEffectValue(accessory, type, player);
					}
				}
			}
		}

		return value;
	}

	/**
	 * A modified version of {@link AccessoryManager#collectStandardAttributes(Player)} designed to work with Curios.
	 * Collect the accessory attribute modifiers from all accessories in the player's inventory.
	 *
	 * @param player the <code>Player</code> to collect from
	 * @return a <code>Map</code> of attribute modifiers
	 */
	public static List<AttributeOperation> collectStandardAttributes(Player player) {
		List<AttributeOperation> attributes = new ArrayList<>(5);

		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItemHolder().getData(Accessory.ACCESSORY) != null);

			for (SlotResult slotResult : curios) {
				Accessory accessory = slotResult.stack().getItemHolder().getData(Accessory.ACCESSORY);
				if (accessory != null) {
					attributes.addAll(accessory.attributeModifiers());
				}
			}
		}

		return attributes;
	}

	/**
	 * A modified version of {@link AccessoryManager#collectDynamicAttributes(Player)} designed to work with Curios.
	 * Collect the dynamic accessory attribute modifiers from all accessories in the player's inventory.
	 *
	 * @param player the <code>Player</code> to collect from
	 * @return a <code>Map</code> of attribute modifiers with their target values
	 */
	public static List<DynamicAttributeOperationInstance> collectDynamicAttributes(Player player) {
		List<DynamicAttributeOperationInstance> dynamicAttributes = new ArrayList<>(5);

		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItemHolder().getData(Accessory.ACCESSORY) != null);

			for (SlotResult slotResult : curios) {
				Accessory accessory = slotResult.stack().getItemHolder().getData(Accessory.ACCESSORY);
				if (accessory != null) {
					dynamicAttributes.addAll(accessory.dynamicAttributeModifiers());
				}
			}
		}

		return dynamicAttributes;
	}

	/**
	 * A modified version of {@link AccessoryManager#collectMobEffects(Player)} designed to work with Curios. Collect
	 * the mob effect instances from all attributes in the player's inventory.
	 *
	 * @param player the <code>Player</code> to collect from
	 * @return a <code>List</code> of mob effect instances
	 */
	public static List<MobEffectInstance> collectMobEffects(Player player) {
		List<MobEffectInstance> effectList = new ArrayList<>(5);

		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItemHolder().getData(Accessory.ACCESSORY) != null);

			for (SlotResult slotResult : curios) {
				Accessory accessory = slotResult.stack().getItemHolder().getData(Accessory.ACCESSORY);
				if (accessory != null) {
					effectList.addAll(accessory.mobEffectInstances());
				}
			}
		}

		return effectList;
	}

	/**
	 * A modified version of {@link Accessory#isAccessoryActive(Player, ItemStack)} designed to work with Curios. Checks
	 * if a given accessory is equipped in a Curios slot.
	 * <p>
	 * Because this checks for the first item, it will always be an active one.
	 */
	public static boolean isAccessoryActive(Player player, ItemStack stack) {
		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItemHolder().getData(Accessory.ACCESSORY) != null && itemStack.getItem() == stack.getItem());

			// Check if the item has a cooldown
			if (player.getCooldowns().isOnCooldown(stack)) {
				return false;
			}

			return !curios.isEmpty();
		}

		return false;
	}
}