package tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.data_provider;

import mcp.mobius.waila.api.*;
import tech.anonymoushacker1279.immersiveweapons.blockentity.DamageableBlockEntity;

public class DamageableBlockDataProvider implements IDataProvider<DamageableBlockEntity> {

	@Override
	public void appendData(IDataWriter data, IServerAccessor<DamageableBlockEntity> accessor, IPluginConfig config) {
		data.raw().merge(accessor.getTarget().getUpdateTag());
	}
}