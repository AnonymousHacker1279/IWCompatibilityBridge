package tech.anonymoushacker1279.iwcompatbridge.plugin.ryoamiclights;

import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import tech.anonymoushacker1279.immersiveweapons.api.IWPlugin;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;

public class RyoamicLightsPlugin implements IWPlugin {

	@Override
	public void init(FMLCommonSetupEvent event) {
		if (areLoadingRequirementsMet()) {
			CustomLightHandlers.register();
		}
	}

	@Override
	public String getPluginName() {
		return IWCompatBridge.MOD_ID + ":ryoamiclights_plugin";
	}

	@Override
	public boolean areLoadingRequirementsMet() {
		return ModList.get().isLoaded("ryoamiclights");
	}
}