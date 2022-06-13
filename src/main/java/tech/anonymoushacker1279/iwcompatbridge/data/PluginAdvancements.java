package tech.anonymoushacker1279.iwcompatbridge.data;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.*;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.LocationTrigger;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;

import java.util.function.Consumer;

public class PluginAdvancements implements Consumer<Consumer<Advancement>> {

	PluginAdvancements() {
	}

	@Override
	public void accept(Consumer<Advancement> consumer) {
		// Root advancement
		// Overrides the default IW one to include a reward for the encyclopedia on unlock
		Builder.advancement()
				.display(DeferredRegistryHandler.TESLA_SWORD.get(),
						new TranslatableComponent("advancements.immersiveweapons.root.title")
								.withStyle(ChatFormatting.RED),
						new TranslatableComponent("advancements.immersiveweapons.root.description"),
						new ResourceLocation(ImmersiveWeapons.MOD_ID, "textures/block/red_stained_bulletproof_glass.png"),
						FrameType.TASK, false, false, false)
				.addCriterion("exist",
						LocationTrigger.TriggerInstance.located(LocationPredicate.inDimension(Level.OVERWORLD)))
				.rewards(AdvancementRewards.Builder.loot(new ResourceLocation(ImmersiveWeapons.MOD_ID, "grant_encyclopedia_book_on_first_join")))
				.save(consumer, "immersiveweapons:root");
	}
}