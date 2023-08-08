package tech.anonymoushacker1279.iwcompatbridge.plugin.wthit.components;

import mcp.mobius.waila.api.*;
import net.minecraft.network.chat.Component;
import tech.anonymoushacker1279.immersiveweapons.blockentity.DamageableBlockEntity;

public class DamageableBlockComponent implements IBlockComponentProvider {

	@Override
	public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
		// Render data about current block damage / max health
		if (accessor.getBlockEntity() instanceof DamageableBlockEntity) {
			int health = accessor.getData().raw().getInt("health");
			int maxHealth = accessor.getData().raw().getInt("maxHealth");

			int stage = accessor.getData().raw().getInt("currentStage");
			int stages = accessor.getData().raw().getInt("stages");

			// Inverse the stages because 0 is the highest stage
			stage = stages - stage;

			tooltip.addLine(Component.translatable("iwcompatbridge.wthit.damageable_block.health", health, maxHealth));
			tooltip.addLine(Component.translatable("iwcompatbridge.wthit.damageable_block.stage", stage, stages));
		}
	}
}