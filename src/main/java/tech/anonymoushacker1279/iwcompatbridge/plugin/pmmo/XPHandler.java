package tech.anonymoushacker1279.iwcompatbridge.plugin.pmmo;

import harmonised.pmmo.api.APIUtils;
import net.minecraft.server.level.ServerPlayer;

public class XPHandler {

	public static void awardSmallPartsTableCraftXP(ServerPlayer player) {
		APIUtils.addXp("craft", player, 10);
	}

	public static void awardTeslaSynthesizerCraftXP(ServerPlayer player) {
		APIUtils.addXp("craft", player, 50);
	}
}