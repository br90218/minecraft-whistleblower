package net.ycteng.mcwhistleblower.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.ycteng.mcwhistleblower.common.StartupCommon;
import net.ycteng.mcwhistleblower.common.network.Networking;
import net.ycteng.mcwhistleblower.common.network.OpenSnitchingSlipGuiPacket;

public class SnitchingSlip extends Item {

	public SnitchingSlip() {
		super(new Item.Properties()
				.stacksTo(1)
				.tab(StartupCommon.ITEM_GROUP));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		if(!world.isClientSide()) {
			Networking.sendToClient(new OpenSnitchingSlipGuiPacket(), (ServerPlayerEntity) playerEntity);
		}
		return ActionResult.success(playerEntity.getItemInHand(hand));
	}
}
