package tech.anonymoushacker1279.iwcompatbridge.config;

import tech.anonymoushacker1729.cobaltconfig.config.ConfigEntry;

@SuppressWarnings("CanBeFinal")
public class ClientConfig {

	@ConfigEntry(comment = "Set the light level of muzzle flashes", group = "Lucent", min = 0, max = 15)
	public static int muzzleFlashLightLevel = 15;

	@ConfigEntry(comment = "Set the light level of mortar launches", group = "Lucent", min = 0, max = 15)
	public static int mortarFlashLightLevel = 15;

	@ConfigEntry(comment = "Set the light level of throwables when they explode", group = "Lucent", min = 0, max = 15)
	public static int throwableFlashLightLevel = 15;

	@ConfigEntry(comment = "Set the light level emitted by The Sword", group = "Lucent", min = 0, max = 15)
	public static int theSwordLightLevel = 8;
}