package tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.data_provider;

import mcp.mobius.waila.api.IDataProvider;
import mcp.mobius.waila.api.IDataWriter;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IServerAccessor;
import tech.anonymoushacker1279.immersiveweapons.blockentity.TeslaSynthesizerBlockEntity;

public class TeslaSynthesizerBlockDataProvider implements IDataProvider<TeslaSynthesizerBlockEntity> {

	@Override
	public void appendData(IDataWriter data, IServerAccessor<TeslaSynthesizerBlockEntity> accessor, IPluginConfig config) {
		data.raw().merge(accessor.getTarget().getUpdateTag(accessor.getWorld().registryAccess()));
	}
}