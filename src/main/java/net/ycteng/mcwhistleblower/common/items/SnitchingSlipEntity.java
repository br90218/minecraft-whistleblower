package net.ycteng.mcwhistleblower.common.items;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class SnitchingSlipEntity extends ItemEntity {

	public SnitchingSlipEntity(World world, double x, double y, double z,
			ItemStack itemStack) {
		super(world, x, y, z, itemStack);
	}
	
	public void saveContent(String content) {
		CompoundNBT slipNBT = new CompoundNBT();
		slipNBT.putString("content", content);
		super.save(slipNBT);
	}

}
