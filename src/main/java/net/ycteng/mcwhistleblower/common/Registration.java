package net.ycteng.mcwhistleblower.common;


import static net.ycteng.mcwhistleblower.McWhistleblower.MODID;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.ycteng.mcwhistleblower.common.blocks.SnitchingMachineBlock;
import net.ycteng.mcwhistleblower.common.items.SnitchingSlip;

public class Registration 
{
	
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	
	public static void init()
	{
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<SnitchingMachineBlock> SNITCHINGMACHINEBLOCK = BLOCKS.register("snitchingmachineblock", SnitchingMachineBlock::new);
	public static final RegistryObject<Item> SNITCHINGMACHINEITEM = ITEMS.register("snitchingmachine", () -> new BlockItem(SNITCHINGMACHINEBLOCK.get(), new Item.Properties().tab(StartupCommon.ITEM_GROUP)));
	
	public static final RegistryObject<Item> SNITCHINGSLIPITEM = ITEMS.register("snitchingslip", SnitchingSlip::new);
}
