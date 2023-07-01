package tech.anonymoushacker1279.iwcompatbridge.plugin.curios;

import net.minecraft.world.entity.player.Player;
import tech.anonymoushacker1279.immersiveweapons.event.game_effects.AccessoryEffects;
import tech.anonymoushacker1279.immersiveweapons.item.AccessoryItem;
import tech.anonymoushacker1279.immersiveweapons.item.AccessoryItem.EffectType;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

/*
 * A class to handle Curios integration. Classes here are invoked by IW in the relevant areas, when IWCB is loaded
 * and the Curios plugin is registered.
 */
@SuppressWarnings("unused")
public class AccessoryBridge {

	/**
	 * A modified version of {@link AccessoryEffects#collectEffects(EffectType, Player)} designed to work with Curios.
	 * Collect the value of the given effect from all {@link AccessoryItem}s in the player's inventory.
	 *
	 * @param type   the <code>EffectType</code> to collect
	 * @param player the <code>Player</code> to collect from
	 * @return the value of the effect
	 */
	public static double collectEffects(EffectType type, Player player) {
		double value = 0;

		List<SlotResult> curios = CuriosApi.getCuriosHelper()
				.findCurios(player, itemStack -> itemStack.getItem() instanceof AccessoryItem);

		for (SlotResult slotResult : curios) {
			AccessoryItem accessoryItem = (AccessoryItem) slotResult.stack().getItem();
			value += accessoryItem.getEffects().getOrDefault(type, 0d);
		}

		return value;
	}

	/**
	 * A modified version of {@link AccessoryItem#isAccessoryActive(Player, AccessoryItem)} designed to work with Curios.
	 * Checks if a given accessory is equipped in a Curios slot.
	 * <p>
	 * Because this checks for the first item, it will always be an active one.
	 */
	public static boolean isAccessoryActive(Player player, AccessoryItem item) {
		List<SlotResult> curios = CuriosApi.getCuriosHelper()
				.findCurios(player, itemStack -> itemStack.getItem() == item);

		return !curios.isEmpty();
	}
}