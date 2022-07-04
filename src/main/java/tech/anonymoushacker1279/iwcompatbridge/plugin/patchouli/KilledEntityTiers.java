package tech.anonymoushacker1279.iwcompatbridge.plugin.patchouli;


import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class KilledEntityTiers {

	public static final MutableComponent PASSIVE = Component.translatable("iwcompatbridge.lorebook.killed_entity_tiers.passive");

	public static final MutableComponent MURDERER = Component.translatable("iwcompatbridge.lorebook.killed_entity_tiers.murderer");
	public static final MutableComponent SERIAL_KILLER = Component.translatable("iwcompatbridge.lorebook.killed_entity_tiers.serial_killer");
	public static final MutableComponent CULT_LEADER = Component.translatable("iwcompatbridge.lorebook.killed_entity_tiers.cult_leader");
	public static final MutableComponent SLAYER = Component.translatable("iwcompatbridge.lorebook.killed_entity_tiers.slayer");
	public static final MutableComponent KILL_EM_ALL = Component.translatable("iwcompatbridge.lorebook.killed_entity_tiers.kill_em_all");

	public static MutableComponent getTier(int kills) {
		int killRange = 0;

		if (kills < 20 && kills > 0) {
			killRange = 1;
		} else if (kills < 40 && kills > 20) {
			killRange = 2;
		} else if (kills < 80 && kills > 40) {
			killRange = 3;
		} else if (kills < 150 && kills > 80) {
			killRange = 4;
		} else if (kills > 300) {
			killRange = 5;
		}

		switch (killRange) {
			case 1 -> {
				return MURDERER;
			}
			case 2 -> {
				return SERIAL_KILLER;
			}
			case 3 -> {
				return CULT_LEADER;
			}
			case 4 -> {
				return SLAYER;
			}
			case 5 -> {
				return KILL_EM_ALL;
			}
			default -> {
				return PASSIVE;
			}
		}
	}
}