package tech.anonymoushacker1279.iwcompatbridge.plugin.ryoamiclights;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.thinkingstudio.ryoamiclights.api.DynamicLightHandlers;
import tech.anonymoushacker1279.immersiveweapons.data.groups.immersiveweapons.IWEntityTypeTagGroups;
import tech.anonymoushacker1279.immersiveweapons.entity.projectile.AdvancedThrowableItemProjectile;
import tech.anonymoushacker1279.immersiveweapons.init.EntityRegistry;
import tech.anonymoushacker1279.iwcompatbridge.config.IWCBConfigs;

public class CustomLightHandlers {

	public static void register() {
		DynamicLightHandlers.registerDynamicLightHandler(EntityRegistry.FLARE_ENTITY.get(), (flare) -> IWCBConfigs.CLIENT.flareLightLevel.getAsInt());
		DynamicLightHandlers.registerDynamicLightHandler(EntityRegistry.FIREFLY_ENTITY.get(), (firefly) -> IWCBConfigs.CLIENT.fireflyLightLevel.getAsInt());
		DynamicLightHandlers.registerDynamicLightHandler(EntityRegistry.METEOR_ENTITY.get(), (meteor) -> IWCBConfigs.CLIENT.meteorLightLevel.getAsInt());
		DynamicLightHandlers.registerDynamicLightHandler(EntityRegistry.STAR_WOLF_ENTITY.get(), (starWolf) -> IWCBConfigs.CLIENT.starWolfLightLevel.getAsInt());

		for (DeferredHolder<EntityType<?>, ? extends EntityType<?>> entityType : EntityRegistry.ENTITY_TYPES.getEntries()) {
			if (entityType.is(IWEntityTypeTagGroups.MUSKET_BALLS)) {
				DynamicLightHandlers.registerDynamicLightHandler(entityType.get(), (bullet) -> bullet.tickCount < 2 ? IWCBConfigs.CLIENT.muzzleFlashLightLevel.getAsInt() : 0);
			}
		}

		DynamicLightHandlers.registerDynamicLightHandler(EntityRegistry.MORTAR_SHELL_ENTITY.get(), (mortar) -> mortar.tickCount < 2 ? IWCBConfigs.CLIENT.mortarLightLevel.getAsInt() : 0);
		DynamicLightHandlers.registerDynamicLightHandler(EntityRegistry.FLASHBANG_ENTITY.get(), (flashbang) -> getThrowableLightLevel(flashbang, 20));
		DynamicLightHandlers.registerDynamicLightHandler(EntityRegistry.SMOKE_GRENADE_ENTITY.get(), (smokeGrenade) -> getThrowableLightLevel(smokeGrenade, 80));
	}

	private static int getThrowableLightLevel(AdvancedThrowableItemProjectile projectile, int maxTicks) {
		if (projectile.getDeltaMovement().lengthSqr() < 0.05d) {
			float ratio = (float) projectile.getTicksInGround() / maxTicks;
			return (int) Mth.clamp(Mth.lerp(ratio, IWCBConfigs.CLIENT.throwableLightLevel.getAsInt(), 0), 0, 15);
		} else {
			return 0;
		}
	}
}