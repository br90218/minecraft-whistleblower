package net.ycteng.mcwhistleblower.common.datagen;

import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.ycteng.mcwhistleblower.McWhistleblower;
import net.ycteng.mcwhistleblower.common.Registration;

public class BlockStates extends BlockStateProvider {

	public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, McWhistleblower.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		registerSnitchingMachineBlock();
	}
	
	/*
	 * This method exists because the default behaviour doesn't quite seem to
	 * place the textures at the correct orientation.
	 */
	private void registerSnitchingMachineBlock() {
		ResourceLocation texture = new ResourceLocation(McWhistleblower.MODID, "blocks/snitchingmachineblock");
		ResourceLocation textureTop = new ResourceLocation(McWhistleblower.MODID, "blocks/snitchingmachineblock-top");
		ResourceLocation textureFront = new ResourceLocation(McWhistleblower.MODID, "blocks/snitchingmachineblock-front");
		BlockModelBuilder snitchingMachineBlockDefault = models().cube("snitchingmachineblock", texture, textureTop, textureFront, texture, texture, texture);
		orientedBlock(Registration.SNITCHINGMACHINEBLOCK.get(), state -> {
			return snitchingMachineBlockDefault;
		});
	}
	
	
	/*
	 * This method puts down the block with the correct orientation. It also takes in
	 * a function (predicate?) that converts a blockstate to a modelfile.
	 */
	private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
		this.getVariantBuilder(block)
			.forAllStates(state -> {
				Direction dir = state.getValue(BlockStateProperties.FACING);
				return ConfiguredModel.builder()
						.modelFile(modelFunc.apply(state))
						.rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                        .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                        .build();
			});
	}

}
