package tech.anonymoushacker1279.iwcompatbridge.config;


import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import org.apache.commons.lang3.tuple.Pair;

public class CommonConfig {

	public static final ModConfigSpec COMMON_SPEC;
	public static final CommonConfig COMMON;

	public static ModConfigSpec.ConfigValue<Boolean> ENABLE_JEI_PLUGIN;
	public static ModConfigSpec.ConfigValue<Boolean> ENABLE_WTHIT_PLUGIN;
	public static ModConfigSpec.ConfigValue<Boolean> ENABLE_PMMO_PLUGIN;
	public static ModConfigSpec.ConfigValue<Boolean> ENABLE_LUCENT_PLUGIN;
	public static ModConfigSpec.ConfigValue<Boolean> ENABLE_CURIOS_PLUGIN;

	CommonConfig(ModConfigSpec.Builder builder) {
		builder.push("Plugin Configuration");

		builder.push("Plugin Settings");
		ENABLE_JEI_PLUGIN = builder
				.comment("Enable the JEI plugin")
				.define("enable_jei", true);
		ENABLE_WTHIT_PLUGIN = builder
				.comment("Enable the WTHIT plugin")
				.define("enable_wthit", true);
		ENABLE_PMMO_PLUGIN = builder
				.comment("Enable the Project MMO plugin")
				.define("enable_pmmo", true);
		ENABLE_LUCENT_PLUGIN = builder
				.comment("Enable the Lucent plugin")
				.define("enable_lucent", true);
		ENABLE_CURIOS_PLUGIN = builder
				.comment("Enable the Curios plugin")
				.define("enable_curios", true);
		builder.pop();

		builder.pop();
	}

	static {
		Pair<CommonConfig, ModConfigSpec> commonConfigModConfigSpecPair = new Builder().configure(CommonConfig::new);

		COMMON_SPEC = commonConfigModConfigSpecPair.getRight();
		COMMON = commonConfigModConfigSpecPair.getLeft();
	}
}