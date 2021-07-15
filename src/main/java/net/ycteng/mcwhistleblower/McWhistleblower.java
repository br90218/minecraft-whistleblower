package net.ycteng.mcwhistleblower;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.ycteng.mcwhistleblower.client.StartupClient;
import net.ycteng.mcwhistleblower.common.Registration;
import net.ycteng.mcwhistleblower.common.StartupCommon;

@Mod(McWhistleblower.MODID)
public class McWhistleblower
{
	public static final String MODID = "mcwhistleblower";
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public McWhistleblower()
	{
		Registration.init();
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(StartupCommon::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(StartupClient::init);
	}
}
