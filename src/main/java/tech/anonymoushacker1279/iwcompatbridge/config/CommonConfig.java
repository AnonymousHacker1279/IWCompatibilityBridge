package tech.anonymoushacker1279.iwcompatbridge.config;

import tech.anonymoushacker1729.cobaltconfig.config.ConfigEntry;

@SuppressWarnings("CanBeFinal")
public class CommonConfig {

	@ConfigEntry(comment = "Allow multiple accessories of the same type to be equipped at once", group = "Curios")
	public static boolean accessoryStacking = false;
}