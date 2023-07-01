package tech.anonymoushacker1279.iwcompatbridge.plugin.curios;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import tech.anonymoushacker1279.immersiveweapons.api.IWPlugin;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.config.CommonConfig;

@EventBusSubscriber(modid = IWCompatBridge.MOD_ID, bus = Bus.MOD)
public class CuriosPlugin implements IWPlugin {

	@Override
	public String getPluginName() {
		return IWCompatBridge.MOD_ID + ":curios_plugin";
	}

	@Override
	public boolean areLoadingRequirementsMet() {
		return CommonConfig.ENABLE_CURIOS_PLUGIN.get() && ModList.get().isLoaded("curios");
	}
}