package tech.anonymoushacker1279.iwcompatbridge.plugin.jei;

import tech.anonymoushacker1279.immersiveweapons.api.IWPlugin;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;

public class JEIPlugin implements IWPlugin {

	@Override
	public String getPluginName() {
		return IWCompatBridge.MOD_ID + ":jei_plugin";
	}
}