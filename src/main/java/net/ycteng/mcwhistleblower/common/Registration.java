package net.ycteng.mcwhistleblower.common;


import static net.ycteng.mcwhistleblower.McWhistleblower.MODID;

import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.ycteng.mcwhistleblower.common.blocks.SnitchingMachineBlock;
import net.ycteng.mcwhistleblower.common.items.SnitchingSlip;
import net.ycteng.mcwhistleblower.common.items.TeamArmorItem;
import net.ycteng.mcwhistleblower.common.items.TeamArmorMaterial;

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
	
	public static final RegistryObject<SnitchingSlip> SNITCHINGSLIPITEM = ITEMS.register("snitchingslip", SnitchingSlip::new);
	
	public static final RegistryObject<TeamArmorItem> TEAMARMOR_HELMET = ITEMS.register("teamarmor_helmet", () -> new TeamArmorItem(TeamArmorMaterial.TEAMARMOR_BASE, EquipmentSlotType.HEAD, new Item.Properties().tab(StartupCommon.ITEM_GROUP)));
	public static final RegistryObject<TeamArmorItem> TEAMARMOR_LEGGINGS = ITEMS.register("teamarmor_leggings", () -> new TeamArmorItem(TeamArmorMaterial.TEAMARMOR_BASE, EquipmentSlotType.LEGS, new Item.Properties().tab(StartupCommon.ITEM_GROUP)));
	public static final RegistryObject<TeamArmorItem> TEAMARMOR_BOOTS = ITEMS.register("teamarmor_boots", () -> new TeamArmorItem(TeamArmorMaterial.TEAMARMOR_BASE, EquipmentSlotType.FEET, new Item.Properties().tab(StartupCommon.ITEM_GROUP)));
	public static final RegistryObject<TeamArmorItem> TEAMARMOR_CHESTPLATE = ITEMS.register("teamarmor_chestplate", () -> new TeamArmorItem(TeamArmorMaterial.TEAMARMOR_BASE, EquipmentSlotType.CHEST, new Item.Properties().tab(StartupCommon.ITEM_GROUP)));
}
