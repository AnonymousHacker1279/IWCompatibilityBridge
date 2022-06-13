package tech.anonymoushacker1279.iwcompatbridge.plugin.pmmo;

import harmonised.pmmo.api.APIUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.immersiveweapons.api.IWPlugin;
import tech.anonymoushacker1279.immersiveweapons.api.events.SmallPartsTableCraftEvent;
import tech.anonymoushacker1279.immersiveweapons.api.events.TeslaSynthesizerCraftEvent;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.config.CommonConfig;

@EventBusSubscriber(modid = IWCompatBridge.MOD_ID, bus = Bus.FORGE)
public class PMMOPlugin implements IWPlugin {

	@Override
	public String getPluginName() {
		return IWCompatBridge.MOD_ID + ":pmmo_plugin";
	}

	@Override
	public boolean areLoadingRequirementsMet() {
		return ModList.get().isLoaded("pmmo");
	}

	@SubscribeEvent
	public static void smallPartsTableCraftEvent(SmallPartsTableCraftEvent event) {
		if (CommonConfig.ENABLE_PMMO_PLUGIN.get() && !event.getPlayer().level.isClientSide) {
			awardSmallPartsTableCraftXP((ServerPlayer) event.getPlayer());
		}
	}

	@SubscribeEvent
	public static void teslaSynthesizerCraftEvent(TeslaSynthesizerCraftEvent event) {
		if (CommonConfig.ENABLE_PMMO_PLUGIN.get() && !event.getPlayer().level.isClientSide) {
			awardTeslaSynthesizerCraftXP((ServerPlayer) event.getPlayer());
		}
	}

	public static void awardSmallPartsTableCraftXP(ServerPlayer player) {
		APIUtils.awardXpTrigger(player.getUUID(), ImmersiveWeapons.MOD_ID + ".small_parts_table.craft",
				null, true, false);
	}

	public static void awardTeslaSynthesizerCraftXP(ServerPlayer player) {
		APIUtils.awardXpTrigger(player.getUUID(), ImmersiveWeapons.MOD_ID + ".tesla_synthesizer.craft",
				null, true, false);
	}
}