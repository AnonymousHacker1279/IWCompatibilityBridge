package tech.anonymoushacker1279.iwcompatbridge.plugin.curios;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import tech.anonymoushacker1279.immersiveweapons.event.game_effects.AccessoryManager;
import tech.anonymoushacker1279.immersiveweapons.item.AccessoryItem;
import tech.anonymoushacker1279.immersiveweapons.item.AccessoryItem.EffectType;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.*;

import static tech.anonymoushacker1279.immersiveweapons.event.game_effects.AccessoryManager.handleEffectScaling;

/*
 * A class to handle Curios integration. Classes here are invoked by IW in the relevant areas, when IWCB is loaded
 * and the Curios plugin is registered.
 */
@SuppressWarnings("unused")
public class AccessoryBridge {

	/**
	 * A modified version of {@link AccessoryManager#collectEffects(EffectType, Player)} designed to work with Curios.
	 * Collect the value of the given effect from all {@link AccessoryItem}s in the player's inventory.
	 *
	 * @param type   the <code>EffectType</code> to collect
	 * @param player the <code>Player</code> to collect from
	 * @return the value of the effect
	 */
	public static double collectEffects(EffectType type, Player player) {
		double value = 0;

		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItem() instanceof AccessoryItem);

			for (SlotResult slotResult : curios) {
				AccessoryItem accessoryItem = (AccessoryItem) slotResult.stack().getItem();
				value += handleEffectScaling(accessoryItem, type, player);
			}
		}

		return value;
	}

	/**
	 * A modified version of {@link AccessoryManager#collectStandardAttributes(Player)} designed to work with Curios.
	 * Collect the accessory attribute modifiers from all {@link AccessoryItem}s in the player's inventory.
	 *
	 * @param player the <code>Player</code> to collect from
	 * @return a <code>Map</code> of attribute modifiers
	 */
	public static Map<AttributeModifier, Attribute> collectStandardAttributes(Player player) {
		Map<AttributeModifier, Attribute> attributeMap = new HashMap<>(5);

		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItem() instanceof AccessoryItem);

			for (SlotResult slotResult : curios) {
				AccessoryItem accessoryItem = (AccessoryItem) slotResult.stack().getItem();
				if (!accessoryItem.getStandardAttributeModifiers().isEmpty()) {
					attributeMap.putAll(accessoryItem.getStandardAttributeModifiers());
				}
			}
		}

		return attributeMap;
	}

	/**
	 * A modified version of {@link AccessoryManager#collectDynamicAttributes(Player)} designed to work with Curios.
	 * Collect the dynamic accessory attribute modifiers from all {@link AccessoryItem}s in the player's inventory.
	 *
	 * @param player the <code>Player</code> to collect from
	 * @return a <code>Map</code> of attribute modifiers with their target values
	 */
	public static Map<Map<AttributeModifier, Attribute>, Double> collectDynamicAttributes(Player player) {
		Map<Map<AttributeModifier, Attribute>, Double> attributeMap = new HashMap<>(5);

		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItem() instanceof AccessoryItem);

			for (SlotResult slotResult : curios) {
				AccessoryItem accessoryItem = (AccessoryItem) slotResult.stack().getItem();
				if (!accessoryItem.getDynamicAttributeModifiers().isEmpty()) {
					attributeMap.putAll(accessoryItem.getDynamicAttributeModifiers());
				}
			}
		}

		return attributeMap;
	}

	/**
	 * A modified version of {@link AccessoryManager#collectMobEffects(Player)} designed to work with Curios.
	 * Collect the mob effect instances from all {@link AccessoryItem}s in the player's inventory.
	 *
	 * @param player the <code>Player</code> to collect from
	 * @return a <code>List</code> of mob effect instances
	 */
	public static List<MobEffectInstance> collectMobEffects(Player player) {
		List<MobEffectInstance> effectList = new ArrayList<>(5);

		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItem() instanceof AccessoryItem);

			for (SlotResult slotResult : curios) {
				AccessoryItem accessoryItem = (AccessoryItem) slotResult.stack().getItem();
				if (!accessoryItem.getMobEffects().isEmpty()) {
					effectList.addAll(accessoryItem.getMobEffects());
				}
			}
		}

		return effectList;
	}

	/**
	 * A modified version of {@link AccessoryItem#isAccessoryActive(Player, AccessoryItem)} designed to work with Curios.
	 * Checks if a given accessory is equipped in a Curios slot.
	 * <p>
	 * Because this checks for the first item, it will always be an active one.
	 */
	public static boolean isAccessoryActive(Player player, AccessoryItem item) {
		Optional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
		if (optional.isPresent()) {
			ICuriosItemHandler itemHandler = optional.get();
			List<SlotResult> curios = itemHandler.findCurios(itemStack -> itemStack.getItem() == item);

			// Check if the item has a cooldown
			if (player.getCooldowns().isOnCooldown(item)) {
				return false;
			}

			return !curios.isEmpty();
		}

		return false;
	}
}