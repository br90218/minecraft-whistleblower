package net.ycteng.mcwhistleblower.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.ycteng.mcwhistleblower.McWhistleblower;
import net.ycteng.mcwhistleblower.client.data.CapabilityLocalPlayerState;
import net.ycteng.mcwhistleblower.client.data.ILocalPlayerState;

public class TeamArmorItem extends DyeableArmorItem {

	public TeamArmorItem(IArmorMaterial p_i50048_1_, EquipmentSlotType p_i50048_2_, Properties p_i50048_3_) {
		super(p_i50048_1_, p_i50048_2_, p_i50048_3_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		LazyOptional<ILocalPlayerState> playerState = entity.getCapability(CapabilityLocalPlayerState.LOCALPLAYERSTATE);
		int number = playerState.resolve().get().getBackNumber();
		if(slot == EquipmentSlotType.HEAD && type == null) {
			return McWhistleblower.MODID +":" + "textures/models/armor/teamarmor_yellow_layer_1.png";
		}
		if(slot == EquipmentSlotType.CHEST && type == null)
		{
			return McWhistleblower.MODID +":" + "textures/models/armor/teamarmor_yellow_layer_1.png";
		}
		if(slot == EquipmentSlotType.CHEST && type == "overlay")
		{
			return McWhistleblower.MODID + ":" + "textures/models/armor/number_dark_layer_1_overlay.png";
		}
		return null;
	}
}
