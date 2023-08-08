package tech.anonymoushacker1279.iwcompatbridge.plugin.wthit;

import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.TooltipPosition;
import tech.anonymoushacker1279.immersiveweapons.block.PitfallBlock;
import tech.anonymoushacker1279.immersiveweapons.block.core.DamageableBlock;
import tech.anonymoushacker1279.immersiveweapons.blockentity.DamageableBlockEntity;
import tech.anonymoushacker1279.immersiveweapons.entity.npc.AbstractMerchantEntity;
import tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.components.DamageableBlockComponent;
import tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.components.MerchantEntityComponent;
import tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.data_provider.DamageableBlockDataProvider;
import tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.overrides.PitfallBlockOverride;

public class WTHITPluginRegisters {

	protected static void registerPlugin(IRegistrar registrar) {
		registrar.addOverride(new PitfallBlockOverride(), PitfallBlock.class);
		registrar.addComponent(new DamageableBlockComponent(), TooltipPosition.BODY, DamageableBlock.class);
		registrar.addComponent(new MerchantEntityComponent(), TooltipPosition.BODY, AbstractMerchantEntity.class);
		registrar.addBlockData(new DamageableBlockDataProvider(), DamageableBlockEntity.class);
	}
}