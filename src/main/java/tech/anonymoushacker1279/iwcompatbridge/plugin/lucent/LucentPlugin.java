package tech.anonymoushacker1279.iwcompatbridge.plugin.lucent;

import net.minecraftforge.fml.ModList;
import tech.anonymoushacker1279.immersiveweapons.api.IWPlugin;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;

public class LucentPlugin implements IWPlugin {

	@Override
	public String getPluginName() {
		return IWCompatBridge.MOD_ID + ":lucent_plugin";
	}

	@Override
	public boolean areLoadingRequirementsMet() {
		return ModList.get().isLoaded("lucent");
	}
}