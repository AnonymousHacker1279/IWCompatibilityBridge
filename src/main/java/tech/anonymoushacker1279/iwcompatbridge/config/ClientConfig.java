package tech.anonymoushacker1279.iwcompatbridge.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {

	public final ModConfigSpec.IntValue flareLightLevel;
	public final ModConfigSpec.IntValue fireflyLightLevel;
	public final ModConfigSpec.IntValue meteorLightLevel;
	public final ModConfigSpec.IntValue starWolfLightLevel;
	public final ModConfigSpec.IntValue muzzleFlashLightLevel;
	public final ModConfigSpec.IntValue mortarLightLevel;
	public final ModConfigSpec.IntValue throwableLightLevel;

	public ClientConfig(ModConfigSpec.Builder builder) {
		builder.comment("RyoamicLights settings")
				.push("RyoamicLights");

		flareLightLevel = builder
				.comment("The light level of flares")
				.defineInRange("flareLightLevel", 15, 0, 15);

		fireflyLightLevel = builder
				.comment("The light level of fireflies")
				.defineInRange("fireflyLightLevel", 8, 0, 15);

		meteorLightLevel = builder
				.comment("The light level of meteors")
				.defineInRange("meteorLightLevel", 15, 0, 15);

		starWolfLightLevel = builder
				.comment("The light level of star wolves")
				.defineInRange("starWolfLightLevel", 6, 0, 15);

		muzzleFlashLightLevel = builder
				.comment("The light level of muzzle flashes")
				.defineInRange("muzzleFlashLightLevel", 15, 0, 15);

		mortarLightLevel = builder
				.comment("The light level of mortars")
				.defineInRange("mortarLightLevel", 15, 0, 15);

		throwableLightLevel = builder
				.comment("The light level of throwable items")
				.defineInRange("throwableLightLevel", 15, 0, 15);

		builder.pop();
	}
}