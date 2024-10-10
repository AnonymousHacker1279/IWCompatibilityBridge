package tech.anonymoushacker1279.iwcompatbridge.data;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.iwcompatbridge.data.lang.IWCBLanguageGenerator;
import tech.anonymoushacker1279.iwcompatbridge.data.model.IWCBItemModelGenerator;
import tech.anonymoushacker1279.iwcompatbridge.data.recipe.mekanism.MekanismRecipeGenerator;
import tech.anonymoushacker1279.iwcompatbridge.data.tags.CuriosTagsGenerator;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = Bus.MOD)
public class CustomDataGenerator {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		/*
		This is to ensure that this generator does not run in its parent project. This is probably not the best
		approach, but I have no idea how to do it better.
		*/
		if (event.getGenerator().getPackOutput().getOutputFolder().toString().toLowerCase().contains(ImmersiveWeapons.MOD_ID)) {
			return;
		}

		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();

		CompletableFuture<Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		// Client data
		generator.addProvider(event.includeClient(), new IWCBItemModelGenerator(output, existingFileHelper));
		generator.addProvider(event.includeClient(), new IWCBLanguageGenerator(output));

		// Server data
		generator.addProvider(event.includeServer(), new MekanismRecipeGenerator(output, event.getLookupProvider()));
		generator.addProvider(event.includeServer(), new CuriosTagsGenerator(output, lookupProvider, existingFileHelper));
	}
}