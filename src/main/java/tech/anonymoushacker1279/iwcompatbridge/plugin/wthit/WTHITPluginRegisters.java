package tech.anonymoushacker1279.iwcompatbridge.plugin.wthit;

import mcp.mobius.waila.api.IRegistrar;
import tech.anonymoushacker1279.immersiveweapons.block.PitfallBlock;
import tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.overrides.PitfallBlockOverride;

public class WTHITPluginRegisters {

	protected static void registerPlugin(IRegistrar registrar) {
		registrar.addOverride(new PitfallBlockOverride(), PitfallBlock.class);
	}
}