package tech.anonymoushacker1279.iwcompatbridge.plugin.wthit;

import mcp.mobius.waila.api.*;
import net.minecraftforge.fml.ModList;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.iwcompatbridge.config.CommonConfig;

@WailaPlugin(id = ImmersiveWeapons.MOD_ID + ":waila_plugin")
@SuppressWarnings("unused")
public class WTHITPluginHandler implements IWailaPlugin {
	private final ModList modList = ModList.get();

	@Override
	public void register(IRegistrar registrar) {
		if (modList.isLoaded("jade")) {
			ImmersiveWeapons.LOGGER.info("Jade is installed, which is incompatible with the Waila plugin. The plugin will be disabled to prevent crashes.");
		} else if (CommonConfig.ENABLE_WTHIT_PLUGIN.get()) {
			WTHITPluginRegisters.registerPlugin(registrar);
		}
	}
}