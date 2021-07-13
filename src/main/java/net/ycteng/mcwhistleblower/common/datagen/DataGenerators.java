package net.ycteng.mcwhistleblower.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.ycteng.mcwhistleblower.McWhistleblower;

@Mod.EventBusSubscriber(modid = McWhistleblower.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		if (event.includeServer()) {
			//generator.addProvider(new Recipes(generator));
			//generator.addProvider(new LootTables(generator));
		}
		if (event.includeClient()) {
			generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
			generator.addProvider(new Items(generator, event.getExistingFileHelper()));
		}
		
	}
}
