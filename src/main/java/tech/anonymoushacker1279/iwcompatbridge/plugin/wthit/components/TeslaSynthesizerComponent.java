package tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.components;

import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.network.chat.Component;
import tech.anonymoushacker1279.immersiveweapons.blockentity.TeslaSynthesizerBlockEntity;

public class TeslaSynthesizerComponent implements IBlockComponentProvider {

	@Override
	public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
		if (accessor.getBlockEntity() instanceof TeslaSynthesizerBlockEntity) {
			int cookTime = accessor.getData().raw().getIntOr("CookTime", 0);
			int cookTimeTotal = accessor.getData().raw().getIntOr("CookTimeTotal", 0);

			if (cookTime > 0) {
				// Calculate remaining time
				int remainingTime = cookTimeTotal - cookTime;
				int minutes = remainingTime / 1200;
				int seconds = (remainingTime % 1200) / 20;

				tooltip.addLine(Component.translatable("iwcompatbridge.wthit.tesla_synthesizer.progress", minutes, seconds));
			}
		}
	}
}