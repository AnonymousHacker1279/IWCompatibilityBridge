package tech.anonymoushacker1279.iwcompatbridge.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import tech.anonymoushacker1279.iwcompatbridge.data.lang.IWCBLanguageGenerator;
import tech.anonymoushacker1279.iwcompatbridge.data.model.IWCBItemModelGenerator;
import tech.anonymoushacker1279.iwcompatbridge.data.recipe.mekanism.MekanismRecipeGenerator;

@Mod.EventBusSubscriber(bus = Bus.MOD)
public class CustomDataGenerator {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();

		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		// Client data
		generator.addProvider(event.includeClient(), new IWCBItemModelGenerator(output, existingFileHelper));
		generator.addProvider(event.includeClient(), new IWCBLanguageGenerator(output));

		// Server data
		generator.addProvider(event.includeServer(), new MekanismRecipeGenerator(output));
	}
}