package tech.anonymoushacker1279.iwcompatbridge;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import tech.anonymoushacker1279.immersiveweapons.api.PluginHandler;
import tech.anonymoushacker1279.iwcompatbridge.config.CommonConfig;
import tech.anonymoushacker1279.iwcompatbridge.init.IWCBDeferredRegistryHandler;
import tech.anonymoushacker1279.iwcompatbridge.plugin.curios.CuriosEventHandler;
import tech.anonymoushacker1279.iwcompatbridge.plugin.curios.CuriosPlugin;
import tech.anonymoushacker1279.iwcompatbridge.plugin.lucent.LucentPlugin;
import tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.WTHITPlugin;

@Mod(IWCompatBridge.MOD_ID)
public class IWCompatBridge {

	public static final String MOD_ID = "iwcompatbridge";

	// Setup logger
	public static final Logger LOGGER = LogUtils.getLogger();

	// Mod setup begins here
	public IWCompatBridge(IEventBus modEventBus) {
		LOGGER.info("IWCompatBridge is starting");

		// Load configuration
		LOGGER.info("Registering configuration files");
		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.COMMON_SPEC);

		// Initialize deferred registry
		IWCBDeferredRegistryHandler.init(modEventBus);

		modEventBus.addListener(IWCBDeferredRegistryHandler::setupCreativeTabs);

		// Register plugins
		// PluginHandler.registerPlugin(new JEIPlugin());
		PluginHandler.registerPlugin(new WTHITPlugin());
		// PluginHandler.registerPlugin(new PMMOPlugin());
		PluginHandler.registerPlugin(new LucentPlugin());
		PluginHandler.registerPlugin(new CuriosPlugin());

		if (ModList.get().isLoaded("curios")) {
			NeoForge.EVENT_BUS.addListener(CuriosEventHandler::curioEquipEvent);
		}
	}
}