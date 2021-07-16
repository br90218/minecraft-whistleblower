package net.ycteng.mcwhistleblower.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.ycteng.mcwhistleblower.McWhistleblower;
import net.ycteng.mcwhistleblower.common.Registration;

public class Items extends ItemModelProvider {
	
	public Items(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, McWhistleblower.MODID, existingFileHelper);
	}
	
	@Override
	protected void registerModels() {
		singleTexture(Registration.SNITCHINGSLIPITEM.get().getRegistryName().getPath(), new ResourceLocation("item/handheld"), "layer0", new ResourceLocation(McWhistleblower.MODID, "items/snitchingslip"));
		withExistingParent(Registration.SNITCHINGMACHINEITEM.get().getRegistryName().getPath(), new ResourceLocation(McWhistleblower.MODID, "blocks/snitchingmachineblock"));
		
	}
}
