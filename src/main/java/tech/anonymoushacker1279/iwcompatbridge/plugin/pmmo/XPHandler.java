package tech.anonymoushacker1279.iwcompatbridge.plugin.pmmo;

import harmonised.pmmo.api.APIUtils;
import net.minecraft.server.level.ServerPlayer;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;

public class XPHandler {
	public static void awardSmallPartsTableCraftXP(ServerPlayer player) {
		APIUtils.awardXpTrigger(player.getUUID(), ImmersiveWeapons.MOD_ID + ".small_parts_table.craft",
				null, true, false);
	}

	public static void awardTeslaSynthesizerCraftXP(ServerPlayer player) {
		APIUtils.awardXpTrigger(player.getUUID(), ImmersiveWeapons.MOD_ID + ".tesla_synthesizer.craft",
				null, true, false);
	}
}