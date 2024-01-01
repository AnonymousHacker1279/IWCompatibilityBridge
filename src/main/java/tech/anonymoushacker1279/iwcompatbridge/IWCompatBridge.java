package tech.anonymoushacker1279.iwcompatbridge;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import tech.anonymoushacker1279.immersiveweapons.api.PluginHandler;
import tech.anonymoushacker1279.iwcompatbridge.config.CommonConfig;
import tech.anonymoushacker1279.iwcompatbridge.init.IWCBDeferredRegistryHandler;
import tech.anonymoushacker1279.iwcompatbridge.plugin.curios.CuriosEventHandler;
import tech.anonymoushacker1279.iwcompatbridge.plugin.curios.CuriosPlugin;
import tech.anonymoushacker1279.iwcompatbridge.plugin.jei.JEIPlugin;
import tech.anonymoushacker1279.iwcompatbridge.plugin.lucent.LucentPlugin;
import tech.anonymoushacker1279.iwcompatbridge.plugin.pmmo.PMMOPlugin;
import tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.WTHITPlugin;

@Mod(IWCompatBridge.MOD_ID)
public class IWCompatBridge {

	public static final String MOD_ID = "iwcompatbridge";

	// Setup logger
	public static final Logger LOGGER = LogUtils.getLogger();

	// Mod setup begins here
	public IWCompatBridge() {
		LOGGER.info("IWCompatBridge is starting");

		// Load configuration
		LOGGER.info("Registering configuration files");
		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.COMMON_SPEC);

		// Initialize deferred registry
		IWCBDeferredRegistryHandler.init();

		// Register on the event bus
		MinecraftForge.EVENT_BUS.register(this);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(IWCBDeferredRegistryHandler::setupCreativeTabs);

		// Register plugins
		PluginHandler.registerPlugin(new JEIPlugin());
		PluginHandler.registerPlugin(new WTHITPlugin());
		PluginHandler.registerPlugin(new PMMOPlugin());
		PluginHandler.registerPlugin(new LucentPlugin());
		PluginHandler.registerPlugin(new CuriosPlugin());

		if (ModList.get().isLoaded("curios")) {
			MinecraftForge.EVENT_BUS.addListener(CuriosEventHandler::curioEquipEvent);
		}
	}
}