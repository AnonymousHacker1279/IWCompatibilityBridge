package tech.anonymoushacker1279.iwcompatbridge.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import tech.anonymoushacker1279.immersiveweapons.data.advancements.AdvancementProvider;

@Mod.EventBusSubscriber(bus = Bus.MOD)
public class CustomDataGenerator {

	/**
	 * Event handler for the GatherDataEvent.
	 *
	 * @param event the <code>GatherDataEvent</code> instance
	 */
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {

		DataGenerator generator = event.getGenerator();

		if (event.includeServer()) {
			generator.addProvider(new AdvancementProvider(generator, ImmutableList.of(new PluginAdvancements())));
		}
	}
}