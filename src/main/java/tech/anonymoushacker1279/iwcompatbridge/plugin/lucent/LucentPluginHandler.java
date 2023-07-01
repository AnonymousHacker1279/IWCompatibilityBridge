package tech.anonymoushacker1279.iwcompatbridge.plugin.lucent;

// TODO: uncomment when Lucent is updated
/*
@LucentPlugin
public class LucentPluginHandler implements ILucentPlugin {

	@Override
	public String ownerModID() {
		return IWCompatBridge.MOD_ID;
	}

	@Override
	public void getEntityLightLevel(EntityBrightness entityBrightness) {
		if (!CommonConfig.ENABLE_LUCENT_PLUGIN.get()) {
			return;
		}

		if (entityBrightness.getEntity() instanceof BulletEntity bullet) {
			if (bullet.tickCount < 2) {
				entityBrightness.setLightLevel(15);
			} else {
				entityBrightness.setLightLevel(0);
			}
		}

		if (entityBrightness.getEntity() instanceof MortarShellEntity mortarShell) {
			if (mortarShell.tickCount < 2) {
				entityBrightness.setLightLevel(15);
			} else {
				entityBrightness.setLightLevel(0);
			}
		}

		if (entityBrightness.getEntity() instanceof SmokeGrenadeEntity smokeGrenade) {
			if (smokeGrenade.tickCount < 80) {
				int lightLevel = (int) (7 * (1 - (smokeGrenade.tickCount / 80.0)));
				entityBrightness.setLightLevel(lightLevel);
			} else {
				entityBrightness.setLightLevel(0);
			}
		}
	}
}*/