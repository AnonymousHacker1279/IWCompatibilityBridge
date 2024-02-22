package tech.anonymoushacker1279.iwcompatbridge.plugin.lucent;

import com.legacy.lucent.api.EntityBrightness;
import com.legacy.lucent.api.plugin.ILucentPlugin;
import com.legacy.lucent.api.plugin.LucentPlugin;
import com.legacy.lucent.api.registry.ItemLightingRegistry;
import net.minecraft.util.Mth;
import tech.anonymoushacker1279.immersiveweapons.entity.projectile.*;
import tech.anonymoushacker1279.immersiveweapons.init.ItemRegistry;
import tech.anonymoushacker1279.iwcompatbridge.IWCompatBridge;
import tech.anonymoushacker1279.iwcompatbridge.config.ClientConfig;

@LucentPlugin
public class LucentPluginHandler implements ILucentPlugin {

	@Override
	public String ownerModID() {
		return IWCompatBridge.MOD_ID;
	}

	@Override
	public void getEntityLightLevel(EntityBrightness entityBrightness) {
		if (entityBrightness.getEntity() instanceof BulletEntity bullet) {
			if (bullet.tickCount < 2) {
				entityBrightness.setLightLevel(ClientConfig.muzzleFlashLightLevel);
			} else {
				entityBrightness.setLightLevel(0);
			}
		}

		if (entityBrightness.getEntity() instanceof MortarShellEntity mortarShell) {
			if (mortarShell.tickCount < 2) {
				entityBrightness.setLightLevel(ClientConfig.mortarFlashLightLevel);
			} else {
				entityBrightness.setLightLevel(0);
			}
		}

		if (entityBrightness.getEntity() instanceof AdvancedThrowableItemProjectile throwable) {
			int maxTicks = 300;

			if (throwable instanceof FlashbangEntity) {
				maxTicks = 60;
			}

			if (throwable.getDeltaMovement().lengthSqr() < 0.1d) {
				float ratio = (float) throwable.getTicksInGround() / maxTicks;
				int lightLevel = (int) Mth.lerp(ratio, ClientConfig.throwableFlashLightLevel, 0);
				lightLevel = Mth.clamp(lightLevel, 0, 15);

				entityBrightness.setLightLevel(lightLevel);
			} else {
				entityBrightness.setLightLevel(0);
			}
		}
	}

	@Override
	public void registerItemLightings(ItemLightingRegistry registry) {
		registry.register(ItemRegistry.THE_SWORD.get(), ClientConfig.theSwordLightLevel);
	}
}