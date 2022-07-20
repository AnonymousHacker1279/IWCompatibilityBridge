package tech.anonymoushacker1279.iwcompatbridge.plugin.pmmo;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import tech.anonymoushacker1279.immersiveweapons.api.IWPlugin;
import tech.anonymoushacker1279.immersiveweapons.api.events.SmallPartsTableCraftEvent;
import tech.anonymoushacker1279.immersiveweapons.api.events.TeslaSynthesizerCraftEvent;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.config.CommonConfig;

@EventBusSubscriber(modid = IWCompatBridge.MOD_ID, bus = Bus.FORGE)
public class PMMOPlugin implements IWPlugin {

	private static boolean isPMMOInstalled = false;

	@Override
	public String getPluginName() {
		return IWCompatBridge.MOD_ID + ":pmmo_plugin";
	}

	@Override
	public boolean areLoadingRequirementsMet() {
		return ModList.get().isLoaded("pmmo");
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		if (areLoadingRequirementsMet()) {
			isPMMOInstalled = true;
		}
	}

	@SubscribeEvent
	public static void smallPartsTableCraftEvent(SmallPartsTableCraftEvent event) {
		if (isPMMOInstalled && CommonConfig.ENABLE_PMMO_PLUGIN.get() && !event.getPlayer().level.isClientSide) {
			XPHandler.awardSmallPartsTableCraftXP((ServerPlayer) event.getPlayer());
		}
	}

	@SubscribeEvent
	public static void teslaSynthesizerCraftEvent(TeslaSynthesizerCraftEvent event) {
		if (isPMMOInstalled && CommonConfig.ENABLE_PMMO_PLUGIN.get() && !event.getPlayer().level.isClientSide) {
			XPHandler.awardTeslaSynthesizerCraftXP((ServerPlayer) event.getPlayer());
		}
	}
}