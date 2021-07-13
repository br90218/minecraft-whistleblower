package net.ycteng.mcwhistleblower.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WritableBookItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.ycteng.mcwhistleblower.common.StartupCommon;

public class SnitchingSlip extends WritableBookItem {

	public SnitchingSlip() {
		super(new Item.Properties()
				.stacksTo(1)
				.tab(StartupCommon.ITEM_GROUP));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		// TODO Auto-generated method stub
		return super.use(world, playerEntity, hand);
	}
	
	
}
