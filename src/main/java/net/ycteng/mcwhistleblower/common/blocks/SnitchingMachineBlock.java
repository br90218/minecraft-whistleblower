package net.ycteng.mcwhistleblower.common.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.ycteng.mcwhistleblower.common.Registration;
import net.ycteng.mcwhistleblower.common.items.SnitchingSlip;

public class SnitchingMachineBlock extends Block 
{

	public SnitchingMachineBlock() 
	{
		super(Properties.of(Material.METAL)
				.sound(SoundType.METAL)
				.strength(-1f));
	}

	@Override
	public void appendHoverText(ItemStack itemStack, IBlockReader blockReader, List<ITextComponent> textComponentList,
			ITooltipFlag tooltipFlag) 
	{
		textComponentList.add(new TranslationTextComponent("message.snitchingmachine"));
	}
	
	@Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState().setValue(BlockStateProperties.FACING, context.getNearestLookingDirection().getOpposite());
    }
	
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
		if(!world.isClientSide) {
			ItemStack heldItemStack = player.getItemInHand(hand);
			boolean compareResult = heldItemStack.getItem().equals(Registration.SNITCHINGSLIPITEM.get());
			if(!compareResult)
			{
				System.out.println("nn");
				return ActionResultType.FAIL;
			}
			System.out.println("Received a snitching slip.");
			CompoundNBT slipNBT = heldItemStack.getTag();
			String content = slipNBT.getString(SnitchingSlip.CONTENT_KEY);
			System.out.println(content);
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		builder.add(BlockStateProperties.FACING);
	}
}
